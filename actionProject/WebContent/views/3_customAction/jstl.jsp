<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSTL 이란 </h1>
<p>
JSP Standard Tag Library 의 약자로 
JSP 에서 사용하는 커스텀 액션 태그<br>
공통저긍로 사용하는 유용한 콛의 집합을 보다 쉽게 사용할 수 있도록 <br>
태그화 해서 표준으로써 제공하는것
</p>


<h3>* JSTL 선언 방법</h3>
<p>
JSTL 사용하고자 하는 해당 jsp 페이지 상단에 <br>
taglib 이라는 지시어 사용해서 선언 해야됨.<br><br>

&lt;%@ taglib prefix="접두어" uri="사용하고자하는 라이브러리의 파일상의 uri 주소" %&gt;
</p>

<h4>1.JSTL core Library</h4>
<p>변수와 제어문(조건문,반복문) 등의 로직과 관련된 문법을 제공</p>
<a href="01_core.jsp">core Library</a>

<h4>2. JSTL formatting Library</h4>
<p>숫자, 가격, 날짜, 시간.. 형식을 지정할때 사용하는문법 제공</p>
<a href="02_formatting.jsp">formatting Library</a>

<h4>3.JSTL function Library</h4>
<p>EL 안에서 사용할 수 있는 메소드들을 제공하는 태그</p>

<a href="03_function.jsp">function Library</a>

</body>
</html>