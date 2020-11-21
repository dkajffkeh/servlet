package com.home.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.home.board.model.service.BoardService;
import com.home.board.model.vo.Board;
import com.home.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//-------------------------- 페이징 처리
		
		int currentPage; // 사용자가 요청한 페이지
		int listCount;   //현재 일반 게시판 총 갯수
		int boardLimit;  //한페이지당 게시글 몇개까지 보여줄것인지
		int pageLimit;   //한 페이지 하단에 보여질 페이지 최대 갯수
		int maxPage;     //마지막 페이지
		int startPage;   // 하단에 보여질 바의 시작수
		int endPage;     //사용자가 요청한 페이지 하단에 보여질 페이징 바의 끝수	
		// * listCount : 현재 일반 게시판 총 게시글 갯수
		listCount = new BoardService().selectListCount();	
		//* current // 사용자가 요청한 페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		pageLimit = 10; //한 페이지에 보여질 게시글.
		boardLimit = 10;//한페이지에 보여질 페이지 갯수
		//*제일 마지막 페이지수
		/*
		 * 총개수 borderLimit        maxPage
		 * 100.0 / 10  => 10.0  =>  10
		 * 101.0 / 10  => 10.1  =>  11
		 * 102.0 / 10  => 10.2  =>  11
		 * 109.0 / 10  => 10.9  =>  11
		 * 
		 * 총 게시글갯수(실수)/boardLimit(10) => 올림처리 => maxPage
		 */
		maxPage = (int)Math.ceil(((double)listCount/boardLimit));
		//startPage
		/*
		 * currentPage, pageLimit 에 영향을 받음.
		 * ex) pageLimit : 10
		 *     startPage : 1 11 21 31 41  ..... => n*10+1;  n*pageLimit+1;  
		 *     currentPage = 1;  n=> 0
		 *     currentPage = 5;  n=> 0
		 *     currentPage = 11; n=> 1
		 *     
		 *    n = currentPage - 1 / pageLimit
		 */
		startPage = ((currentPage-1)/pageLimit)*pageLimit+1;
		
		/*
		 * startPage = 1
		 * startPage = 11
		 */	
		endPage=startPage + pageLimit -1;
		//max 가 만약 13까지 밖에 안된다면 endPage 를 다시 13으로 바꿔야함.
		if(endPage > maxPage) {
			endPage = maxPage;
		}
	//페이징 바를 만들때 필요한 정보들이 담겨있는 pageInfo 객체
		PageInfo pi = new PageInfo(listCount
				                  ,currentPage
				                  ,pageLimit
				                  ,boardLimit
				                  ,maxPage
				                  ,startPage
				                  ,endPage);
		
		//사용자가 요청한 페이지에 뿌려줄 게시글 리스트
		
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/board/boardListView.jsp").forward(request, response);
		
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
