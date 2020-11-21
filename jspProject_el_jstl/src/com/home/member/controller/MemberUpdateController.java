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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		
		//요청시 전달된 값을 뽑아야함.
		String userId = request.getParameter("userId");
		String userName =request.getParameter("userName");
		String phone = request.getParameter("phone");
		String userEmail = request.getParameter("userEmail");
		String userAddress = request.getParameter("address");
		String interests [] = request.getParameterValues("interest");
		
		String interest = "";
		if(interests!=null) {
			interest = String.join(",", interests);
		}
		
		Member m = new Member(userId, userName, phone , userEmail, userAddress , interest);
		
		//요청을 처리할 서비스의 메소드 호출 및 결과받기
		Member updateMem = new MemberService().updateMember(m);
		
		if(updateMem==null) { //정보 변경 실패 =>에러페이지
			
			request.setAttribute("errorMsg","정보 변경에 실패하였습니다.");
			RequestDispatcher views = request.getRequestDispatcher("views/common/errorPage.jsp");
			views.forward(request, response);
			
		} else {
			//session 에 담겨있는 loginUser 을 업데이트 해줘야함.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);
			session.setAttribute("alertMsg", "변경에 성공하였습니다.");
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
