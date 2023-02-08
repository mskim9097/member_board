package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public BoardDAO() {
		
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("board-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public List<Board> selectAllBoard(Connection conn) throws Exception{
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				String createDate = rs.getString("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				
				Board board = new Board(boardNo, 
						boardTitle, memberName, createDate, readCount);
				
				boardList.add(board);

				
			}
		} finally {
			rs.close();
			stmt.close();
		}
		
		return boardList;
	}



	public Board selectBoard(Connection conn, int boardNo) throws Exception{
		
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board(boardNo, rs.getString("BOARD_TITLE"),
						rs.getString("BOARD_CONTENT"), rs.getString("MEMBER_NM"),
						rs.getString("CREATE_DT"), rs.getInt("READ_COUNT"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}



	public List<Integer> commentCount(Connection conn) throws Exception{
		
		List<Integer> commentCountList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("commentCount");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int commentCount = rs.getInt("COUNT");
				commentCountList.add(commentCount);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return commentCountList;
	}

}
