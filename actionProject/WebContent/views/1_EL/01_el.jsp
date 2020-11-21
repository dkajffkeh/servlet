<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.home.model.vo.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <h3>1. 기존방식의 스크립틀릿.</h3>

<%
	String classRoom = (String)request.getAttribute("classRoom");
	Person student = (Person)request.getAttribute("student");
	
	
	String business = (String)session.getAttribute("business");
	Person teacher = (Person)session.getAttribute("teacher");
%>

<p>
	학원명:<%=business%><br>
	강의장:<%=classRoom%><br>
	강사 :<%=teacher.getName()%><br>
	
	수강생정보
	<ul>
		<li>
		이름:<%=student.getName()%>
		</li>
		<li>
		나이:<%=student.getAge()%>
		</li>
		<li>
		성별:<%=student.getGender()%>
		</li>	
	</ul>
</p>
--%>
<h3>2. EL을 이용해서 보다 쉽게 각 영역에 저장된 값들 출력하기</h3>
<p>
EL은 getXXXX을 통해 값을 빼올 필요 없이 단지 키값만 제시해서 바로 접근 가능<br>
내부적으로 해당 영역에 getXXXX을 자동적으로 실행되면서 해당 키에 저장된 밸류값을 읽음.<br><Br>


EL은 request, session 등 jsp 내장객체를 구분하지 않아도<br>
알아서 해당 영역들에 해당 키값을 검색해서 존재하는 경우 해당 벨류값 가져옴<br>
</p>

<p>
학원명:${business}<br>
강의장:${classRoom}<br>
강사: ${teacher.name}<br>
 <%-- teacher 에 접근하면 value 값은 person 임 하지만 원리는 내부적으로 getter 메소드가 실행되는거임. --%>
</p>

수강생정보
	<ul>
		<li>
		이름:${student.name}
		</li>
		<li>
		나이:${student.age}
		</li>
		<li>
		성별:${student.gender}
		</li>	
	</ul>
	
	
	<h3>동일한 키값으로 저장된게 있을경우</h3>
	<p>scope 값: ${scope}</p>
	
	<%--
	같은 key 값일 경우 공유범위가 가장 작은거부터 찾음. request==>session==>application => 만약 없으면 아무것도 출력 안됨.
	 --%>
	<h3>4.특정 영역을 제시해서 자장된 값 출력하기</h3>
	
	<%
	//pageScope 에 담기
	pageContext.setAttribute("scope", "page");	
	%>
	
	<p>
	pageScope에 담긴 값 : ${pageScope.scope}<br>
	Session 에 담긴 값:${sessionScope.scope}<br>
	request 에 담긴 값:${requestScope.scope}<br>
	Application 에 담긴 값: ${applicationScope.scope}<br>	
	</p>
	
	
</body>
</html>