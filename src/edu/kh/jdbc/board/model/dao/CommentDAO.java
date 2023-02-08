package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Comment;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class CommentDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public CommentDAO() {
	
		try {
			prop = new Properties();
		
			prop.loadFromXML(new FileInputStream("comment-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public List<Comment> selectAllComment(Connection conn, int boardNo) throws Exception {
		
		List<Comment> commentList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectComment");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int commentNo = rs.getInt("COMMENT_NO");
				String commentContent = rs.getString("COMMENT_CONTENT");
				String createDate = rs.getString("CREATE_DT");
				String memberName = rs.getString("MEMBER_NM");
				
				Comment comment = new Comment(commentNo, commentContent,
								createDate, memberName);
				
				commentList.add(comment);
			}
			
		} finally {
			rs.close();
			pstmt.close();
		}
		return commentList;
	}

}
