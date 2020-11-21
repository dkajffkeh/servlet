package com.home.member.model.vo;

import java.sql.Date;

//vo

public class Member {
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	private String userInterest;
	private Date enrollDate;
	private Date modifyDate;
	private String userStatus;
	
	public Member() {
		
	}
	
	public Member(int userNo, String userId, String userPwd, String userName, String userPhone, String userEmail,
			String userAddress, String userInterest, Date enrollDate, Date modifyDate, String userStatus) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userInterest = userInterest;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.userStatus = userStatus;
	}

	public Member(String userId, String userPwd, String userName, String userPhone, String userEmail,
			String userAddress, String userInterest) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userInterest = userInterest;
	}
	
	public Member(String userId, String userName, String userPhone, String userEmail, String userAddress,
			String userInterest) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userInterest = userInterest;
	}

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserInterest() {
		return userInterest;
	}
	public void setUserInterest(String userInterest) {
		this.userInterest = userInterest;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", userAddress=" + userAddress
				+ ", userInterest=" + userInterest + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate
				+ ", userStatus=" + userStatus + "]";
	}

}
