package com.home.board.model.vo;

import java.sql.Date;

public class Attachment {

	private int fileNo;			//첨부파일 고유번호
	private int refboardNo;		//참조하고 있는 게시글 번호	
	private String originName;  //첨부 파일의 원본명
	private String changeName;  //실제 서버에 업로드 되어있는 파일명.
	private String filePath;	//첨부파일 저장된 폴더 경로
	private Date uploadDate;	//첨부파일 업로드일
	private int fileLevel;		//1:대표이미지, 2,상세이미지	
	private String status;      //파일 상태값
	
	public Attachment() {
		
	}
	
	public Attachment(int fileNo, int refboardNo, String originName, String changeName, String filePath,
			Date uploadDate, int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refboardNo = refboardNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}

	public Attachment(int fileNo, String originName, String changeName, String filePath) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getRefboardNo() {
		return refboardNo;
	}

	public void setRefboardNo(int refboardNo) {
		this.refboardNo = refboardNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refboardNo=" + refboardNo + ", originName=" + originName
				+ ", changeName=" + changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate
				+ ", fileLevel=" + fileLevel + ", status=" + status + "]";
	}
	
}
