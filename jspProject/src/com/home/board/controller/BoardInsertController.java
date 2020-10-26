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
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardInsertController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 일반 방식이 아니라 multipart/form-data 방식으로 전송할 경우 request 로 값을 뽑을 수 없음.
		request.setCharacterEncoding("utf-8");
		// HttpServletReqeust => MultipartRequest 로 변환한 이후에 뽑을 수 있음.
		if (ServletFileUpload.isMultipartContent(request)) {

			// >> 요청시 전달된 파일을 업로드 (서버에 셋팅해 놓은 폴더에 저장하겠다.) 처리
			// 1. 전달되는 파일을 처리할 작업내용(전달되는 파일의 용량 제한(10MB), 전달되는 파일을 어느폴더에 저장할건지, 폴더 경로.)
			// 1_1전송파일 용량 제한 int maxSize => byte단위 10M byte
			/*
			 * 1kbyte = 1024byte 1mbyte = 1024byte*1024 10mbyte = 10*1024*1024 1gbyte =
			 * 1024*1024*1024 byte;
			 */
			int maxSize = 10 * 1024 * 1024;
			// 1_2. 전달되는 파일을 저장할 서버의 폴더 물리적인 경로 알아내기 String savePath uploade 처리할떄 필요한 과정.
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/"); // 절대경로

			// 2. 해당 전달되는 파일명 수정 및 서버에 업로드 작업.
			/*
			 * HttpServletRequest -> MultipartRequest multiRequest 변환시켜야함. (해당 객체 생성.) 하지만
			 * 외부 라이브러리를 추가해야함. : cos.jar(com.oreilly.servlet 의 약자) 다운로드 받아야함
			 * http://www.servlets.com 위의 구문 만으로 넘어온 첨부파일은 내가 지정한 폴더에 업로드가 됨. ==> 혹시라도 DB 에
			 * insert 할때 문제가 발생했을 경우 해당 업로드된 파일 찾아서 지우는게 좋음
			 * 
			 * ==> 사용자가 첨부한 파일을 업로드 할 떄 있는 그대로의 이름으로 업로드 하지 않음. -1.중복이 발생할수 있기때문 -2.한글 또는
			 * 특수문자가 포함돼있을경우 띄어쓰기가 있는경우가 있기때문. => 기본적인 수정명 작업을 해주는 객체가 cos 에서 제공하는
			 * defaultFileRenamePolicy. => 내부적으로 rename() 메소드가 실행되면서 파일명 수정이 진행됨 => 기존에 동일한
			 * 파일이 있을경우 뒤에 카운팅 된 숫자가 붙음. 동일한 이름의 파일이 없다 그럼 알아서 들어옴. ex)aaa,jpg,
			 * 
			 * **하지만 나만의 방법대로 수정할수 있는 방법이 있음.
			 * 
			 * 
			 * 
			 */
			// MultipartRequest multiRequest = new
			// MultipartRequest(request,savePath,maxSize,"UTF-8",new DefaultRenamePolicy());
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			// >> 요청시 전달된 값들을 뽑아서 DB에 insert하는 프로세스.
			// 1. Board 테이블에 Insert할 카테고리번호, 게시제목, 내용, 작성자회원번호를 뽑아서 Board 객체에 담기

			// 2. Attachment 테이블에 insert 할 원본명, 수정명 (실제 서버에 업로드된 명), 저장된 폴더 경로를 Attachment
			// 객체에 담음
			// (만일 첨부파일이 없다면 Attachment 객체 null)

			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String boardWriter = multiRequest.getParameter("userNo");

			Board b = new Board();
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(boardWriter);

			Attachment at = null; // 있는지 없는지 조건검사 후 실행.
			// 넘어온 파일이 있을경우
			if (multiRequest.getOriginalFileName("upfile") != null) { // 넘어온 첨부 파일이 있을 경우

				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); // 원본명
				at.setChangeName(multiRequest.getFilesystemName("upfile")); // 실제서버에 업로드된 파일 이름.
				at.setFilePath("resources/board_upfiles/");

			}

			// insert 호출하는 service 호출해서 결과받기
			// case 1: 첨부파일이 있었다면 insertBoard(생성된 Board Attachment 객체)
			// case 2: 첨부파일 없었다면 insertBoard(Board객체 , null)
			int result = new BoardService().insertBoard(b, at);

			if (result > 0) { // 성공

				
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1");

			} else { // 실패 ==> 업로드 파일 찾아서 삭제시킨 후에 => error 페이지 포워딩

				if (at != null) {// 첨부파일이 있을경우

					File failedFile = new File(savePath + at.getChangeName());
					failedFile.delete();
				}

				request.setAttribute("errorMsg", "등록실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);

			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
