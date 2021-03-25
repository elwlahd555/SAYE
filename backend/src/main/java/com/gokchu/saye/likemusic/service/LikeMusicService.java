package com.gokchu.saye.likemusic.service;

import java.util.List;

import com.gokchu.saye.repository.dto.LikeMusic;
import com.gokchu.saye.repository.dto.Music;

public interface LikeMusicService {

	/* C :: 관심 음원 추가 */
	int insertLikeMusic(LikeMusic likeMusic) throws Exception;

	/* R :: 관심 음원 조회 */
	List<Music> selectLikeMusic(int uNo) throws Exception;

	/* D :: 관심 음원 삭제 */
	boolean deleteLikeMusic(LikeMusic likeMusic) throws Exception;

}