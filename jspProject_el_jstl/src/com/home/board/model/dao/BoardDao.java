package com.home.board.model.dao;

import java.io.FileInputStream;
import static com.home.common.JDBCtemplate.*;
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

import com.home.board.model.vo.Attachment;
import com.home.board.model.vo.Board;
import com.home.board.model.vo.PageInfo;
import com.home.board.model.vo.Reply;

public class BoardDao {

	private Properties prop = new Properties();
	
	public BoardDao() {
		
		try {
			
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int selectListCount(Connection conn) {
		
		Statement st = null;
		ResultSet rs = null;
		int listCount = 0;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
			listCount =	rs.getInt("LISTCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(st);
		}
		
		
		return listCount;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectList");
		
		
		
		/*
		 * boardLimit : 10 라는 가정하에
		 * currentPage = 1 => startRow 1 endRow :10
		 * currentPage = 2 => startRow 11 endRow :20
		 * currentPage = 3 => startRow 21 endRow :30
		 * 
		 * startRow = (currentPage-1)* boardLimit + 1
		 * endRow = startRow + boardLimit -1;
		 */
		
		int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				list.add(new Board(rs.getInt("BOARD_NO")
						          ,rs.getString("CATEGORY_NAME")
						          ,rs.getString("BOARD_TITLE")
						          ,rs.getString("USER_ID")
						          ,rs.getInt("COUNT")
						          ,rs.getDate("CREATE_DATE")));
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
	
		
		return list;
	}

	public int insertBoard(Board b, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Attachment at, Connection conn) {
		
		// insert문 => 처리된 행 수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
 		 
		return result;
	}

	public int increaseCount(Connection conn, int bno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
	
		return result;
	}

	public Board selectBoard(Connection conn, int bno) {
		
		Board b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				b= new Board(rs.getInt("BOARD_NO")
						    ,rs.getString("CATEGORY_NAME")
						    ,rs.getString("BOARD_TITLE")
						    ,rs.getString("BOARD_CONTENT")
						    ,rs.getString("USER_ID")
						    ,rs.getDate("CREATE_DATE"));
							
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return b;
	}

	public Attachment selectAttachment(Connection conn, int bno) {
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
		    if(rs.next()) {
		    	at = new Attachment(rs.getInt("FILE_NO")
		    					   ,rs.getString("ORIGIN_NAME")
		    					   ,rs.getString("CHANGE_NAME")
		    					   ,rs.getString("FILE_PATH"));
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return at;
	}

	public int updateBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3,b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);		
		}

		
		return result;
	}

	public int updateAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, at.getOriginName());
		    pstmt.setString(2, at.getChangeName());
		    pstmt.setString(3, at.getFilePath());
		    pstmt.setInt(4, at.getFileNo());
		    
		    result = pstmt.executeUpdate();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertNewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getRefboardNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
		    result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}

	public int inserThBoard(Board b, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("inserThBoard");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
					close(pstmt);
					
		}

		return result;
	}

	public int insertAttachmentList(ArrayList<Attachment> list, Connection conn) {
		// 여러번의 insert문
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentList");
		try {
	
			for(Attachment at : list) {
		//반복문 돌때마다 미완성된 sql문을 담은 pstmt 객체 생성.
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			
			result = pstmt.executeUpdate();
			if(result==0) {
				return 0;
			}
	    }
		
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public ArrayList<Board> selectThumbnailList(Connection conn) {
		
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectThumbnailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {			
			Board b = new Board(rs.getInt("BOARD_NO")
							   ,rs.getString("BOARD_TITLE")
							   ,rs.getInt("count")
							   ,rs.getString("titleimg"));
				
			list.add(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Attachment> selectAttachmentList(Connection conn, int bno) {
		
		ArrayList<Attachment> list = new ArrayList<>();
		// select문 => 여러행이 조회됨.
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Attachment at = new Attachment();
				
				at.setChangeName(rs.getString("CHANGE_NAME"));
				at.setFilePath(rs.getString("FILE_PATH"));				 
				
				list.add(at);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int bno) {
		
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply r = new Reply(rs.getInt("REPLY_NO")
								   ,rs.getString("REPLY_CONTENT")
								   ,rs.getInt("REF_BNO")
								   ,rs.getString("USER_NAME")
								   ,rs.getDate("CREATE_DATE"));
				list.add(r);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return list;
	}

	public int insertReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBno());
			pstmt.setInt(3, Integer.parseInt(r.getReplyWriter()));
			
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
