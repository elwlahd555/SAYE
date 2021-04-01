package com.gokchu.saye.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.music.service.MusicService;
import com.gokchu.saye.playlist.service.PlaylistService;
import com.gokchu.saye.repository.dto.Playlist;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private MusicService musicService;
	
	/* C :: 플레이리스트 생성 */
	@PostMapping("/add")
	public ResponseEntity<String> createPlaylist(Playlist Playlist) throws Exception {
		if(playlistService.insertPlaylist(Playlist) > 0)
			return new ResponseEntity<String>("플레이리스트 추가 성공", HttpStatus.OK);
		return new ResponseEntity<String>("플레이리스트 추가 실패", HttpStatus.NO_CONTENT);
	}
	
	/* R :: 유저 모든 플레이리스트 조회 */
	@GetMapping("")
	public ResponseEntity<List<Playlist>> reviewPlayList(int uNo) throws Exception {
		return new ResponseEntity<List<Playlist>>(playlistService.reviewPlaylist(uNo), HttpStatus.OK);
	}
	
	/* U :: 플레이리스트 수정 (플레이리스트명 수정) */
	@PutMapping("/edit")
	public ResponseEntity<String> updatePlayListName(Playlist playlist) throws Exception {
		System.out.println("수정할 플레이리스트명 : " + playlist.getpName());
		if(playlistService.updatePlaylistName(playlist)) {
			return new ResponseEntity<String>("플레이리스트명 수정 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("플레이리스트명 수정 실패", HttpStatus.NO_CONTENT);
	}

	/* D :: 플레이리스트 삭제 */
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePlaylist(int pNo)  throws Exception {
		if(playlistService.deletePlaylist(pNo)) {
			return new ResponseEntity<String>("플레이리스트 삭제 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("플레이리스트 삭제 실패", HttpStatus.NO_CONTENT);
	}
	

}
