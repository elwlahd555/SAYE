package com.gokchu.saye.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Comment;
import com.gokchu.saye.repository.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	/* C :: 댓글 생성 */
	@Override
	public int insertComment(Comment comment) throws Exception {
		return commentMapper.insertComment(comment);
	}
	
	/* R :: 댓글 조회 */
	@Override
	public List<Comment> selectComment(int cBNo) throws Exception {
		return commentMapper.selectComment(cBNo);
	}
	
	/* U :: 댓글 수정 */
	@Override
	public boolean updateComment(Comment comment) throws Exception {
		return commentMapper.updateComment(comment);
	}
	
	/* D :: 댓글 삭제 */
	@Override
	public boolean deleteComment(int cUNo, int cNo, int cBNo) throws Exception {
		return commentMapper.deleteComment(cUNo, cNo, cBNo);
	}
}
