package com.home.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.home.member.model.service.MemberService;
import com.home.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().updatePwd(userId,userPwd,updatePwd);
		
		if(updateMem==null) { //비밀번호 변경 실패 => 에러페이지
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "비밀번호 변경실패");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
			
		} else {
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);
			session.setAttribute("alertMsg", "성공적으로 비밀번호 변경됐습니다.");
			
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
