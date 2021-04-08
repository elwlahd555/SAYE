package com.gokchu.saye.music.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.music.service.MusicService;
import com.gokchu.saye.music.service.SpotifyService;
import com.gokchu.saye.music.service.YoutubeService;
import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.SpotifyAccesstoken;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

	@Autowired
	private SpotifyService spotifyService;
	@Autowired
	private YoutubeService youtubeService;

	SpotifyApi spotifyApi = SpotifyAccesstoken.getSpotifyapi();

	// 토큰 생성
	@GetMapping("/accesstoken")
	public void accesstoken() {
		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		try {
			final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
			// Set access token for further "spotifyApi" object usage
			System.out.println(clientCredentials.getAccessToken());
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());
			System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// 가수명으로 음악 검색
	@GetMapping("/searchartist")
	public ArtistSimplified searchTracks_Sync(String q) {
		SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q).market(CountryCode.KR).limit(5).build();
		String preview = "";
		ArtistSimplified artist = null;
		try {
			final Paging<Track> trackPaging = searchTracksRequest.execute();
			System.out.println("트랙 크기 : " + trackPaging.getItems().length);
			System.out.println(trackPaging.getItems()[0].toString());
			for (Track t : trackPaging.getItems()) {
				System.out.println("제목 : " + t.getName());
				System.out.println("가수 : " + t.getArtists()[0].getName());
				artist = t.getArtists()[0];
				preview = t.getPreviewUrl();
				System.out.println(preview);
			}
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return artist;
	}

	// 가수, 제목 일괄 검색
	@GetMapping("/search")
	public List<Music> search(String q) {
		SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q).market(CountryCode.KR).limit(50).build();
		String preview = "";
		Artist artist = null;
		List<Music> musics = new ArrayList<Music>();
		try {
			final Paging<Track> trackPaging = searchTracksRequest.execute();
			System.out.println("트랙 크기 : " + trackPaging.getItems().length);
			System.out.println(trackPaging.getItems()[0].toString());
			ArrayList<Music> youtube = new ArrayList<Music>();
			for (Track t : trackPaging.getItems()) {

				System.out.println("가수 : " + t.getArtists()[0].getName());
				artist = getArtist_Sync(t.getArtists()[0].getId());
				System.out.println(preview);
				Music music = new Music();
				// 제목
				System.out.println("제목 : " + t.getName());
				music.setmTitle(t.getName());
				// 장르
				String genres = "";
				if (artist.getGenres().length < 6) {
					for (String s : artist.getGenres()) {
						genres = genres.concat(s).concat(",");
					}

				} else {
					for (int j = 0; j < 6; j++) {
						genres = genres.concat(artist.getGenres()[j]).concat(",");
					}
				}
				if (genres.length() > 0) {
					genres = genres.substring(0, genres.length() - 1);

				}
				music.setmGenre(genres);
				// 가수
				music.setmArtist(artist.getName());
				// 앨범
				music.setmAlbum(t.getAlbum().getName());
				// 미리듣기
				preview = t.getPreviewUrl();
				music.setmPreview(preview);
				// 이미지
				music.setmImg(t.getAlbum().getImages()[0].getUrl());
				// 음악ID
				music.setmId(t.getId());
				// 가수ID
				music.setmArtistId(artist.getId());
				// 앨범ID
				music.setmAlbumId(t.getAlbum().getId());
				// 인기도
				music.setmPopularity(String.valueOf(t.getPopularity()));
				// 발매일
				music.setmDate(t.getAlbum().getReleaseDate());

				if (spotifyService.selectByMId(t.getId()) != null) {
					Music m = spotifyService.selectByMId(t.getId());
					music.setmUrl(m.getmUrl());
					music.setmNo(m.getmNo());
					musics.add(music);
				} else {
					if (youtube.size() >= 10) {
						break;
					}
					System.out.println("youtube api 사용");
					System.out.println(
							"-----------------------------------------------------------------------------------");
					music.setmUrl(t.getName() + " " + artist.getName());
					youtube.add(music);
				}
				System.out.println("리스트에 들어간 뮤직 : " + music.toString());

			}
			ArrayList<Music> result = youtubeService.selectUrlByTitle(youtube);
			for (Music m : result) {
				spotifyService.insertMusic(m);
				m.setmNo(spotifyService.selectByMId(m.getmId()).getmNo());
				musics.add(m);
			}
			System.out.println("리스트 크기 : " + musics.size());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return musics;
	}

	// 가수ID로 정보 가져오기
	@GetMapping("/getartist")
	public Artist getArtist_Sync(String id) {
		GetArtistRequest getArtistRequest = spotifyApi.getArtist(id).build();

		Artist artist = null;
		try {
			artist = getArtistRequest.execute();
			System.out.println(artist.toString());
			for (String s : artist.getGenres()) {
				System.out.println(s);
			}
			System.out.println("Name: " + artist.getName());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return artist;
	}

	// 장르로 검색
	@GetMapping("/genre")
	public List<Music> getRecommendations_Sync(String genre) {
		GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations().limit(10)
				.market(CountryCode.KR)
//	          .max_popularity(50)
				.min_popularity(50)
//	          .seed_genres("k-pop")
				.seed_genres(genre).build();
		Track track = null;
		Artist artist = null;
		List<Music> musics = new ArrayList<Music>();
		try {
			final Recommendations recommendations = getRecommendationsRequest.execute();
			for (int i = 0; i < recommendations.getTracks().length; i++) {
				track = getTrack_Sync(recommendations.getTracks()[i].getId());
				artist = getArtist_Sync(recommendations.getTracks()[i].getArtists()[0].getId());
				// 제목, 장르, 가수, 앨범, 미리듣기, 이미지, 인기도, 음악ID, 가수ID, 앨범ID, 추천횟수
				Music music = new Music();
				// 제목
				music.setmTitle(recommendations.getTracks()[i].getName());
				// 장르
				String genres = "";
				if (artist.getGenres().length < 6) {
					for (String s : artist.getGenres()) {
						genres = genres.concat(s).concat(",");
					}

				} else {
					for (int j = 0; j < 6; j++) {
						genres = genres.concat(artist.getGenres()[j]).concat(",");
					}
				}
				if (genres.length() > 0) {
					genres = genres.substring(0, genres.length() - 1);

				}
				music.setmGenre(genres);
				// 가수
				music.setmArtist(track.getArtists()[0].getName());
				// 앨범
				music.setmAlbum(track.getAlbum().getName());
				// 미리듣기
				music.setmPreview(recommendations.getTracks()[i].getPreviewUrl());
				// 이미지
				music.setmImg(track.getAlbum().getImages()[0].getUrl());
				// 음악ID
				music.setmId(recommendations.getTracks()[i].getId());
				// 가수ID
				music.setmArtistId(recommendations.getTracks()[i].getArtists()[0].getId());
				// 앨범ID
				music.setmAlbumId(track.getAlbum().getId());
				// 인기도
				music.setmPopularity(String.valueOf(track.getPopularity()));
				// 감정
				music.setmEmotion(genre);
				// 발매일
				music.setmDate(track.getAlbum().getReleaseDate());
				String date = track.getAlbum().getReleaseDate().substring(0, 4);
				if (music.getmPreview() != null) { // happy, pop, sad, rock
					if (Integer.parseInt(date) >= 2010) {
						spotifyService.insertMusic(music);
						musics.add(music);

						System.out.println("제목 : " + recommendations.getTracks()[i].getName() + " 가수 : "
								+ recommendations.getTracks()[i].getArtists()[0].getName() + " 미리듣기 : "
								+ recommendations.getTracks()[i].getPreviewUrl() + " 인기도: " + track.getPopularity());

					}
				}
			}
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return musics;
	}

	// 음악 ID로 음악 검색
	@GetMapping("/gettrack")
	public Track getTrack_Sync(String id) {
		GetTrackRequest getTrackRequest = spotifyApi.getTrack(id).build();

		Track track = null;
		try {
			track = getTrackRequest.execute();

			System.out.println("Name: " + track.getName());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return track;
	}
}
