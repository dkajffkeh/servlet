package com.home.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.member.model.service.MemberService;
import com.home.notice.model.service.NoticeService;
import com.home.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int nno = Integer.parseInt(request.getParameter("nno")); //  "7"-->7
	
	int result = new NoticeService().increaseCount(nno);
	
	if(result>0) { //성공
		
		Notice n = new NoticeService().selectNotice(nno);

		request.setAttribute("n",n);
		
		request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		
	} else { //실패
		// 유효한 공지사항이 아님 => 에러페이지 
		request.setAttribute("errorMsg","글을 찾을수 없습니다.");
		
		RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
		view.forward(request, response);
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
