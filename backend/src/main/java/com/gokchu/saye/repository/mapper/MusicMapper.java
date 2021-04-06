package com.gokchu.saye.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.Music;

@Mapper
public interface MusicMapper {

	List<Music> selectByPopularity();

	List<Music> selectByDate();

	List<Music> selectByTitle(String mTitle);

	List<Music> selectByArtist(String mArtist);

	List<Music> selectByAlbum(String mAlbum);

	List<Music> selectByGenre(String mGenre);

	List<Music> selectByEmotion(String mEmotion);


	String temp(int i);
//
//	void update(int i, String artist);

	void update(Music music);

	String artist(int i);

	Music selectByNo(int mNo);

	void updateMurlByNo(int mNo, String mUrl);

	void updateCnt(int mNo);



}
