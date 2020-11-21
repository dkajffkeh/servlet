package com.home.board.controller;

import java.io.File;
import java.io.IOException;

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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	
	//enctype = multipart/form-date 로 잘 전송이 되었을 때만 수행되게끔
	if(ServletFileUpload.isMultipartContent(request)) {
		
		int maxSize = 10*1024*1024;
		// 1.2 전달되는 파일을 저장시킬 서버의 폴더의 물리적 경로
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
		//2. 전달되는 파일 수정명 작업 및 업로드 처리
		// HttpServletRequest --> MultipartRequest 로 생성.
		
		MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
		
		//본격적으로 sql 문 실행하기 위한 값 뽑아서 객체에 담기.
		//무조건 실행하는 sql문. => Board 테이블 Update
		
		//=====Board======//
		int bno = Integer.parseInt(multiRequest.getParameter("bno"));
		String category = multiRequest.getParameter("category");
		String title = multiRequest.getParameter("title");
		String content = multiRequest.getParameter("content");
		
		Board b = new Board();
		b.setBoardNo(bno);
		b.setCategory(category);
		b.setBoardTitle(title);
		b.setBoardContent(content);
		
		
		//======Attachment=======//
		
		Attachment at = null;
		
		//원본명을 뽑는 
		if(multiRequest.getOriginalFileName("reupfile")!=null) {
			
			at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("reupfile"));
			at.setChangeName(multiRequest.getFilesystemName("reupfile"));
			at.setFilePath("resources/board_upfiles/");
			
			//위의 3개의 데이터는 기존의 첨부파일이 있든 없든 간에 
			//새로전달된 파일이 있었기 때문에 무조건 dB에 기록할값.		
			if(multiRequest.getParameter("originFileNo")!=null) {
				//새로운 첨부파일 있고, 기존의 파일또한 있었을경우
				//=> Attachment 테이블에 Update
				// + 기존의 첨부파일번호
				at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				
			
			} else {
				// 새로운 첨부파일이 있고 기존의 파일이 없엇을 경우
				// Attachment 테이블에 insert.
				// +게시글 번호가 필요함.
				at.setRefboardNo(bno);
						
			}
		}
			int result = new BoardService().updateBoard(b,at);
			
			// case 1: 새로운 첨부파일이 없을경우.  => board update
			// case 2: 첨부파일이 O 새로운 첨부파일 O => board update attachment update
			// case 3: 첨부파일이 X 새로운 첨부파일이 O. => board => update attachment => insert
			if(result>0) {
				//성공
				//기존의 첨부 파일이 있었다면
				if(at !=null && multiRequest.getParameter("originFileName")!=null) {
					File deleteFile = new File(savePath + multiRequest.getParameter("originFileName"));
					deleteFile.delete();
				}
				
				request.getRequestDispatcher("detail.bo?bno="+bno).forward(request, response);				
				
			} else {
				
				//문제 생김. 실패시 errorPage
				request.setAttribute("errorMsg","수정실패");
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
