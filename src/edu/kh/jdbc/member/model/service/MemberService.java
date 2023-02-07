package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	public List<Member> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);
		
		return memberList;
	}



	
}
