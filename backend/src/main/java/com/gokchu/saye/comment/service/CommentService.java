package com.gokchu.saye.comment.service;

import java.util.List;

import com.gokchu.saye.repository.dto.Comment;

public interface CommentService {

	/* C :: 댓글 생성 */
	int insertComment(Comment comment) throws Exception;

	/* R :: 댓글 조회 */
	List<Comment> selectComment(int cBNo) throws Exception;

	/* U :: 댓글 수정 */
	boolean updateComment(Comment comment) throws Exception;

	/* D :: 댓글 삭제 */
	boolean deleteComment(int cUNo, int cNo, int cBNo) throws Exception;

}