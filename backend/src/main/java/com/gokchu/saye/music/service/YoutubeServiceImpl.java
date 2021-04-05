package com.gokchu.saye.music.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	/** Global instance properties filename. */
	private static String PROPERTIES_FILENAME = "youtube.properties";

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/**
	 * Global instance of the max number of videos we want returned (50 = upper
	 * limit per page).
	 */
	private static final long NUMBER_OF_VIDEOS_RETURNED = 1;

	/** Global instance of Youtube object to make all API requests. */
	private static YouTube youtube;

	/**
	 * Initializes YouTube object to search for videos on YouTube
	 * (Youtube.Search.List). The program then prints the names and thumbnails of
	 * each of the videos (only first 50 videos).
	 *
	 * @param args command line args.
	 * @throws IOException
	 */
	@Override
	public String selectUrlByTitle(String mTitle) {
		String musicurl = "";
		String queryTerm = mTitle;
		String keyarr[] = { 
				"AIzaSyCMFu4lu9O7_3VZp6i5jK_aMtDO5yor4-Q",	//0
				"AIzaSyDzZlEup7tbpw-uO9qhnZ4Wnw0X7RpOONw",	//2
				"AIzaSyAfDT808_UJPcTbcwh829JSTtwjUqFFTe8",	//3
				"AIzaSyA-LrKGyMG44jgu5kfjp3CbmsMedfI6eQM",	//4
				"AIzaSyATgOWL72U2SjOy111YP65nzP2sLB4zKXs",	//youtube api
				"AIzaSyBmK9-jEmCYXotNsa7NagyosJ-GdpPFAG8",	//music
				"AIzaSyCsJKJqBYxsRjtSPIiagx964wIUZm7CDZg",	//saye
				"AIzaSyBtRrPWEDBlfZyWJ-LYc-oGt5b6wlLrRv0",	//saye music
				"AIzaSyBBIOQooUnpH2cQUAZkcNtbj36ytKoZltU",	//DB My First Project
				"AIzaSyCjFy1QekD1oyKswUOTENpBUQDldlSdk3I",	//DB letmeet
				"AIzaSyCNQXnos1sSPd4S0cRAd2XjNiVIWjnsUPU",	//DB FAQ-Bot
				"AIzaSyC1CCGExB6cVzDTpPCRlKi-FGnhFoGFUaY",	//DB My Project
				"AIzaSyA1-l1EvRjeO7jI8calsFNcUUFMu3C0-7w"	//DB FixOrder-BOT
				};
//		int num = (int) (Math.random() * keyarr.length);
		for (int i = 0; i < keyarr.length; i++) {
			System.out.println("사용된 키값 : " + keyarr[i]);
			try {
				
				youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
					public void initialize(HttpRequest request) throws IOException {
					}
				}).setApplicationName("youtube-cmdline-search-sample").build();
				SearchListResponse searchResponse = null;
				
				YouTube.Search.List search = youtube.search().list("id,snippet");
				search.setKey(keyarr[i]);
				search.setQ(queryTerm);
				search.setType("video");
				search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
				search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
				searchResponse = search.execute();
				
				List<SearchResult> searchResultList = searchResponse.getItems();
				
				if (searchResultList != null) {
					musicurl = prettyPrint(searchResultList.iterator(), queryTerm);
					
				}
			} catch (GoogleJsonResponseException e) {
				System.err.println(
						"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
				continue;
			} catch (IOException e) {
				System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
				break;
			} catch (Throwable t) {
				t.printStackTrace();
				break;
			}
			break;
		}
		return musicurl;
	}

	private static String prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

		System.out.println("\n=============================================================");
		System.out.println("   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
		System.out.println("=============================================================\n");

		String musicurl = "";
		if (!iteratorSearchResults.hasNext()) {
			System.out.println(" There aren't any results for your query.");
		}

		while (iteratorSearchResults.hasNext()) {

			SearchResult singleVideo = iteratorSearchResults.next();
			ResourceId rId = singleVideo.getId();
			if (rId.getKind().equals("youtube#video")) {
				Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
				musicurl = rId.getVideoId();
				System.out.println(" Video Id" + musicurl);
				System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
				System.out.println(" Thumbnail: " + thumbnail.getUrl());
				System.out.println("\n-------------------------------------------------------------\n");
			}
		}
		return musicurl;
	}

}