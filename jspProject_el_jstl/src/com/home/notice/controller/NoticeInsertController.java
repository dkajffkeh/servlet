package com.home.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.notice.model.service.NoticeService;
import com.home.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userNo = request.getParameter("userNo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice();
		n.setNoticeWriter(userNo);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result>0) { //성공 list.no => 공지사항 제요청 해야함.
			
			request.getSession().setAttribute("alertMsg","성공적으로 등록되었습니다!");
			response.sendRedirect(request.getContextPath()+"/list.no");
			
			//forward 가 아니라 url 그대로 로청해야함.
			//아래같이 불러버리면 list forward 를 보낼때 list 가 null 이 되어버림.
			//RequestDispatcher view = request.getRequestDispatcher("");

			
		} else { //실패
			request.setAttribute("errorMsg", "공지사항 작성 실패");
			request.getRequestDispatcher("views/commom/errorPage.jsp").forward(request, response);
			
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
