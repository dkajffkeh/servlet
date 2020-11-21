package com.kh.common;

import java.security.MessageDigest;


public class SHA256 {

	public static String getSHA256(String input) {
		
		
		StringBuffer result = new StringBuffer();
		
		try {
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte [] salt = "Hello! this is a Salt".getBytes();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
		return result.toString();
		
	}
	
	
}
