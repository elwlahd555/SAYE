package com.gokchu.saye.playlistmusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.PlaylistMusic;
import com.gokchu.saye.repository.mapper.PlaylistMusicMapper;

@Service
public class PlaylistMusicServiceImpl implements PlaylistMusicService {
	
	@Autowired
	private PlaylistMusicMapper playlistMusicMapper;
	
	/* C :: 플레이리스트 안에 노래 추가 */
	@Override
	public int insertPlaylistMusic(int pNo, int mNo) throws Exception {
		return playlistMusicMapper.insertPlaylistMusic(pNo, mNo);
	}
	
	/* R :: 플레이리스트 내 곡 조회 (플레이리스트 상세 조회) */
	@Override
	public List<Music> selectPlaylistMusic(int pNo) throws Exception {
		return playlistMusicMapper.selectPlaylistMusic(pNo);
	}
	
	/* D :: 플레이리스트 내 곡 삭제 */
	@Override
	public boolean deletePlaylistMusic(PlaylistMusic music) throws Exception {
		return playlistMusicMapper.deletePlaylistMusic(music);
	}
}
