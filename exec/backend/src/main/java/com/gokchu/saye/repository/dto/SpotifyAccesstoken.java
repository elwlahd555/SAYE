package com.gokchu.saye.repository.dto;

import com.wrapper.spotify.SpotifyApi;

public class SpotifyAccesstoken {
	
	private static final String clientId = "d3334c83c5164e88be121b0c08f6a9c8";
	private static final String clientSecret = "c6b5fa6175284908ba736881e879d60f";
	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId)
			.setClientSecret(clientSecret).build();
	public static SpotifyApi getSpotifyapi() {
		return spotifyApi;
	}

}
