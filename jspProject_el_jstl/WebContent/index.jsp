<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="/views/common/menubar.jsp" %>

<%-- -- 
	1. 회원서비스.
		      | C insert R select U update D delete
	==========================================
	    1. 로그인 ==> R
	    2. 회원가입 ==> C
	      [아이디 중복 체크]  => ajax 기술이 필요함.  ==> R
		3.마이페이지    ==> R
		4.정보수정   ==> U
		5.회원탈퇴   ==> U    회원 탈퇴시 일정시간동안 정보를 갖고있어야함.
		
	2. 공지사항 서비스
		공지사항 list 조회(R)/상세조회(R)/작성(C)/수정(U)/삭제(D)
	
	3. 일반게시판 서비스
		게시판 리스트조회(R)/상세조회(R)+첨부파일 업로드/작성하기(C)/수정(U)/삭제(D)
		첨부파일 업로드 , 게시판 수정, 
	
	[댓글리스트 조회(R)]/댓글 작성하기(C)
	
	4. 사진게시판 서비스
		썸네일리스트 조회(R)
		상세조회(R)
		게시판작성하기(C)
		첨부파일(다중)업로드
 --%>
 
</body>
</html>