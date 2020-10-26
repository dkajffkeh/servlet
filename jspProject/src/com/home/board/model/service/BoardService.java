package com.home.board.model.service;
import static com.home.common.JDBCtemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.home.board.model.dao.BoardDao;
import com.home.board.model.vo.Attachment;
import com.home.board.model.vo.Board;
import com.home.board.model.vo.PageInfo;

public class BoardService {

	/** 1-1
	 * @return
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	/**
	 * @param pi
	 * @return
	 */
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn,pi);
		
		close(conn);
		
		return list;
	}

	/** 2. 일반게시판 작성용 service
	 * @param b 제목 내용 카테고리, 작성자회원번호가 담겨있는 Board 객체
	 * @param at 첨부파일 o=> 원본명, 수정명, 폴더 경로가 담겨있는 Attachment 객체 / 첨부파일 x=>null
	 * @return
	 */
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertBoard(b,conn);
		int result2 = 1;
		if(at!=null) {
		
		result2 = new BoardDao().insertAttachment(at,conn);
		
		}
		
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public int increaseCount(int bno) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn,bno);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
			close(conn);
		
		return result;
	}

	public Board selectBoard(int bno) {
		
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn,bno);
		
		close(conn);
		
		return b;
	}

	public Attachment selectAttachment(int bno) {
		
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn,bno);
		
		close(conn);
		
		return at;
	}
	
	

}
