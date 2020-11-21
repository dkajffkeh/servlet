package com.kh.model.vo;

public class User1 extends User2 {

	private String a;
	private String b;
	
	public User1() {
		
	}

	public User1(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "User1 [a=" + a + ", b=" + b + "]";
	}

}
