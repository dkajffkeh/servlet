<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>    
<%@ page import="com.home.board.model.vo.Board"%>
    <%
    ArrayList<Board> list = (ArrayList)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .outer{
            background-color: black;
            color:white;
            width:900px;
            height:800px;
            margin: auto;
            margin-top:50px;   
        }
        .list-area{
            width: 760px;
            margin: auto;
        }
        .thumbnail{
            border:1px solid white;
            width:220px;
            display: inline-block;
            margin:13px;
        }
        .thumbnail:hover{
            cursor:pointer;
            opacity:0.7;
        }
    </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
<div class="outer">
    <br>
    <h2 align="center">사진게시판</h2>
    <br>
    <!-- 현재 로그인 되어있을 경우 -->
    <% if(loginUser!=null) { %>
    <div align="right" style="width:800px">
        <a href="<%=getPath%>/enrollForm.th" class="btn btn-secondary btn-sm">글작성</a>
        <br><br>
    </div>
	<% } %>
	
    <div class="list-area">
    <%for(Board b : list){ %>
        <div class="thumbnail" align="center">
        <input type="hidden" value="<%=b.getBoardNo() %>">
            <img src="<%=getPath%>/<%=b.getTitleImg()%>" alt="" width="200" height="150">
            <p>
            No.<%=b.getBoardNo() %> <%=b.getBoardTitle()%> <br>
            조회수:<%=b.getBoardCount() %>    
            </p>
        </div>
        <%} %>
    </div>
<script>
$(function(){
	$(".thumbnail").click(function(){
		
		location.href="<%=getPath%>/detail.th?bno=" + $(this).children().eq(0).val();
		
		
	});
});
</script>
</div>
</body>
</html>
