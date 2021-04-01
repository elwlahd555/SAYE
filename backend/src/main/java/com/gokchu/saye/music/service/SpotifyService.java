package com.gokchu.saye.music.service;

import com.gokchu.saye.repository.dto.Music;

public interface SpotifyService {

	void insertMusic(Music music);

	Music selectByMId(String mId);
	

}
