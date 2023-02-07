package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	public Member selectMyInfo(String memberId) throws Exception{
		
		Connection conn = getConnection();
		Member member = dao.selectMyInfo(conn, memberId);
		
		return member;
	}

	/** 회원 목록 조회
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);
		
		close(conn);
		
		return memberList;
	}

	public int updateMember(String memberName, String memberGender, String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		try {
			result = dao.updateMember(conn, memberName, memberGender, memberId);
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch(SQLException e) {
			rollback(conn);
			throw new Exception("DAO 수행 중 예외 발생");
		} finally {
			close(conn);
		}
		
		
		return result;
	}
	
	public String findPw(String memberId) throws Exception{
		Connection conn = getConnection();
		
		String memberPw = null;
		
		memberPw = dao.findPw(conn, memberId);
		
		close(conn);
		
		return memberPw;
		
	}

	public int updatePw(String memberId, String memberPw1) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		try {
			result = dao.updatePw(conn, memberId, memberPw1);
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch(SQLException e) {
			rollback(conn);
			throw new Exception("DAO 수행 중 예외 발생");
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int secession(String memberId) throws Exception{
		
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = dao.secession(conn, memberId);
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}			
		} catch(SQLException e) {
			rollback(conn);
			throw new Exception("DAO 수행 중 예외 발생");
		} finally {
			close(conn);
		}
		
		return result;
	}



	
}
