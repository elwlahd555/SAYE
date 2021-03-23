package com.gokchu.saye.music.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Music;

public interface MusicService {

	List<Music> selectByPopularity();

	List<Music> selectByDate();

	List<Music> selectByTitle(String mTitle);

}
