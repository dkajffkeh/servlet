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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 1. 요청시 전달값에 한글이 있을  경우  대비해 인코딩 해줘야함.
		request.setCharacterEncoding("UTF-8");
		// 2. 요청시 전달값 뽑기
		String userId = request.getParameter("userId");
		String userPwd =request.getParameter("userPwd");
		String userName =request.getParameter("userName");
		String phone = request.getParameter("phone"); //"" 빈문자 열일수 있음.
		String userEmail = request.getParameter("userEmail"); // ""가능
		String address = request.getParameter("address"); // "" 가능
		String [] interests = request.getParameterValues("interest"); //null 가능. ["운동" , "등산"] 운동,등산;
		
		String interest = "";
		if(interests != null) {
			interest = String.join(",", interests);
		}
		
		Member m = new Member(userId, 
				              userPwd,
				              userName,
				              phone,
				              userEmail,
				              address,
				              interest);
		
		int result = new MemberService().insertMember(m);
		// 3. 요청을 처리해주는 서비스 클래스의 메소드 호출 후 결과 받기.
		// 4. 반환 받은 결과를 가지고 사용자가 보게될 요청 응답 페이지 지정.
		
		if(result>0) {
			//Redirect 방식으로 제 요청.
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg",m.getUserName()+" 님 환영합니다!");
			
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errorMsg", "회원가입에 실패 했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
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
