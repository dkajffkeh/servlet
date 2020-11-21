<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
해당 이페이지로 바로 요청시에 전달되는 값들은 내부적으로 param 영역에 저장되어있음. <br>
</p>
<h2>주문 내역은 다음과 같습니다.</h2>
<p>
상품명:${param.pname}<br>
수량:${param.pcount}<br>
옵션1:${paramValues.option[0]}<br>
옵션2:${paramValues.option[1]}<br>
</p>
</body>
</html>