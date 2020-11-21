package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.vo.User;

/**
 * Servlet implementation class JqAjaxController4
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int num = Integer.parseInt(request.getParameter("num"));
		ArrayList<User> list = new ArrayList();
		
		User u = new User("상속변수1","상속변수2",1,"박철수",30,"남");
		User u1 = new User(1,"박철수",30,"남");
		
		list.add(u);
		list.add(u1);
		list.get(0).getA();
		list.get(1).getAge();
	
		
		//GoogleJason -> Gson.
		//내가 응답할 데이터를 알아서 Json객체로 변환해서 응답해주는 객체
		//라이브러리 추가해야함.
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		gson.toJson(u,response.getWriter());
		//키값은 해당 vo 객체의 필드명.
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
