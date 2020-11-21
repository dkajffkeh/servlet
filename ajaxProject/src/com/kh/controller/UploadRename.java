package com.kh.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadRename {

	
	public String randomString(String fileName) {	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(new Date());	
		
		// 2. 5자리랜덤값 (int random)
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		// 3. 원본파일명으로부터뽑은 확장자 (String ext)
		int dot = fileName.lastIndexOf(".");
		String ext = fileName.substring(dot);		
		
		String renamed = currentTime + ranNum + ext;
		
		
		return renamed;
	}
}
