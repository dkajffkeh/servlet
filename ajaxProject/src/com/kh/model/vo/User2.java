package com.kh.model.vo;

public class User2 {

	private String c;
	private String d;
	
	public User2() {
		
	}

	public User2(String c, String d) {
		super();
		this.c = c;
		this.d = d;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "User2 [c=" + c + ", d=" + d + "]";
	}
	
}
