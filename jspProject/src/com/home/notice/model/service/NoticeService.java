package com.home.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.home.notice.model.dao.NoticeDao;
import com.home.notice.model.vo.Notice;
import static com.home.common.JDBCtemplate.*;

public class NoticeService {
	
	public NoticeService() {
		
	}

	/**1.조회된 공지사항 List
	 * @return
	 */
	public ArrayList<Notice> selectNoticeList() {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		close(conn);
	
		return list;
	}

	/**2. 공지사항 작성용 서비스
	 * @param n
	 * @return
	 */
	public int insertNotice(Notice n) {

		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn,n);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		 
		return result;
	}
	
	/**3-1. 공지사항 조회수 증가용 Service
	 * @param nno  상세조회 요청한 해당 공지사항 글 번호
	 * @return
	 */
	public int increaseCount(int nno) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn,nno);		
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Notice selectNotice(int nno) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn,nno);
		
		close(conn);
		
	
		
		return n;
	}

	public int noticeUpdate(Notice n) {

		Connection conn = getConnection();
				
		int result = new NoticeDao().noticeUpdate(conn,n);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteNotice(int nno) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn,nno);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

}
