<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="str" value="How are you?"/>
	
	${ str } <br>
	
	문자열의 길이 : ${ str.length() } <br>
	문자열의 길이 : ${ fn:length(str) }<br>
	
	모두 대문자로 출력 : ${ str.toUpperCase() }   <br>
	모두 대문자로 출력 : ${ fn:toUpperCase(str) } <br>	
	모두 소문자로 출력 : ${ fn:toLowerCase(str) } <br>
	
	are의 시작인덱스 : ${ str.indexOf('are') } <br>
	are의 시작인덱스 : ${ fn:indexOf(str,'are') } <br>
	
	are => were : ${ fn:replace(str,'are','were') } <br>
	replace 후 str : ${str} <br>
	
	<c:if test="${ fn:contains(str,'you') }">
		<b>포함되어있음</b>
	</c:if>
	
	
</body>
</html>