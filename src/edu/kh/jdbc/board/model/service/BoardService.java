package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.vo.Board;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	public List<Board> selectAllBoard() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectAllBoard(conn);
		
		close(conn);
		
		return boardList;
	}

	public Board selectBoard(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(conn, boardNo);
		
		close(conn);
		
		return board;
	}


}
