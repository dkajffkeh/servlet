package com.home.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.home.member.model.dao.MemberDao;
import com.home.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
	  int result = new MemberService().deleteMember(userId,userPwd);
	
	  if(result>0) {//성공
		  HttpSession session = request.getSession();
		  session.setAttribute("alertMsg","정상 처리되었습니다.");
		  session.removeAttribute("loginUser");
		  response.sendRedirect(request.getContextPath());
		  
		  
	  } else {//실패
		  HttpSession session = request.getSession();
		  session.setAttribute("alertMsg", "비밀번호가 잘못되었거나 정상적인 처리가 일어나지 않았습니다!");
		  response.sendRedirect(request.getContextPath()+"/myPage.me");
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
