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
		return musicMapper.selectByDate();
	}

	@Override
	public List<Music> selectByTitle(String mTitle) {
		return musicMapper.selectByTitle(mTitle);
	}

	@Override
	public List<Music> selectByArtist(String mArtist) {
		return musicMapper.selectByArtist(mArtist);
	}

	@Override
	public List<Music> selectByAlbum(String mAlbum) {
		return musicMapper.selectByAlbum(mAlbum);
	}

	@Override
	public List<Music> selectByGenre(String mGenre) {
		return musicMapper.selectByGenre(mGenre);
	}

	@Override
	public List<Music> selectByEmotion(String mEmotion) {
		return musicMapper.selectByEmotion(mEmotion);
	}

	@Override
	public String artist(int i) {
		return musicMapper.artist(i);
	}

	@Override
	public Music selectByNo(int mNo) {
		return musicMapper.selectByNo(mNo);
	}

	@Override
	public void updateMurlByNo(int mNo, String mUrl) {
		musicMapper.updateMurlByNo(mNo, mUrl);

	}

	@Override
	public void updateCnt(int mNo) {
		musicMapper.updateCnt(mNo);
	}

	@Override
	public List<Music> selectBymCnt() {
		return musicMapper.selectBymCnt();
	}

	@Override
	public List<Music> selectByrandGenre(String mGenre) {
		return musicMapper.selectByrandGenre(mGenre);
	}
}
