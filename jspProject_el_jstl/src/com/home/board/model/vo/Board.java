package com.home.board.model.vo;

import java.sql.Date;

public class Board {

	private int boardNo;  //게시글 식별번호
	private int boradType;  // 게시글 타입 1.일반 2.사진게시판
	private String category;   //게시글 카테고리
	private String boardTitle; //게시글 제목
	private String boardContent; //게시글 내용
	private String boardWriter; // 게시글 작성자
	private int boardCount; // 게시글 조회수
	private Date createDate; // 게시글 작성일
	private String status; // 게시글 상태
	
	private String titleImg;
	
	public Board() {
		
	}

	public Board(int boardNo, int boradType, String category, String boardTitle, String boardContent,
			String boardWriter, int boardCount, Date boardDate, String boardStatus) {
		super();
		this.boardNo = boardNo;
		this.boradType = boradType;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.createDate = boardDate;
		this.status = boardStatus;
	}
	
	public Board(int boardNo, String category, String boardTitle, String boardWriter, int boardCount, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.createDate = createDate;
	}	
	
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter,
			Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}

	public Board(int boardNo, String boardTitle, int boardCount, String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCount = boardCount;
		this.titleImg = titleImg;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoradType() {
		return boradType;
	}

	public void setBoradType(int boradType) {
		this.boradType = boradType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public Date getBoardDate() {
		return createDate;
	}

	public void setBoardDate(Date boardDate) {
		this.createDate = boardDate;
	}

	public String getBoardStatus() {
		return status;
	}

	public void setBoardStatus(String boardStatus) {
		this.status = boardStatus;
	}
	
	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boradType=" + boradType + ", category=" + category + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", boardCount="
				+ boardCount + ", boardDate=" + createDate + ", boardStatus=" + status + "]";
	}	
	
}
