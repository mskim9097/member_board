package edu.kh.jdbc.board.model.vo;

import java.util.List;

public class Board {
	
	private int boardNo; // 게시글 번호
	private String boardTitle; // 게시글 제목
	private int commentCount; // 댓글 수
	private String boardContent; // 게시글 내용
	private String memberName; // 작성자명
	private String createDate; // 게시글 작성일
	private int readCount; // 게시글 조회수
	private String deleteFlag; // 게시글 삭제여부
	
	private List<Comment> commentList; //댓글 목록
	
	
	public List<Comment> getCommentList() {
		return commentList;
	}


	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}


	public Board() {}


	public Board(int boardNo, String boardTitle, int commentCount, String memberName, String createDate, int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.commentCount = commentCount;
		this.memberName = memberName;
		this.createDate = createDate;
		this.readCount = readCount;
	}


	


	public Board(int boardNo, String boardTitle, String boardContent, String memberName, String createDate,
			int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberName = memberName;
		this.createDate = createDate;
		this.readCount = readCount;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public String getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public int getCommentCount() {
		return commentCount;
	}


	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
}
