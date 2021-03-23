package com.gokchu.saye.music.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.music.service.MusicService;
import com.gokchu.saye.repository.dto.Music;

@RestController
@RequestMapping("/music")
public class MusicController {
	
	@Autowired
	MusicService musicService;
	
	//인기도 순서로 출력
	@GetMapping("popularity")
	public List<Music> selectByPopularity(){
		List<Music>musics=new ArrayList<Music>();
		musics=musicService.selectByPopularity();
		return musics;
	}
	
	//발매일 순서로 출력
	@GetMapping("date")
	public List<Music> selectByDate(){
		List<Music>musics=new ArrayList<Music>();
		musics=musicService.selectByDate();
		return musics;
	}
	
	//제목으로 검색
	@GetMapping("title")
	public List<Music> selectByTitle(String mTitle){
		List<Music>musics=new ArrayList<Music>();
		musics=musicService.selectByTitle(mTitle);
		return musics;
	}
	//가수로 검색
	
	//앨범으로 검색
	
	
}
