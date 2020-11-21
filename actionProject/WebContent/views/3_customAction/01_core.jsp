<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.home.model.vo.*" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Core Library Test</h1>

	<h3>1.변수</h3>
	<p>
	* 변수 선언 (c:set var="변수명" value="초기값" [scope="지정"]) <br>
	- 변수 선언 및 초기값을 대입하는 기능을 제공<br>
	- 변수 선언시 scope 지정 가능 (지정 안할시 기본 scope 는 page스코프) <br>
	- 즉 해당 scope setAttribute 를 통해 key-value 형태로 값을 담아놓는 거라고 생각하면 됨.	<br>

	*주의사항<br>
	-변수 타입은 별도로 선언하지 않음	<br>
	-초기값은 반드시 지정해야됨
	-c:set 을 통해 선언한 변수는 EL을 이용해서 접근가능!! (스크립팅원소로는 접근 불가)
	</p>

	<c:set var="num1" value="10" scope="page"/>
	<c:set var="num2" value="20" scope="request"/>
	
	num1 값 : ${pageScope.num1} <br>
	num2 값 : ${requestScope.num2} <br>
	
	<c:set var="result" value="${ num1 + num2 }" scope="session"/> 
	${ num1 } + ${ num2 } = ${ result } <br>  <!-- 잘못쳐도 오류난게 아님 그냥 출력이 안됨. -->
	
	<c:set var="colors" scope="request">
	red, blue, yellow, pink, green
	</c:set>
	
	colors값 : ${ colors }
	
	<p>
	*변수 삭제(c:remove var="삭제하고자하는 변수명" [스코프]) <br>
	- 해당 scope에서 내가 지정한 변수를 찾아서 삭제하는 테그<br>
	- 만일 scope 를 지정하지 않을 시 모든 영역에서 다 삭제됨 <br>
	- 즉 해당 scope에 removeAttribute 를 통해 삭제하는거라고 생각하면 됨.<br>
	</p>
	
	<c:set var="result" value="9999" scope="request"/>
	삭제전 result <br>
	result = ${result} <br><br>
	
	<c:remove var="result" scope="request"/>
	삭제후 result <br>
	result = ${result} <br><br>
	
	<c:remove var="result"/>
	삭제후 result <br>
	result = ${result} <br><br>
	
	<p>
	*값(데이터) 출력 (c:out value="출력하고자 하는 값" [escapeXml = "true | false"] [default="기본값"]) <br>
	-데이터를 출력하고자 할 때 사용하는 태그
	</p>
	
	<c:set var="outTest"><b>아웃테스트</b></c:set>
	
	<c:out value="${outTest}"/> <br><br>
	
	<c:out value="${outTest}" escapeXml="false"/> <br><br>
	
	<c:out value="${result}" default="값이 없습니다."/><br>
	
	<hr>
	<h3>2.조건문 - IF(c:if test "조건식") 존나중요</h3>
	<p>
	- JAVA의 단일 if문과 비슷한 역할을 수행해주는 태그
	- 조건식은 EL 구문으로 기술해야함
	</p>
	
	<!-- num1 = 10 , num2 = 20  -->

	<c:if test="${ num1 gt num2 }">
	<b>num1 이 더 큽니다</b>
	</c:if>
	
	<c:if test="${ num1 le num2 }">
	<b>num2 가 더 큽니다</b> <br>
	</c:if>
	
	<c:set var="str" value="안녕하세요"/>
	
	<c:if test="${ str eq '안녕하세요' }">
	<b>참입니다</b>
	</c:if>
	
	<c:if test="${ str ne '안녕하세요' }">
	<b>Bye World</b>
	</c:if>
	
	<br>
	
	<h3>3.조건문 - Choose (c:choose, c:when test="조건식", c:otherwise)</h3>
	<p>
	- JAVA 의 if-else, if-else if문 또는 switch문과 비슷한 역할을 하는 태그<br>
	- 각 조건들을 c:choose의 하위 요소로서 c:when 을 통해 작성 (else 또는 default 문 같은건 otherwise)
	</p>
	
	<c:choose>
		<c:when test="${ num1 eq 40 }">
			<b>when Test</b>
		</c:when>
		<c:when test="${ num1 eq 15 }">
		    <b>num1 의 값은 10입니다.</b>
		</c:when>
		<c:otherwise>
		 <b>아무것도 해당이 되지 않습니다.</b>
		</c:otherwise>
	</c:choose>
	
	<!-- 반복문! -->
	<h3>4.반복문 - forEach</h3>
	<p>
	(c:forEach var="변수명" begin="초기값" end="끝값" [step="반복시 증가값"]) <br>
	(c:forEach var="변수명" items="순차적으로 접근할 객체(배열 또는 리스트)" [varStatus="순차적으로 접근되는 요소의 상태값을 매번 보관할 변수명"]) <br>
	--JAVA for문에 해당하는 기능.
	</p>
	
	<c:forEach var="i" begin="1" end="10">
	<b>반복확인 ${ i }</b> <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="6">
	<h${ i }>실행</h${ i }>
	</c:forEach>
	
	<br><br>
	<!-- colors : red, yellow, -->
	
	<c:forEach var="c" items="${ colors }">
	<font color="${ c }">반복확인</font>
	</c:forEach>
	
	<ul>
		<c:forEach var="c" items="${ colors }" >
		<li style="background:${c}">목록</li>
		</c:forEach>
	</ul>
	
	<%
	ArrayList<Person> pList = new ArrayList<Person>();
	pList.add(new Person("홍길동",30,"M"));
	pList.add(new Person("홍남기",25,"F"));
	pList.add(new Person("강호동",29,"M"));
	request.setAttribute("pList", pList);
	%>
	
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>	
		</thead>
		<tbody>
		<c:forEach var="i" items="${ pList }" varStatus="status">
			<tr>
				<td>${ status.index }</td>
				<td>${ i.name }</td>
				<td>${ i.age }</td>
				<td>${ i.gender }</td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	
	
	<h3>5. 반복문 - forTokens (c:forTokens var="변수명" items="문자열" delims="구분자")</h3>
	<p>
	- 문자열에 포함된 구분자를 통해 가각의 토큰으로 분리 후 반복 처리 <br>
	- JAVA split("구분자")
	</p>
	
	<c:set var="device" value="컴퓨터,에어컨|TV>냉장고_세탁기"/>
	
	<ul>
		<c:forTokens var="d" items="${ device }" delims="',|>_'">
		<li>${ d }</li>
		</c:forTokens>
	</ul>
	
	<h3>6.쿼리스트링</h3>
	<p>
	  - url 경로를 생성하고, 해당 url 에 쿼리스트링을 정의할 수 있는 태그 <br><br>
	  
	  c:url var="생성된 url을 담을 변수명" value="요청할 url" <br>
	  	c:param name="키값" value="전달할값"/
	  	c:param name="키값" value="전달할값"/
	  	c:param name="키값" value="전달할값"/	
	  c:url    
	</p>
	
	<c:url var="url" value="list.do" >
	<c:param name="cPage" value="1"/>
	<c:param name="num" value="2"/>
	</c:url>
	
	<a href="${url}">c:url 사용방식</a>
	
</body>
</html>