package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.emp.model.vo.Employee;
import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.model.vo.Member;

public class MemberView{
	
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	
	
	public void memberMenu(Member loginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		
		int input = -1;
		try {
			
			do {
				System.out.println("***** 회원 기능*****");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회");
				System.out.println("3. 내 정보 수정");
				System.out.println("4. 비밀번호 변경");
				System.out.println("5. 회원 탈퇴");
				System.out.println("0. 이전 메뉴 이동");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				
				case 1: selectMyInfo(loginMember); break; // 내 정보 조회
				case 2: selectAll(loginMember); break; // 회원 목록 조회
				case 3: updateMember(loginMember); break; //내정보 수정
				case 4: updatePw(loginMember); break; // 비밀번호 변경
				case 5: secession(loginMember); break; // 회원 탈퇴
				case 0:
					System.out.println("이전 메뉴 이동");
					return;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세용");
				}
				
				
				
			}while(input != 0);
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}



	private void selectMyInfo(Member loginMember) {
		
		System.out.println("<<내 정보 조회>>");
		System.out.println("------------------------------------------------------------");
		System.out.println("회원번호| 아이디 |  이름  | 성별 |          가입일");
		System.out.println("------------------------------------------------------------");
		System.out.printf("   %d    | %s | %s |  %s   | %s\n",
				loginMember.getMemberNo(), 
				loginMember.getMemberId(),
				loginMember.getMemberName(), 
				loginMember.getMemberGender(),
				loginMember.getEnrollDate());
		
	}
	
	private void selectAll(Member loginMember) throws Exception{
		
		System.out.println("<<회원 목록 조회>>");
		
		List<Member> memberList = service.selectAll();
		
		if(memberList.isEmpty()) {
			System.out.println("조회된 사원 정보가 없습니다.");
		} else {
			System.out.println("------------------------");
			System.out.println("아이디 |  이름  | 성별 |");
			System.out.println("------------------------");
			for(Member member : memberList) { 
				System.out.printf("%s | %s |  %s   |\n",
						member.getMemberId(),
						member.getMemberName(), 
						member.getMemberGender());
			}
		}
		
	}
	
	private void updateMember(Member loginMember) {
		// TODO Auto-generated method stub
		
	}
	
	private void updatePw(Member loginMember) {
		// TODO Auto-generated method stub
		
	}
	
	private void secession(Member loginMember) {
		// TODO Auto-generated method stub
		
	}



	
}
