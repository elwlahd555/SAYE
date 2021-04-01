package com.gokchu.saye.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.LikeMusic;
import com.gokchu.saye.repository.dto.Music;

@Mapper
public interface LikeMusicMapper {
	/* C :: 관심 음원 등록 */
	int insertLikeMusic(LikeMusic likeMusic) throws Exception;
	
	/* R :: 관심 음원 조회 */
	List<Music> selectLikeMusic(int uNo) throws Exception;
	
	/* D :: 관심 음원 삭제 */
	boolean deleteLikeMusic(LikeMusic likeMusic) throws Exception;
}
