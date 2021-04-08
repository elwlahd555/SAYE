package com.gokchu.saye.music.service;

import com.gokchu.saye.repository.dto.Music;

public interface SpotifyService {
	// 음악 입력
	void insertMusic(Music music);

	// 음악 ID로 음악 검색
	Music selectByMId(String mId);

	// 음악 번호로 url update
	void updateMurlByMid(int mNo, String mUrl);

}
