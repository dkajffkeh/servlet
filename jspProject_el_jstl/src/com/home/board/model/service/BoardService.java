package com.home.board.model.service;
import static com.home.common.JDBCtemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.home.board.model.dao.BoardDao;
import com.home.board.model.vo.Attachment;
import com.home.board.model.vo.Board;
import com.home.board.model.vo.PageInfo;
import com.home.board.model.vo.Reply;

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

	public int updateBoard(Board b, Attachment at) {
		//b, null => Board Update
		//b, fileNo at => Board Update, Attachment Update
		//b, refBoardNo at => Board Update, Attachment Insert
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn,b);
		
		int result2 = 1;
		if(at!=null) {
			//첨부파일이 있음.
			if(at.getFileNo()!=0) {
				//기존에 파일이 있었음.
				result2 = new BoardDao().updateAttachment(conn,at);
								
			} else {
				//기존에 파일이 없었음
				
				result2 = new BoardDao().insertNewAttachment(conn,at);			
			}				
		}
		
		if(result1 > 0 && result2 >0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1*result2;
	}

	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().inserThBoard(b,conn);
		
		int result2 = new BoardDao().insertAttachmentList(list,conn);
		
		if(result1>0 && result2>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1*result2;
	}

	public ArrayList<Board> selectThumbnailList() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		
		return list;
	}

	public ArrayList<Attachment> selectAttachmentList(int bno) {
		
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn,bno);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(int bno) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn,bno);
		
		close(conn);
		
		return list;
	}

	public int insertPeply(Reply r) {
		
		int result = 0;
		Connection conn = getConnection();
		
		result = new BoardDao().insertReply(conn,r);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		 close(conn);
		
		return result;
	}
	
	

}
