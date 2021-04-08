package com.gokchu.saye.music.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Music;

public interface MusicService {
	// 인기순 음악 검색
	List<Music> selectByPopularity();

	// 발매일순 을악 검색
	List<Music> selectByDate();

	// 제목으로 음악 검색
	List<Music> selectByTitle(String mTitle);

	// 가수로 음악 검색
	List<Music> selectByArtist(String mArtist);

	// 앨범으로 음악 검색
	List<Music> selectByAlbum(String mAlbum);

	// 장르로 음악 검색
	List<Music> selectByGenre(String mGenre);

	// 감정으로 음악 검색
	List<Music> selectByEmotion(String mEmotion);

	// m_no로 가수 검색
	String artist(int i);

	// m_no로 음악 검색
	Music selectByNo(int mNo);

	// Youtube url update
	void updateMurlByNo(int mNo, String mUrl);

	// 음악 실행 횟수 update
	void updateCnt(int mNo);

	// 음악 실행 횟수순 검색
	List<Music> selectBymCnt();

	// 장르기준 랜덤 뮤직 검색
	List<Music> selectByrandGenre(String mGenre);
}
