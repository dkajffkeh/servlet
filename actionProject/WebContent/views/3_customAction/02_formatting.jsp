<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>1. fmt:formatNumber</h3>
	<p>
	숫자의 출력 형식을 지정<br>
	- 표현하고자 하는 숫자 데이터의 형식을 통화기호, % 등 원하는 쓰임에 맞춰 지정하는 태그
	</p>
	
	<c:set var="num1" value="123456789"/>
	
	<p>그냥 출력: : ${ num1 } <br>
	   세자리 마다 컴마 찍히게끔--> <fmt:formatNumber value="${num1}"/> <br>
	  있는 그대로 출력 --> <fmt:formatNumber value="${num1}" groupingUsed="false"/>	
	 <!--  -->		
	</p>
	
	<c:set var="num2" value="0.75"/>
	<c:set var="num3" value="50000"/>
	
	percent: : <fmt:formatNumber value="${ num2 }" type="percent" /> <br><br>

	currency : <fmt:formatNumber value="${ num3 }" type="currency"/> <br><br>
	
	currency : <fmt:formatNumber value="${ num3 }" type="currency" currencySymbol="$"/> <br>
	
	<!-- 날짜 및 시간 데이터  -->
	
	<hr>
	
	<h3>2. fmt:formatDate</h3>
	<p>
	날찌 및 시간 데이터 출력형식 지정<br>
	java.util.Date 객체를 사용해야함.
	</p>
	
	<c:set var="current" value="<%= new java.util.Date() %>"/>
	
	그냥 출력: ${ current }
	
	<ul>
	    <li>현재 날짜 : <fmt:formatDate value="${ current }" type="date"/></li> <!-- 기본값 -->
		<li>현재 시간 : <fmt:formatDate value="${ current }" type="time"/></li>
		<li>현재 시간 : <fmt:formatDate value="${ current }" type="both"/></li>
		<li>[full]   : <fmt:formatDate value="${ current }" type="both" dateStyle="full" timeStyle="full"/></li>
		<li>[long]   : <fmt:formatDate value="${ current }" type="both" dateStyle="long" timeStyle="long"/></li>
		<li>[medium] : <fmt:formatDate value="${ current }" type="both" dateStyle="medium" timeStyle="medium"/></li>
		<li>[short] : <fmt:formatDate value="${ current }" type="both" dateStyle="short" timeStyle="short"/></li>
		<li>[custom] : <fmt:formatDate value="${ current }" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/></li>		
		<li>[custom] : <fmt:formatDate value="${ current }" type="both" pattern="HH:mm:ss(a)"/></li>
	</ul>

</body>
</html>