package com.gokchu.saye.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gokchu.saye.repository.dto.Comment;

@Mapper
public interface CommentMapper {
	/* C :: 댓글 생성 */
	int insertComment(Comment comment) throws Exception;
	
	/* R :: 댓글 조회 */
	List<Comment> selectComment(int cBNo) throws Exception;
	
	/* U :: 댓글 수정 */
	boolean updateComment(Comment comment) throws Exception;
	
	/* D :: 댓글 삭제 */
	boolean deleteComment(int cUNo, int cNo, int cBNo) throws Exception;
}
