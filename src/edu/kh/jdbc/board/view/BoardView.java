package edu.kh.jdbc.board.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.member.model.vo.Member;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	private BoardService service = new BoardService();
	
	private Member loginMember = null;
	
	
	public void boardMenu(Member loginMember) {
		
		this.loginMember = loginMember;
		
		int input = -1;
		try {
			
			do {
				System.out.println("*****게시판 기능*****");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색");
				System.out.println("0. 이전 메뉴 이동");
				
				System.out.println("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				
				case 1: selectAllBoard(loginMember); break;
				case 2: selectBoard(loginMember); break;
				case 3: insertBoard(loginMember); break;
				case 4: searchBoard(loginMember); break;
				case 0:
					System.out.println("이전 메뉴 이동");
					return;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세용");
				}
				
			} while(input != 0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectAllBoard(Member loginMember) throws Exception{
		
		System.out.println("<<게시글 목록 조회>>");
		
		List<Board> boardList = service.selectAllBoard();
		
		List<Integer> commentCountList = service.commentCount();
		
		if(boardList.isEmpty()) {
			System.out.println("조회된 게시글이 없습니다");
		} else {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("게시글 번호 | 제목[댓글 수] | 작성자명 |        작성일       | 조회수");
			System.out.println("---------------------------------------------------------------------");
			for(Board board : boardList) {
				System.out.printf("      %d     |  %s  |  %s  | %s |    %d\n",
						board.getBoardNo(),
						board.getBoardTitle(),
						board.getMemberName(),
						board.getCreateDate(),
						board.getReadCount()						
						);
			}
		}
		
	}
	
	private void selectBoard(Member loginMember) throws Exception{
		
		System.out.println("<<게시글 상세 조회>>");
		
		
		System.out.print("게시글 번호 : ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		Board board = service.selectBoard(boardNo);
		
		if(board == null) {
			System.out.println("조회된 게시글이 없습니다.");
		} else {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("게시글 번호 | 제목[댓글 수] | 내용 | 작성자명 |        작성일       | 조회수");
			System.out.println("---------------------------------------------------------------------");
			System.out.printf("      %d     |  %s  | %s |  %s  | %s |    %d\n",
					board.getBoardNo(),
					board.getBoardTitle(),
					board.getMemberName(),
					board.getCreateDate(),
					board.getReadCount()						
					);
		}
	}
	
	private void insertBoard(Member loginMember) {
		// TODO Auto-generated method stub
		
	}


	private void searchBoard(Member loginMember) {
		// TODO Auto-generated method stub
		
	}


}
