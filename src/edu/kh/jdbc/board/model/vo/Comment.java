package edu.kh.jdbc.board.model.vo;

public class Comment {
	
	private int commentNo; // 댓글 번호
	private String commentContent; // 댓글 내용
	private String createDate; // 댓글 작성일
	private String deleteFlag;
	private int boardNo;  // 작성된 게시글 번호
	private String memberName; // 작성자 이름
	
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}


	public Comment(int commentNo, String commentContent, String createDate, String memberName) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.createDate = createDate;
		this.memberName = memberName;
	}


	public int getCommentNo() {
		return commentNo;
	}


	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	
	
	
}
