package com.home.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.home.member.model.service.MemberService;
import com.home.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 요청.
		//1. 전달값에 한글이 있을경우 인코딩 처리 해야함.
		request.setCharacterEncoding("UTF-8");
		//2. 요청시 전달값 꺼내서 변수 또는 객체에 기록하기.
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginUser = new MemberService().loginMember(userId,userPwd); 
		//4. 반환받은 결과를 가지고 사용자가 보게될 응답 페이지 작성.
		if(loginUser==null) { //로그인 실패 // 에러페이지
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			//응답할 view 에 필요한 데이터가 있을경우 attribute 에 담기.
			//응답페이지를 JSP 에게 위힘(제어권 넘김). RequestDispatcher
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request,response);
			
			//forwarding 방식 해당 선택된 뷰가 보여질뿐 url 에는 변동이 없음. (이게 너무 비효율적.)
			
		}else { //로그인 성공 //
		/*
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			request.setAttribute("member", loginUser);
			view.forward(request, response);
			잘 보여지지만 url 에 여전히 login.me가 남아있어버림.
			forwarding 방식으로 응답 출력.
			
			로그인 한 회원정보 (login User) 를 session 에 한번만 담아놓으면 어느 페이지던간에 session 에 담겨있는 회원객체에 대한 정보를 뽑아서 써내 술 수 있음.
			
			*/
			
			//2. redirect 방식.(url 을 재 요청하는 방식)
			
			// 데이터를 담을수 있는 영역 JSP 내장 객체
			// 1) application : 여기에 담은 객체는 전역에서 이용이 가능  ==>>가장 넓은 영역의 내장객체
			// 2) session     : session 에 담은 데이터는  모든 jsp 모든 servlet 상에서 꺼내 쓸수 있음
			// 					공유 범위가 전역은 아니지만 넓은 범위에서 사용가능
			// 3) request     : forwarding 한 응답페이지 내에서만 사용가능. 전달된 응답 jsp 에서만 꺼내 쓸 수 있음.
			
			// 4) Page        : page 에 담은 데이터는 해당 그 jsp 페이지 에서만 꺼내 쓸 수 있음.
			
			//setAttribute("키",밸류)<담음  getAttribute("키")<뽑음.
			
			 //.removeAttribute << ("키") -->> 삭제.
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(-1);
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath());
			
			
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
