package com.gokchu.saye.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokchu.saye.repository.dto.Board;
import com.gokchu.saye.repository.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/* C :: 게시판 생성 */
	@Override
	public int insertBoard(Board board) throws Exception {
		return boardMapper.insertBoard(board);
	}
	
	/* R :: 게시판 조회 */
	@Override
	public List<Board> selectBoard(String key, String word) throws Exception {
		return boardMapper.selectBoard(key, word);
	}

	/* R :: 게시판 상세 조회 */
	@Override
	public Board selectDetailBoard(int bNo) throws Exception {
		return boardMapper.selectDetailBoard(bNo);
	}
	/* R :: 도움글 리스트 조회 */
	@Override
	public List<Board> selectHelpBoard() throws Exception {
		return boardMapper.selectHelpBoard();
	}
	
	/* R :: 하소연 리스트 조회 */
	@Override
	public List<Board> selectSayBoard() throws Exception {
		return boardMapper.selectSayBoard();
	}
	
	/* U :: 게시판 수정 */
	@Override
	public boolean updateBoard(Board board) throws Exception {
		return boardMapper.updateBoard(board);
	}
	
	/* D :: 게시판 삭제 */
	@Override
	public boolean deleteBoard(int bUNo, int bNo) throws Exception {
		return boardMapper.deleteBoard(bUNo, bNo);
	}
}
