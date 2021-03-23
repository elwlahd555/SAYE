package com.gokchu.saye.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.Music;

@Mapper
public interface SpotifyMapper {

	
	//음악등록
	int insertMusic(Music music);
	//음악유무체크
	int checkMusic(Music music);
	//검색횟수
	int updateMusicCnt(String mId);

}
