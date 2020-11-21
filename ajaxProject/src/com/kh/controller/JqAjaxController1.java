package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxController1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청시 전달되는값 뽑아보기.
		
		
		String str = request.getParameter("input");
		String bno = request.getParameter("bno");
		System.out.println(bno);
	
		//요청처리가 완료했다는 가정하에
		//문자열로 응답할 데이터
		String responseData = "입력된값:" + str + " ,길이:" + str.length();
		
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(responseData); //오케 여기서 보냈는데 어디서 받을건데 ?  => 응답한 데이터 success:function(응답결과.)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
