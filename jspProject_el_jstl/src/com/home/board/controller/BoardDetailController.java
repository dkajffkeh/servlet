package com.home.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.board.model.service.BoardService;
import com.home.board.model.vo.Attachment;
import com.home.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int result = new BoardService().increaseCount(bno);
		
		if(result>0) { //유효한 게시글.
			Board b = new BoardService().selectBoard(bno);
			
			Attachment at = new BoardService().selectAttachment(bno);
			
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			request.getRequestDispatcher("views/board/BoardDetailView.jsp").forward(request, response);
			
			
			
		}else { // 유효한 게시글 x error 페이지 담아서 forwarding.
			request.setAttribute("errorMsg","게시글이 존재하지 않습니다.");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request,response);
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
