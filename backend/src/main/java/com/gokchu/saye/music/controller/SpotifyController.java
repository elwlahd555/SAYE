package com.gokchu.saye.music.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.music.service.SpotifyService;
import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.SpotifyAccesstoken;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.AudioFeatures;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

@RestController
@RequestMapping("/spotify")
public class SpotifyController {

	@Autowired
	private SpotifyService spotifyService;

	SpotifyApi spotifyApi = SpotifyAccesstoken.getSpotifyapi();

	@GetMapping("/accesstoken")
	public void accesstoken() {
		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		try {
			final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
			// Set access token for further "spotifyApi" object usage
			spotifyApi.setAccessToken(clientCredentials.getAccessToken());

			System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@GetMapping("/searchartist")
	public ArtistSimplified searchTracks_Sync(String q) {
		SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
				.market(CountryCode.US)
		          .limit(10)
//		          .offset(0)
//		          .includeExternal("audio")
				.build();
		String preview = "";
		Track track=null;
		ArtistSimplified artist=null;
		try {
			final Paging<Track> trackPaging = searchTracksRequest.execute();

//			artist=trackPaging.getItems()[0];
//			System.out.println("Total: " + trackPaging.getTotal());
			track=trackPaging.getItems()[0];
			System.out.println(trackPaging.getItems()[0].toString());
			System.out.println("제목 : "+track.getName());
			System.out.println("가수 : "+track.getArtists()[0].getName());
			artist=trackPaging.getItems()[0].getArtists()[0];
			preview = trackPaging.getItems()[0].getPreviewUrl();
//			String temp= trackPaging.getItems()[0].getAlbum().get
			System.out.println(preview);
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return artist;
	}

	@GetMapping("/getartist")
	public Artist getArtist_Sync(String id) {
		GetArtistRequest getArtistRequest = spotifyApi.getArtist(id).build();

		Artist artist=null;
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

	@GetMapping("/genre")
	public List<Music> getRecommendations_Sync(String genre) {
		GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
	          .limit(100)
			.market(CountryCode.KR)
//	          .max_popularity(50)
	          .min_popularity(50)
//	          .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
//	          .seed_genres("k-pop")
				.seed_genres(genre)
//	          .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
//	          .target_popularity(20)
				.build();

		Track track=null;
		Artist artist=null;
//		AudioFeatures audioFeatures=null;
		List<Music>musics=new ArrayList<Music>();
		try {
			final Recommendations recommendations = getRecommendationsRequest.execute();

//			System.out.println("Length: " + recommendations.getTracks().length);
//			System.out.println(recommendations.toString());
			for (int i = 0; i < recommendations.getTracks().length; i++) {
//				GetAudioFeaturesForTrackRequest getAudioFeaturesForTrackRequest = spotifyApi
//					    .getAudioFeaturesForTrack(recommendations.getTracks()[i].getId())
//					    .build();
//				CompletableFuture<AudioFeatures> audioFeaturesFuture = getAudioFeaturesForTrackRequest.executeAsync();
//				audioFeatures=audioFeaturesFuture.join();
				track=getTrack_Sync(recommendations.getTracks()[i].getId());
				artist=getArtist_Sync(recommendations.getTracks()[i].getArtists()[0].getId());
				//제목, 장르, 가수, 앨범, 미리듣기, 이미지, 인기도, 음악ID, 가수ID, 앨범ID, 추천횟수
				Music music =new Music();
				//제목
				music.setmTitle(recommendations.getTracks()[i].getName());
				//장르
				String genres="";
				if(artist.getGenres().length<6) {
					for (String s : artist.getGenres()) {
						genres=genres.concat(s).concat(",");
					}
					
				}else {
					for (int j = 0; j < 6; j++) {
						genres=genres.concat(artist.getGenres()[j]).concat(",");
					}
				}
				if(genres.length()>0) {
					genres=genres.substring(0, genres.length()-1);
					
				}
				music.setmGenre(genres);
				//가수
				music.setmArtist(track.getArtists()[0].getName());
				//앨범
				music.setmAlbum(track.getAlbum().getName());
				//미리듣기
				music.setmPreview(recommendations.getTracks()[i].getPreviewUrl());
				//이미지
				music.setmImg(track.getAlbum().getImages()[0].getUrl());
				//음악ID
				music.setmId(recommendations.getTracks()[i].getId());
				//가수ID
				music.setmArtistId(recommendations.getTracks()[i].getArtists()[0].getId());
				//앨범ID
				music.setmAlbumId(track.getAlbum().getId());
				//인기도
				music.setmPopularity(String.valueOf(track.getPopularity()));
				//감정
				music.setmEmotion(genre);
				//발매일
				music.setmDate(track.getAlbum().getReleaseDate());
				String date=track.getAlbum().getReleaseDate().substring(0, 4);
//				System.out.println(date);
//				System.out.println("트랙정보"+track.toString());
				if(music.getmPreview()!=null) {	//happy, pop, sad, rock
					if(Integer.parseInt(date)>=2010) {
						spotifyService.insertMusic(music);
						musics.add(music);
						
						System.out.println("제목 : " + recommendations.getTracks()[i].getName() + " 가수 : "
								+ recommendations.getTracks()[i].getArtists()[0].getName() + " 미리듣기 : "
								+ recommendations.getTracks()[i].getPreviewUrl() + " 인기도: "
								+ track.getPopularity());
						
					}
				}
//				System.out.println("발매일 : "+track.getAlbum().getReleaseDate());
			}
		} catch (IOException | SpotifyWebApiException | ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return musics;
	}

	@GetMapping("/gettrack")
	public Track getTrack_Sync(String id) {
		GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
//		          .market(CountryCode.SE)
				.build();

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
