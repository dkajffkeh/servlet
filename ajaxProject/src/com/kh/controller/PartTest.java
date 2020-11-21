package com.kh.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class PartTest
 */

/**
 * fileSizeThreshold	서버로 파일을 저장할 때 파일의 임시 저장 사이즈
 * maxFileSize			1개 파일에 대한 최대 사이즈
 * maxRequestSize		서버로 전송되는 request의 최대 사이즈
 * @author  "SeokRae (kslbsh@gmail.com)"
 * https://www.journaldev.com/2122/servlet-3-file-upload-multipartconfig-part
 */

@WebServlet("/upload.do")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
				 maxFileSize=1024*1024*50,      	// 50 MB
				 maxRequestSize=1024*1024*100)      // 100 MB
public class PartTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "filefolder";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html; charset=UTF-8"); 
		String path = request.getServletContext().getRealPath("/resources/board_upfiles");
		String Filepath = path+UPLOAD_DIR;	
		File fileSaveDir = new File(path);
		
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();	
		}	
		List<String> list = new ArrayList<String>();
		for(Part part : request.getParts()) {
			
			if(part.getName().equals("file")) {			
				String renamed = new UploadRename().randomString(getFileName(part));
				part.write(path+File.separator+renamed);
				list.add(renamed);
			}
			
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 private String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");      
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {     	
	                return token.substring(token.indexOf("=") + 2, token.length()-1);
	            }
	        }
	        return "";
	    }
}
