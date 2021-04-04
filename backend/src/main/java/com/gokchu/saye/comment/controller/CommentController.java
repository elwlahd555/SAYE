package com.gokchu.saye.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gokchu.saye.comment.service.CommentService;
import com.gokchu.saye.repository.dto.Comment;

@RestController
@RequestMapping("/board/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	/* C :: 댓글 생성 */
	@PostMapping("/add")
	public ResponseEntity<String> createComment(@RequestBody Comment comment) throws Exception {
		if(commentService.insertComment(comment) > 0)
			return new ResponseEntity<String>("댓글 추가 성공", HttpStatus.OK);
		return new ResponseEntity<String>("댓글 추가 실패", HttpStatus.NO_CONTENT);
	}
	
	/* R :: 댓글 조회 */
	@GetMapping("")
	public ResponseEntity<List<Comment>> reviewComment(int cBNo) throws Exception {
		return new ResponseEntity<List<Comment>>(commentService.selectComment(cBNo), HttpStatus.OK);
	}

	/* U :: 댓글 수정 */
	@PutMapping("/edit")
	public ResponseEntity<String> updateComment(Comment comment) throws Exception {
		if(commentService.updateComment(comment))
			return new ResponseEntity<String>("댓글 수정 성공", HttpStatus.OK);
		return new ResponseEntity<String>("댓글 수정 실패", HttpStatus.NO_CONTENT);
	}
	
	/* D :: 댓글 삭제 */
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteComment(int cUNo, int cNo, int cBNo) throws Exception {
		if(commentService.deleteComment(cUNo, cNo, cBNo))
			return new ResponseEntity<String>("댓글 삭제 성공", HttpStatus.OK);
		return new ResponseEntity<String>("댓글 삭제 실패", HttpStatus.NO_CONTENT);
	}
}
