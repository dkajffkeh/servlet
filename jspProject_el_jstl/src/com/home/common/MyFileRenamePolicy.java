package com.home.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	
	//원본파일이 전달됨   전달 받아서 이름 수정을 한 후 반환. 다 마친 수정된 파일을 반환해주는 File.
	@Override
	public File rename(File originFile) {
		
		// 무슨 이름으로 ?
		// 원본파일이
		// 'aaa.jsp 이면 "20201023194810_(랜덤값5개).jpg 
		// 원본파일명 
		String originName = originFile.getName(); //aaa.jpg
		// 수정명 : 파일업로드시간(년월일시분초) + 5자리랜덤값(10000~99999) 사이 랜덤값 + 원본파일명부터 뽑은 확장자.
		//1.  파일 업로드 시간 알아내기
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String currentTime =sdf.format(new Date());
		//2.  5자리 랜덤값 int ranNum
		int ranNum = (int)(Math.random()*90000+10000); //5자리 랜덤값.
		
		//3.  원본파일명으로부터 뽑은 확장자(String ext)
		int dot = originName.lastIndexOf(".");
		String ext = originName.substring(dot);  //.jpg 가 기록이 될거임.
		
		String fileName = currentTime + ranNum + ext;
		//전달된 원본을 수정된 파일명으로 파일 객체를 하나 생성해서 리턴해야함
		
		
		return new File(originFile.getParent(),fileName);
	}

	
	
	
}
