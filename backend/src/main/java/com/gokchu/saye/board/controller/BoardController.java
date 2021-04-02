package com.gokchu.saye.board.controller;

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

import com.gokchu.saye.board.service.BoardService;
import com.gokchu.saye.repository.dto.Board;

@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/* C :: 익명 게시판 글 작성 */
	@PostMapping("/add")
	public ResponseEntity<String> createBoard(Board board) throws Exception {
		if(boardService.insertBoard(board) > 0) 
			return new ResponseEntity<String>("게시글 작성 성공", HttpStatus.OK);
		return new ResponseEntity<String>("게시글 작성 실패", HttpStatus.NO_CONTENT);
	}
	
	/* R :: 익명 게시판 리스트 조회 */
	@GetMapping("")
	public ResponseEntity<List<Board>> reviewBoard(String key, String word) throws Exception {
		return new ResponseEntity<List<Board>>(boardService.selectBoard(key, word), HttpStatus.OK);
	}
	
	/* U :: 익명 게시판 글 수정 */
	@PutMapping("/edit")
	public ResponseEntity<String> updateBoard(Board board) throws Exception {
		if(boardService.updateBoard(board))
			return new ResponseEntity<String>("게시판 수정 성공", HttpStatus.OK);
		return new ResponseEntity<String>("게시판 수저 실패", HttpStatus.NO_CONTENT);
	}
	
	/* D :: 익명 게시판 글 삭제 */
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBoard(int bUNo, int bNo) throws Exception {
		if(boardService.deleteBoard(bUNo, bNo))
			return new ResponseEntity<String>("게시판 삭제 성공", HttpStatus.OK);
		return new ResponseEntity<String>("게시판 삭제 실패", HttpStatus.NO_CONTENT);
		
	}
}
