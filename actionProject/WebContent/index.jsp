<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>*EL(Expression Language)</h1>
<p>
	기존 사용했던 표현식(출력식) &lt;%=name %&gt; 와 같이 JSP 에 표현하고자 하는 코드를 EL 로 표현함 <br>
	\${name} 의 형식으로 표현하여 작성.
</p>

<h3>1.EL 기본 구문에 대해서 먼저 배워보자!!</h3>
<h3>${scope}</h3>
<a href="el.do">01_EL</a>

<h3>2.EL(Param)</h3>
<p>
JSP에 서블릿 요청을 통해 응답 JSP 로 갔지만<br>
현재 JSP에서 또다른 JSP 요청 가능.(요청시 전달값도 전달 가능.) <br>
권장 X (왜? 디렉토리 구조가 노출되기 때문에)
</p>

<form action="views/1_EL/02_elParam.jsp">
<fieldset>
	<input type="text" name="pname" placeholder="제품명입력"> <br>
	<input type="number" name="pcount" placeholder="수량입력"> <br>
	<input type="text" name="option" placeholder="옵션1입력"> <br>
	<input type="text" name="option" placeholder="옵션2입력"> <br>
	<input type="submit" value="02_EL(param)">
</fieldset>
</form>

<h3>3. EL(연산자)</h3>
<a href="operation.do">03_EL(연산자)</a>

<%-- 
*JSP 를 이루는 구성 인자들. 
1. 스크립팅 원소  : JSP 페이지에서 자바 코드를 직접 기술할 수 있게끔 하는 기능
				ex) 스크립틀릿 표현식
2. 지시어 : JSP 페이지 정보에 대한 내용을 기술하거나 다른 jsp 를 표함하고자 할 때 사용
3. JSP 액션태그 (표준 액션태그,커스텀 액션태스)
	XML 기술을 이용하여 JSP 문법을 확장하는 기술들을 제공하는 테그  
	>> 표준 액션테그(Standard Action) : JSP 페이지에서 바로 사용 가능 (별도의 연동 없이)
									모든 태그들의 이름앞에 jsp: 라는 접두어 붙음
	
	>> 커스텀액션태그(Custom Action)  : JSP페이지에서 바로 사용불가능(별도의 라이브러리 설치가 필요함)		// 이용 굉장히 중요함.						
									모든 태그들의 이름앞에 jsp : 외에 접두어들이 붙음 가장 유용한 라이브러리.
									(c: fn: fmt: ....) 대표적으로 JSTL 사용함.
--%>

<h1>JSP Action Tag</h1>
<p>XML 기술을 이용하여 기존의 JSP 문법을 확장하는 기술을 제공하는 태그들</p>

<h3>1. 표준 액션 태그(Standard Action Tag)</h3>
<p>
JSP 페이지에서 별도의 라이브러리 연동 없이 바로 사용 가능하고 태그 앞에 jsp:접두어가 붙음
</p>

<a href="views/2_standardAction/01_include.jsp">Include</a>
<br>

<a href="forward.do">forward</a><br><br>

<h3>커스텀 액션테그(jstl)</h3>
<a href="views/3_customAction/jstl.jsp">JSTL</a>



</body>
</html>