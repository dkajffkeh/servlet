package com.home.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCtemplate {

	//1.Connection 객체 생성(DB) 생성 한 후 해당 생성된 Connection 반환해주는 getConnection
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		//물리적인 파일 경로를 알아내는 방법이 있음(classes 폴더 안에 경로를 읽어들여야함.).
		String path = JDBCtemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// C:\\servlet-jep-workspace2\\jspProject\\WebCont\\classes ..... ->
		
		
		try {
			prop.load(new FileInputStream(path));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("id"),
											   prop.getProperty("pwd"));
		
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return conn;
	}
	
	
	//2.Connection 객체 전달 받아서 commit 해주는 commit 메소드
	public static void commit(Connection conn) {
		
		try {
			if(!conn.isClosed()||conn!=null) {
				
				conn.commit();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//3.Connection 객체 전달 받아서 rollback 해주는 rollback 메소드
	public static void rollback(Connection conn) {
		
		try {
			if(!conn.isClosed()||conn!=null) {
				
				conn.rollback();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//4.Connection 객체 전달받고 close 해주는 메소드.
	public static void close(Connection conn) {
		try {
			if(!conn.isClosed()||conn!=null) {
				
				conn.close();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//5.Statement  관련 객체 전달받고 close 해주는 메소드
	public static void close(Statement stmt) {
		
		try {
			if(!stmt.isClosed()||stmt!=null) {
				
				stmt.close();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//6.ResultSet  close 메소드.
	public static void close(ResultSet rs) {
		
		try {
			if(!rs.isClosed()||rs!=null) {
				
				rs.close();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
}
