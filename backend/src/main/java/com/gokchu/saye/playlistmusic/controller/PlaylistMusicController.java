package com.gokchu.saye.playlistmusic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.music.service.MusicService;
import com.gokchu.saye.playlist.service.PlaylistService;
import com.gokchu.saye.playlistmusic.service.PlaylistMusicService;
import com.gokchu.saye.repository.dto.LikeMusic;
import com.gokchu.saye.repository.dto.Music;
import com.gokchu.saye.repository.dto.Playlist;
import com.gokchu.saye.repository.dto.PlaylistMusic;

@RestController
@RequestMapping("")
public class PlaylistMusicController {
	
	@Autowired
	private MusicService musicService;

	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private PlaylistMusicService playlistMusicService;
	
	/* C :: 플레이리스트 내 곡 추가 */
	@PostMapping("/add")
	private ResponseEntity<String> createPlaylistMusic(int pNo, int mNo) throws Exception {
		if(playlistMusicService.insertPlaylistMusic(pNo, mNo) > 0) {
			return new ResponseEntity<String>("플레이리스트 내 곡 추가 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("플레이리스트 내 곡 추가 실패", HttpStatus.NO_CONTENT);
			
	}
	
	/* R :: 플레이리스트 내 곡 조회 (플레이리스트 상세 조회) */
	@GetMapping("")
	private ResponseEntity<List<Music>> reviewPlayListMusic(int pNo) throws Exception {
		return new ResponseEntity<List<Music>>(playlistMusicService.selectPlaylistMusic(pNo), HttpStatus.OK);
	}
	
	/* D :: 플레이리스트 내 곡 삭제 */
	@DeleteMapping("/delete")
	private ResponseEntity<String> deletePlayListMusic(PlaylistMusic music) throws Exception {
		System.out.println("삭제할 음원 고유번호 : " + music.getPmMNo());
		if(playlistMusicService.deletePlaylistMusic(music))
			return new ResponseEntity<String>("플레이리스트 내 음원 삭제 성공", HttpStatus.OK);
		return new ResponseEntity<String>("플레이리스트 내 음원 삭제 실패", HttpStatus.NO_CONTENT);
	}
}
