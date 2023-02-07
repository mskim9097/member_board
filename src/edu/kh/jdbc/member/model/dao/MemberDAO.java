package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	// 기본생성
	public MemberDAO() {
		
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("member-query.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member selectMyInfo(Connection conn, String memberId) throws Exception{
		
		Member member = null;
		
		try {
			String sql = prop.getProperty("selectMyInfo");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member(rs.getInt("MEMBER_NO"),
						memberId,
						rs.getString("MEMBER_NM"),
						rs.getString("MEMBER_GENDER"),
						rs.getString("ENROLL_DATE"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	/** 회원 정보 조회 DAO
	 * @param conn
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception{
		
		
		List<Member> memberList = new ArrayList<>();
		
		try {
						
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				Member member = new Member(memberId, memberName, memberGender);
				
				memberList.add(member);
				
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}

	public int updateMember(Connection conn, String memberName, String memberGender, String memberId) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public String findPw(Connection conn, String memberId) throws Exception {
		
		String memberPw = null;
		
		try {
			String sql = prop.getProperty("findPw");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberPw = rs.getString("MEMBER_PW");
			}
			
		} finally {
			
		}
		
		return memberPw;
		
	}

	public int updatePw(Connection conn, String memberId, String memberPw1) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberPw1);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int secession(Connection conn, String memberId) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

}
