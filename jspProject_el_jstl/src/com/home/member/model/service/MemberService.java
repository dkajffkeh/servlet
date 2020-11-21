package com.home.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.home.member.model.dao.MemberDao;
import com.home.member.model.vo.Member;

import static com.home.common.JDBCtemplate.*;

public class MemberService {
/**   alt shift j
 * 1.로그인용 서비스메소드.
 * @param userId 사용자가 입력한 아이디값
 * @param userPwd 사용자가 입력한 비밀번호값.
 * @return 해당 아이디와 비밀번호가 일치하는 조회된 회원객체 / 없으면 null
 */
	public Member loginMember(String userId,String userPwd) {
		// TODO Auto-generated method stub
		
		Connection conn = getConnection();
		
		Member loginUser = new MemberDao().loginMember(userId,userPwd,conn);
		
		close(conn);
		
		return loginUser;
		
	}

/**
 * 2. 회원가입용 서비스
 * @param m 사용자가 입력한 아이디,비밀번호,이름,전화번호,이메일,주소,취미가 담겨있음.
 * @return
 */
public int insertMember(Member m) {

	Connection conn = getConnection();
	
	int result = new MemberDao().insertMember(m,conn);
	
	if(result>0) {
		
		commit(conn);
		
	} else {
		
		rollback(conn);
		
	}
	return result;
}

/**
 * 
 * 3.회원 정보 수정용 서비스
 * @param m 변경할 내용들+변경한 요청회원의 아이디가 담겨있는 객체
 * @return 갱신된 회원 객체 //null 값이 반환될 수 있음.
 */
public Member updateMember(Member m) {
	// TODO Auto-generated method stub
	Connection conn = getConnection();
	int result = new MemberDao().updateMember(m,conn);

	Member updateMem = null;
	if(result>0) { //update 성공 했을경우.
		commit(conn);
		// 갱신된 회원 다시 조회 해오기
		// 다시 select문 실행
		updateMem = new MemberDao().selectMember(conn,m.getUserId());

	} else {
		rollback(conn);
	}

	close(conn);

	return updateMem;

}

/**
 * @param userId 변경요청한 아이디
 * @param userPwd 현재 비밀번호
 * @param updatePwd 변경할 비밀번호
 * @return 
 */
public Member updatePwd(String userId, String userPwd, String updatePwd) {

	Connection conn = getConnection();

	
	
	int result = new MemberDao().updatePwd(userId,userPwd,updatePwd,conn);
	
	Member updateMem = null;
	
	if(result>0) { //성공
		commit(conn);
		
		updateMem = new MemberDao().selectMember(conn, userId);
		
	} else { //실패
		rollback(conn);
	}
	

	return updateMem;

}

public int deleteMember(String userId, String userPwd) {
	
	
	Connection conn = getConnection();
	
	int result = new MemberDao().deleteMember(userId, userPwd, conn);
	
	if(result>0) {
		commit(conn);
	} else {
		rollback(conn);	
	}
	
	close(conn);
	
	return result;
	
	
}

public int idCheck(String checkId) {
	
	Connection conn = getConnection();
	
	int count = new MemberDao().idCheck(conn, checkId);
	
	close(conn);
	
	return count;
}

}
