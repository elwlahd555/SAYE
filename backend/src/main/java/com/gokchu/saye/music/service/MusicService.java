package com.gokchu.saye.music.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Music;

public interface MusicService {

	List<Music> selectByPopularity();

	List<Music> selectByDate();

	List<Music> selectByTitle(String mTitle);

	List<Music> selectByArtist(String mArtist);

	List<Music> selectByAlbum(String mAlbum);

	List<Music> selectByGenre(String mGenre);

	List<Music> selectByEmotion(String mEmotion);

//	String temp(int i);
//
//	void update(Music music);

	String artist(int i);

	Music selectByNo(int mNo);

	void updateMurlByNo(int mNo, String mUrl);

//	String temp(int i);
//
//	void update(int i, String artist);

}
