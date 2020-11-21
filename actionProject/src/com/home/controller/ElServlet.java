package com.home.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.home.model.vo.Person;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/el.do")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	/*
	 * 데이터를 담울 수 있는 JSP 내장객체 종류 총 4가지
	 * 1.Application(ServletContext) 영역 공유범위가 가장 넓음 한 어플리케이션당 단 1개 존재하는 객체. (전역에서 사용이 가능함.)(어떤 java/servlet/jsp 에서 뽑아쓸 수 있음)
	 * 2.Session(HttpSession) 한 브라우저당 1개 존재하는 객체. 여기에 담긴 데이터는 어떠한 jsp/servlet 사용가능.   
	 * 3.Request(HttpServletReqeust) 사용자의 요청에 의해서 만들어지는객체. 여기에 담긴 데이터는 해당 request. 객체를 포워딩 받는 응답페이지 에서만 사용가능.
	 * 4.page 영역. 오로지 jsp 에서만 담고 꺼내쓸 수 있음. 공유범위가 가장 작음.
	 * 
	 * 4개의 객체들에 데이터를 담고자 할때는 .setAttribute 메소드를 이용해서 담음.
	 * 
	 * 영역==scope
	 */
	
		//requestScope에 담음.
	request.setAttribute("classRoom", "I강의장");	
	request.setAttribute("student", new Person("고니",24,"M"));
	
	    //Session scope
    HttpSession session = request.getSession();
    session.setAttribute("business","KH정보교육원");
    session.setAttribute("teacher", new Person("강보람",20,"F"));
    
    request.setAttribute("scope", "request");
    session.setAttribute("scope", "session");
    
    //Application
    ServletContext application = request.getServletContext();
    application.setAttribute("scope", "application");
	
    request.getRequestDispatcher("views/1_EL/01_el.jsp").forward(request, response);
    
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
