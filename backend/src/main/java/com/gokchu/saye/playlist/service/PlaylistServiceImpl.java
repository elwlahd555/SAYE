package com.gokchu.saye.playlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.Playlist;
import com.gokchu.saye.repository.mapper.PlaylistMapper;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	@Autowired
	private PlaylistMapper playlistMapper;
	
	/* C :: 플레이리스트 생성 */
	@Override
	public int insertPlaylist(Playlist playlist) throws Exception {
		return playlistMapper.insertPlaylist(playlist);
	}
	
	/* R :: 플레이리스트 조회 */
	@Override
	public List<Playlist> reviewPlaylist(int uNo) throws Exception {
		return playlistMapper.selectPlaylist(uNo);
	}
	
	/* U :: 플레이리스트 수정(플레이리스트명 수정) */
	@Override
	public boolean updatePlaylistName(Playlist playlist) throws Exception {
		return playlistMapper.updatePlaylistName(playlist);
	}
	
	/* D :: 플레이리스트 삭제 */
	@Override
	public boolean deletePlaylist(int pNo) throws Exception {
		return playlistMapper.deletePlaylist(pNo);
	}
	
	/* R :: 플레이리스트 조회 (곡눌렀을 때 들어가있는 플레이리스트 확인) */
	@Override
	public List<Playlist> selectPlaylistBelong(Music music) throws Exception {
		return playlistMapper.selectPlaylistBelong(music);
	}
}
