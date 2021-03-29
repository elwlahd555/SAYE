package com.gokchu.saye.music.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Music;

public interface MusicService {

	List<Music> selectByPopularity();

	List<Music> selectByDate();

	List<Music> selectByTitle(String mTitle);

//	String temp(int i);
//
//	void update(int i, String artist);

}
