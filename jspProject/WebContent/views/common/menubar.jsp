<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.home.member.model.vo.*" %>
    <%
    Member loginUser = (Member)session.getAttribute("loginUser");
    String alertMsg = (String)session.getAttribute("alertMsg");
    //> 서비스 요청 전 null
    // 요청후 alert 로 띄워줄 메세지문구
    String getPath = request.getContextPath();
    %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        #loginForm,
        #userInfo {
            float: right;
           
        }

        #userInfo a {
            color: black;
            text-decoration: none;
            font-size: 12px;
            cursor: pointer;            
        }

        .navWraper {
            background-color: black;
            display: flex;
            justify-content: center;
            margin-bottom:50px
        }
        .menu {
            display: table-cell;
            text-align:center;
            height: 50px;
            width: 150px;        
        }

        .menu a {
            text-decoration: none;
            color: white;
            font-weight: bold;
            display: block;
            width: 100%;
            height: 100%;
            font-size: 1.4rem;
            line-height: 50px;
        }

        .menu a:hover {
            background: darkgray;
        }
    </style>
</head>

<body>

	<%
	if(alertMsg != null){
	%>
	
<script>
alert("<%= alertMsg %>");  
</script>
	<!-- 1회성 메세지이기 때문에 한번 띄우고 session 을 비워야함 -->
	<% session.removeAttribute("alertMsg"); %>
	<% }  %>
	

    <h1 align="center">Welcome JSP World</h1>
    <!-- 로그인 전에 보여지는 로그인 폼. -->
   <% if(loginUser==null) { %>
	<div class="loginArea">
		<form action="<%=getPath%>/login.me" id="loginForm" method="POST">
						 <%-- /jsp  --%>
			<table>
				<tr>
					<th><label for="userId">아이디</label></th>
					<th><input type="text" id="userId" name="userId" required></th>
				</tr>
				<tr>
					<th><label for="userPwd">비밀번호</label></th>
					<th><input type="password" id="userPwd" name="userPwd"
						required></th>
				</tr>
				<tr>
					<th colspan="2">
						<button type="submit">로그인</button>
						<button type="button" onclick="enrollPage()">회원가입</button>
					</th>
				</tr>
			</table>
			<script>
				function enrollPage() {
						// location.href = "/jsp/views/member/memberEnrollForm.jsp";
						// 바로 페이지 요청하게 되면 => 보안상 좋지 않음.
				
						//단순한 정적인 페이지 요청에 있어서는 반드시 servlet 으로 요청한후 forwarding 방식으로 응답할 것!
						//=> url 에는 servler 맵핑값만 남아있게됨.
						
						location.href = "<%=getPath%>/enrollForm.me";
				}
			</script>
		</form>
	</div>
	<% } else { %>
	 <!-- 로그인 성공수 보여질 div -->
   
    <div id="userInfo">
        <b><%= loginUser.getUserName() %></b> 님 의 방문을 환영합니다
        <div align="center">
            <a href="<%=getPath%>/myPage.me">마이페이지</a>
            <a href="<%=getPath%>/logout.me">로그아웃</a>
        </div>
    </div>
  <% } %>
    

	<br clear="both">
    <br>

    <div class="navWraper">
        <div class="menu"><a href="<%=getPath%>">Home</a></div>
        <div class="menu"><a href="<%=getPath%>/list.no">공지사항</a></div>
        <div class="menu"><a href="<%=getPath%>/list.bo?currentPage=1">일반게시판</a></div>
        <div class="menu"><a href="">사진게시판</a></div>
    </div>

   
</body>

</html>