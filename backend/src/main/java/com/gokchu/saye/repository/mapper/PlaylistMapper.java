package com.gokchu.saye.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gokchu.saye.repository.dto.Playlist;

@Mapper
public interface PlaylistMapper {
	/* C :: 플레이리스트 생성 */
	int insertPlaylist(Playlist playlist) throws Exception;
	
	/* R :: 플레이리스트 조회 */
	List<Playlist> selectPlaylist(int pUNo) throws Exception;
	
	/* U :: 플레이리스트명 수정 */
	boolean updatePlaylistName(Playlist playlist) throws Exception;
	
	/* D :: 플레이리스트 삭제 */
	boolean deletePlaylist(int pNo) throws Exception;
}
