package com.home.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.home.board.model.service.BoardService;
import com.home.board.model.vo.Attachment;
import com.home.board.model.vo.Board;
import com.home.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1_1. 용량제한값 있어야함. (int maxSize)
			int maxSize = 10*1024*1024;
			
			// 1_2. 저장할 폴더 물리적 경로.(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			// 2. 전달된 파일들 수정명 작업 및 업로드
			MultipartRequest mr = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			// 3. DB에 기록이 필요함.
			
			// 3_1. Board
			String boardWriter = mr.getParameter("userNo");
			String boardTitle = mr.getParameter("title");
			String boardContent = mr.getParameter("content");
		
			Board b = new Board();
			b.setBoardWriter(boardWriter);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			// 3_2. Attachment
			ArrayList<Attachment> list = new ArrayList<>();
			
			for(int i = 1; i<=4; i ++) {
				
				String key = "file"+i;
				
				if(mr.getOriginalFileName(key)!=null) {
					// Attachment 객채 생성해서 답아줌. <=원본명,수정명,폴더경로,파일레벨 담아야함.
					// list 추가
					Attachment at = new Attachment();					
					at.setOriginName(mr.getOriginalFileName(key));
					at.setChangeName(mr.getFilesystemName(key));
					at.setFilePath("resources/thumbnail_upfiles/");
					
					if(i==1) {
						at.setFileLevel(1);
					}else {
						at.setFileLevel(2);
					}
					
					list.add(at);		
				}
			}
			
			int result = new BoardService().insertThumbnailBoard(b,list);
			
			if(result>0) {//성공 ==> 리스트페이지 요청 list.th url 요청
				
				request.getSession().setAttribute("alertMsg","사진게시판 등록 성공");
				response.sendRedirect(request.getContextPath()+"/list.th");
				
			} else { //실패
				
				//업로드된 파일 삭제해야함. 그 이후에 errorPage 포함.
				for(Attachment at : list) {
					new File(savePath + at.getChangeName()).delete();
		
				}
				request.setAttribute("errorMSg","업로드 실패.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);;
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

}
