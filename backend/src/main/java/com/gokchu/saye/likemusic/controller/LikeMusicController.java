package com.gokchu.saye.likemusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.likemusic.service.LikeMusicService;
import com.gokchu.saye.repository.dto.LikeMusic;
import com.gokchu.saye.repository.dto.Music;


@RestController
public class LikeMusicController {
	
	@Autowired
	private LikeMusicService likeMusicService;
	
	/* C :: 관심 음원 등록 */
	@PostMapping("/likemusic/add")
	public ResponseEntity<String> createLikeMusic(LikeMusic likeMusic) throws Exception {
		System.out.println("찜한사람고유No" + likeMusic.getLmUNo() + ", 음원고유No : " + likeMusic.getLmMNo());
		if(likeMusicService.insertLikeMusic(likeMusic) > 0) {
			return new ResponseEntity<String>("관심 음원 등록 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("관심 음원 등록 실패", HttpStatus.NO_CONTENT);
	}
	
	/* R :: 관심 음원 조회 */
	@GetMapping("/playlist/likemusic")
	public ResponseEntity<List<Music>> reviewLikeMusic(int uNo) throws Exception {
		return new ResponseEntity<List<Music>> (likeMusicService.selectLikeMusic(uNo), HttpStatus.OK);
	}
	
	/* R :: 유저의 관심 음원과 유사한 음악들 분석 위한 음원1개 추출 */
	@PostMapping("/likemusic/likeone")
	public ResponseEntity<Music> selectOneLikeMusic(@RequestBody int uNo) throws Exception {
		return new ResponseEntity<Music> (likeMusicService.selectOneLikeMusic(uNo), HttpStatus.OK);
	}
	
	/* D :: 관심 음원 삭제 */
	@DeleteMapping("/likemusic/delete")
	public ResponseEntity<String> deleteLikeMusic(LikeMusic likeMusic) throws Exception {
		System.out.println("님의 플레이리스트");
		if(likeMusicService.deleteLikeMusic(likeMusic)) {
			return new ResponseEntity<String>("관심 음원 삭제 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("관심 음원 삭제 실패", HttpStatus.NO_CONTENT);
	}	
}
