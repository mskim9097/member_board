package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.model.vo.Member;

public class MemberView{
	
	
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	
	
	public void memberMenu(Member LoginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = LoginMember;
		
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



	private void selectMyInfo(Member loginMember) throws Exception{
		
		System.out.println("<<내 정보 조회>>");
		
		String memberId = loginMember.getMemberId();
		
		Member member = service.selectMyInfo(memberId);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("회원번호| 아이디 |  이름  | 성별 |          가입일");
		System.out.println("------------------------------------------------------------");
		System.out.printf("   %d    | %s | %s |  %s   | %s\n",
				
				member.getMemberNo(), 
				member.getMemberId(),
				member.getMemberName(), 
				member.getMemberGender(),
				member.getEnrollDate());
		
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
	
	private void updateMember(Member loginMember) throws Exception{
		
		System.out.println("<<내 정보 수정>>");
		
		int result = 0;
		String memberId = loginMember.getMemberId();
		
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		while(true) {
			System.out.print("성별 입력(M/F): ");
			String memberGender = sc.next().toUpperCase();
			
			System.out.println();
			if(memberGender.equals("M") || memberGender.equals("F")) {
				System.out.print("정말 수정하시겠습니까?(Y/N)");
				String input = sc.next().toUpperCase();
				if(input.equals("Y")) {
					result = service.updateMember(memberName, memberGender, memberId);
					break;
				} else {
					System.out.println("취소되었습니다.");
					return;
				}
				
			} else {
				System.out.println("[M 또는 F만 입력하세요!]");
			}
			System.out.println();
			
		}
		
		if(result > 0) {
			System.out.println("회원 정보가 수정되었습니다");
		} else {
			System.out.println("회원 정보가 수정되지 않았습니다");
		}
		
		
	}
	
	private void updatePw(Member loginMember) throws Exception{
		System.out.println("<<비밀번호 변경>>");
		
		int result = 0;
		
		String memberId = loginMember.getMemberId();
	
		// 로그인 멤버의 비밀번호 불러오기
		String memberPw = service.findPw(memberId);
		

		while(true) {
			System.out.print("현재 비밀번호 : ");
			String checkPw = sc.next();
			
			if(!checkPw.equals(memberPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} else {
			
				System.out.print("새 비밀번호 : ");
				String memberPw1 = sc.next();
				
				System.out.print("새 비밀번호 확인 : ");
				String memberPw2 = sc.next();
				
				if(memberPw1.equals(memberPw2)) {
					System.out.print("정말 수정하시겠습니까?(Y/N) : ");
					String input = sc.next().toUpperCase();
					if(input.equals("Y")) {
						result = service.updatePw(memberId, memberPw1);
						break;
						
					} else {
						System.out.println("취소되었습니다");
						return;
					}
				} else {
					System.out.println("새 비밀번호가 일치하지 않습니다.");
				}
						
			}	
		}if(result > 0) {
			System.out.println("비밀번호가 수정되었습니다");
		} else {
			System.out.println("비밀번호가 수정되지않았습니다");
		}
				
	}
	
	private void secession(Member loginMember) throws Exception{
		System.out.println("<<회원 탈퇴>>");
		
		int result = 0;
		String memberId = loginMember.getMemberId();
		
		System.out.print("정말 탈퇴하시겠습니까? (Y/N) : ");
		String input = sc.next().toUpperCase();
		
		if(input.equals("Y")) {
			result = service.secession(memberId);
		} else {
			System.out.println("회원 탈퇴를 취소하였습니다.");
		}
		if(result > 0) {
			System.out.println("회원 탈퇴를 완료하였습니다");
			
		} else {
			System.out.println("회원 탈퇴에 실패하였습니다");
		}
		
	}



	
}
