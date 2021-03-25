package com.gokchu.saye.playlistmusic.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.PlaylistMusic;

public interface PlaylistMusicService {

	/* C :: 플레이리스트 안에 노래 추가 */
	int insertPlaylistMusic(int music, int playlist) throws Exception;

	/* R :: 플레이리스트 내 곡 조회 (플레이리스트 상세 조회) */
	List<Music> selectPlaylistMusic(int pNo) throws Exception;

	/* D :: 플레이리스트 내 곡 삭제 */
	boolean deletePlaylistMusic(PlaylistMusic music) throws Exception;

}