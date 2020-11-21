<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>Include</h3>
<p>다른 페이지를 포함하고자 할때 쓰는 방식</p>

<h4>1. 기존의 include 지시어 이용한 방식 (정적 include 방식)</h4>

<%-- 

<%@ include file="footer.jsp" %> <br><br>

특징 : include 한 페이지에서 scriptlet 으로 선언된 변수를 여기서도 사용할 수 있음<br>
<%=year%><br>

단, 문제점 : 현재 페이지에서 동일한 이름의 변수명으로 선언 불가

--%>

<h4>2.JSP 표준액션 태그를 이용한 방식(동적 include 방식 == 런타임시 포함되는 형태)</h4>

<jsp:include page="footer.jsp"/> <br><br>

특징1: include 한 페이지에 선언된 변수를 공유하지 않음! <br>
include한 페이지의 year변수값  공유하지 않음<br>

특징2 : include할 때 포함시킬 페이지로 값 전달할 수 있다. <br><br>
<jsp:include page="footer.jsp">
<jsp:param name="test" value="Hello Wrold"/>
</jsp:include>

<br><br>

<jsp:include page="footer.jsp">
<jsp:param name="test" value="Bye World"/>
</jsp:include>


</body>
</html>