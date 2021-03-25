package com.gokchu.saye.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.PlaylistMusic;

@Mapper
public interface PlaylistMusicMapper {
	/* C :: 플레이리스트 안에 노래 추가 */
	public int insertPlaylistMusic(int mNo, int pNo) throws Exception;
	
	/* R :: 플레이리스트 내 곡 조회 (플레이리스트 상세 조회) */
	public List<Music> selectPlaylistMusic(int pNo) throws Exception;
	
	/* D :: 플레이리스트 내 곡 삭제 */
	public boolean deletePlaylistMusic(PlaylistMusic music) throws Exception;
}
