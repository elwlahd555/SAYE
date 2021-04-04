package com.gokchu.saye.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.mapper.SpotifyMapper;

@Service
public class SpotifyServiceImpl implements SpotifyService{
	
	@Autowired
	private SpotifyMapper spotifyMapper; 

	@Override
	public void insertMusic(Music music) {
		if(spotifyMapper.checkMusic(music)>0) {
			spotifyMapper.updateMusicCnt(music.getmId());
		}else {
			spotifyMapper.insertMusic(music);
		}
		
	}

	@Override
	public Music selectByMId(String mId) {
		// TODO Auto-generated method stub
		return spotifyMapper.selectByMId(mId);
	}

	@Override
	public void updateMurlByMid(int mNo, String mUrl) {
		// TODO Auto-generated method stub
		spotifyMapper.updateMurlByMid(mNo,mUrl);
	}

}
