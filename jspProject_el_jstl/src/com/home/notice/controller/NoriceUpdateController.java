package com.home.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.notice.model.service.NoticeService;
import com.home.notice.model.vo.Notice;

/**
 * Servlet implementation class NoriceUpdateController
 */
@WebServlet("/update.no")
public class NoriceUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoriceUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice();
		n.setNoticeNo(nno);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		
		int result = new NoticeService().noticeUpdate(n);
		
		if(result>0) { //성공
			request.getSession().setAttribute("alertMsg", "변경되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.no?nno=" + nno);
			
			
			//jsp/detail.no?nno=9
		} else { //실패
			request.setAttribute("errorMsg", "업데이트 실패");
			request.getRequestDispatcher("../common/errorPage.jsp");
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
