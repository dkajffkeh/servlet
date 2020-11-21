<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>EL 에서의 연산자</h2>
<h3>1. 산술연산자</h3>

 el : ${big + small}
 
 10 + 3 = ${big+small} <br>
 10 - 3 = ${big-small} <br>
 10 * 3 = ${big*small} <br>
 10 / 3 = ${big/small} 또는 ${big div small}<br>
 10 % 3 = ${big%small} 또는 ${big mod small}<br>
 
 <h3>2.대소비교</h3>
 <p>
 10 &gt; 3 = ${ big>small } 또는 ${ big gt small } <br>
 10 &lt; 3 = ${ big>small } 또는 ${ big lt small } <br>
 10 &gt;= 3 = ${ big>=small } 또는 ${ big ge small } <br>
 10 &lt;= 3 = ${ big<=small } 또는 ${ big le small } <br>
 </p>
 
<h3>3. 객체간 비교</h3>

<p>
- sOne 과 sTwo 가 같습니까? : \${ sOne == sTwo } ==> equals 와 같은 동작 <br>
- ${sOne==sTwo} or ${sOne eq sTwo} <br>

- sOne 과 sTwo 가 다릅니까? : \${ sOne != sTwo } ==> equals 와 같은 동작 <br>
- ${sOne!=sTwo} or ${sOne ne sTwo} <br>

-pOne 과 pTwo 가 같습니까? ${pOne==pTwo}
</p> 

<h3>4. 객체가 null 인지 또는 비어있는지 비교 (empty)</h3>

<p>
- p 가 널입니까? ${empty p} <br>
- p 가 널이 아닙니까? ${!empty p} <br>
- list가 비어있습니까? ${ empty list} <br>
- list가 비어있지 않습니까 ? ${!empty list}
</p>
 
 <h3>5. 논리연산자</h3>
 ${ true && true }  ${ true and true }<br>
 ${ true || false } ${ true or false }<br>
 
 
</body>
</html>