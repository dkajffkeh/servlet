package com.home.member.model.dao;

import static com.home.common.JDBCtemplate.close;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.home.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao(){
		 // String path = MemberDao.class.getResource("/sql/member/member-mapper.properties").getPath();
			String path = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		try {
		//	prop.load(new FileInputStream(path));
			prop.loadFromXML(new FileInputStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Member loginMember(String userId, String userPwd, Connection conn) {
		
		// select => 한 행=> Member 변수에 담을 수 있음
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//지금 미완성된 sql 문 담은채로 작성돼있음.
			
			pstmt.setString(1, userId); //admin
			pstmt.setString(2, userPwd); // '1234'
			rs = pstmt.executeQuery(); //select 문 같은경우는 execueQuery (반환형 result Set) //dml -> executeQuery(반환형 int)
			
			if(rs.next()) {
				m = new Member(rs.getInt("USER_NO"),
						       rs.getString("USER_ID"),
						       rs.getString("USER_PWD"),
						       rs.getString("USER_NAME"),
						       rs.getString("PHONE"),
						       rs.getString("EMAIL"),
						       rs.getString("ADDRESS"),
						       rs.getString("INTEREST"),
						       rs.getDate("ENROLL_DATE"),
						       rs.getDate("MODIFY_DATE"),
						       rs.getString("STATUS"));					
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return m; //일치하는 회원 찾았을 경우 생성된 Member 객체 반환
		
		
	}

	public int insertMember(Member m, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//완성형태로 만들고  실행.
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getUserPhone());
			pstmt.setString(5, m.getUserEmail());
			pstmt.setString(6, m.getUserAddress());
			pstmt.setString(7, m.getUserInterest());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
		
		
	}

	public int updateMember(Member m, Connection conn) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getUserPhone());
			pstmt.setString(3, m.getUserEmail());
			pstmt.setString(4, m.getUserAddress());
			pstmt.setString(5, m.getUserInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
			
		
		
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMember");
		Member m = null;	
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m = new Member(rs.getInt("USER_NO"),
						       rs.getString("USER_ID"),
						       rs.getString("USER_PWD"),
						       rs.getString("USER_NAME"),
						       rs.getString("PHONE"),
						       rs.getString("EMAIL"),
						       rs.getString("ADDRESS"),
						       rs.getString("INTEREST"),
						       rs.getDate("ENROLL_DATE"),
						       rs.getDate("MODIFY_DATE"),
						       rs.getString("STATUS"));					
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return m;
	}

	public int updatePwd(String userId, String userPwd, String updatePwd, Connection conn) {
		
		int result = 0;
		
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}

	public int deleteMember(String userId, String userPwd, Connection conn) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
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
