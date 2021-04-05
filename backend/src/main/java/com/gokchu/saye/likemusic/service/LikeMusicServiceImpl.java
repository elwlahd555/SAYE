package com.gokchu.saye.likemusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.LikeMusic;
import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.mapper.LikeMusicMapper;

@Service
public class LikeMusicServiceImpl implements LikeMusicService {

	@Autowired
	private LikeMusicMapper likeMusicMapper;
	
	/* C :: 관심 음원 추가 */
	@Override
	public int insertLikeMusic(LikeMusic likeMusic) throws Exception {
		return likeMusicMapper.insertLikeMusic(likeMusic);
	}
	
	/* R :: 관심 음원 조회 */
	@Override
	public List<Music> selectLikeMusic(int uNo) throws Exception {
		return likeMusicMapper.selectLikeMusic(uNo);
	}
	
	/* R :: 유저의 관심 음원과 유사한 음악들 분석 위한 음원1개 추출 */
	@Override
	public Music selectOneLikeMusic(int uNo) throws Exception {
		return likeMusicMapper.selectOneLikeMusic(uNo);
	}
	
	/* D :: 관심 음원 삭제 */
	@Override
	public boolean deleteLikeMusic(LikeMusic likeMusic) throws Exception {
		return likeMusicMapper.deleteLikeMusic(likeMusic);
	}
}
