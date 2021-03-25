package com.gokchu.saye.playlist.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Playlist;

public interface PlaylistService {

	/* C :: 플레이리스트 생성 */
	int insertPlaylist(Playlist playlist) throws Exception;

	/* R :: 플레이리스트 조회 */
	List<Playlist> reviewPlaylist(int uNo) throws Exception;

	/* U :: 플레이리스트 수정(플레이리스트명 수정) */
	boolean updatePlaylistName(Playlist playlist) throws Exception;

	/* D :: 플레이리스트 삭제 */
	boolean deletePlaylist(int pNo) throws Exception;

}