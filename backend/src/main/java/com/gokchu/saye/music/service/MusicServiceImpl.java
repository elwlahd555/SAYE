package com.gokchu.saye.music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.mapper.MusicMapper;

@Service
public class MusicServiceImpl implements MusicService {

	@Autowired
	MusicMapper musicMapper;
	@Override
	public List<Music> selectByPopularity() {
		
		return musicMapper.selectByPopularity();
	}
	@Override
	public List<Music> selectByDate() {
		// TODO Auto-generated method stub
		return musicMapper.selectByDate();
	}
	@Override
	public List<Music> selectByTitle(String mTitle) {
		// TODO Auto-generated method stub
		return musicMapper.selectByTitle(mTitle);
	}



}
