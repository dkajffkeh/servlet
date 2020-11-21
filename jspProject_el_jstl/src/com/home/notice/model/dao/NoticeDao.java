package com.home.notice.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.home.notice.model.vo.Notice;

import static com.home.common.JDBCtemplate.close;

public class NoticeDao {

	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String path = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	public ArrayList<Notice> selectNoticeList(Connection conn) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList();
		String sql = prop.getProperty("selectNoticeList");
		
		
		try {
			 stmt = conn.createStatement();
			   rs = stmt.executeQuery(sql);
			   
			   while(rs.next()) {
				   list.add(new Notice(rs.getInt("NOTICE_NO")
						              ,rs.getString("NOTICE_TITLE")
						              ,rs.getString("USER_ID")
						              ,rs.getInt("COUNT")
						              ,rs.getDate("CREATE_DATE")
						              )
						   );
			   }
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}

	public int insertNotice(Connection conn, Notice n) {
		
		PreparedStatement pstmt = null;
		int result=0;
		
		String sql=prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeWriter());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int increaseCount(Connection conn, int nno) {
		//update문 처리된 행수 
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice selectNotice(Connection conn, int nno) {

		Notice n = new Notice();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				n = new Notice(rs.getInt("NOTICE_NO")
						      ,rs.getString("NOTICE_TITLE")
						      ,rs.getString("NOTICE_CONTENT")
						      ,rs.getString("USER_ID")
						      ,rs.getDate("CREATE_DATE")
						      ,rs.getInt("COUNT")
						      );
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return n;
	}

	public int noticeUpdate(Connection conn, Notice n) {
		int result = 0 ;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("noticeUpdate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteNotice(Connection conn, int nno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
