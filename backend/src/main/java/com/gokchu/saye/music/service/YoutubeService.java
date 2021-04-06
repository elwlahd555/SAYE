package com.gokchu.saye.music.service;

import java.util.ArrayList;

import com.gokchu.saye.repository.dto.Music;

public interface YoutubeService {
	
	ArrayList<Music> selectUrlByTitle(ArrayList<Music> youtube);

}
