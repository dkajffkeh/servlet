package com.home.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.board.model.service.BoardService;
import com.home.board.model.vo.Reply;

/**
 * Servlet implementation class ReplyInsertFormController
 */
@WebServlet("/rinsert.bo")
public class ReplyInsertFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String content = request.getParameter("content");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String userNo = request.getParameter("userNo");
		
		
		
		Reply r = new Reply();
		r.setReplyContent(content);
		r.setRefBno(bno);
		r.setReplyWriter(userNo);
		
		int result = new BoardService().insertPeply(r);
		
		if(result>0) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print("fail");
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
