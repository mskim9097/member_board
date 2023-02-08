package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.CommentDAO;
import edu.kh.jdbc.board.model.vo.Comment;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class CommentService {
	
	private CommentDAO dao = new CommentDAO();

	public List<Comment> selectAllComment(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Comment> commentList = dao.selectAllComment(conn, boardNo);
		
		close(conn);
		
		return commentList;
	}

}
