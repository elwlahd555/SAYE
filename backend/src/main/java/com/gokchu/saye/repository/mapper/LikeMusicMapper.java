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
		
	/* R :: 유저의 관심 음원과 유사한 음악들 분석 위한 음원1개 추출 */
	Music selectOneLikeMusic(int uNo) throws Exception;
	
	/* R :: 콜드스타트 위한 랜덤 음원 1개 추출 */
	Music selectRandomMusic() throws Exception;
	
	/* D :: 관심 음원 삭제 */
	boolean deleteLikeMusic(LikeMusic likeMusic) throws Exception;
}
