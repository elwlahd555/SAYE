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
//	@Override
//	public String temp(int i) {
//		// TODO Auto-generated method stub
//		return musicMapper.temp(i);
//	}
//	@Override
//	public void update(int i, String artist) {
//		// TODO Auto-generated method stub
//		musicMapper.update(i,artist);
//	}
	@Override
	public List<Music> selectByArtist(String mArtist) {
		// TODO Auto-generated method stub
		return musicMapper.selectByArtist(mArtist);
	}
	@Override
	public List<Music> selectByAlbum(String mAlbum) {
		// TODO Auto-generated method stub
		return musicMapper.selectByAlbum(mAlbum);
	}
	@Override
	public List<Music> selectByGenre(String mGenre) {
		// TODO Auto-generated method stub
		return musicMapper.selectByGenre(mGenre);
	}
	@Override
	public List<Music> selectByEmotion(String mEmotion) {
		// TODO Auto-generated method stub
		return musicMapper.selectByEmotion(mEmotion);
	}
//	@Override
//	public String temp(int i) {
//		// TODO Auto-generated method stub
//		return musicMapper.temp(i);
//	}
//	@Override
//	public void update(Music music) {
//		// TODO Auto-generated method stub
//		musicMapper.update(music);
//		
//	}
	@Override
	public String artist(int i) {
		// TODO Auto-generated method stub
		return musicMapper.artist(i);
	}
	@Override
	public Music selectByNo(int mNo) {
		// TODO Auto-generated method stub
		return musicMapper.selectByNo(mNo);
	}
	@Override
	public void updateMurlByNo(int mNo, String mUrl) {
		// TODO Auto-generated method stub
		musicMapper.updateMurlByNo(mNo,mUrl);
		
	}



}
