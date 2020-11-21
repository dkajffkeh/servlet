package com.kh.model.vo;

public class User extends User1 {

	private int no;
	private String name;
	private int age;
	private String gender;
	
	public User() {
		
	}
	public User(int no, String name, int age, String gender) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public User(String a, String b) {
		super(a, b);
	}

	public User(String a, String b, int no, String name, int age, String gender) {
		super(a, b);
		this.no = no;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
}
