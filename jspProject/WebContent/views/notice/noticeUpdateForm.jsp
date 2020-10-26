<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.home.notice.model.vo.Notice"  %>

<%
Notice n = (Notice)request.getAttribute("n");

%>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .outer {
            background:black;
            color:white;
            width:800px;
            height:500px;
            margin:auto;
            margin-top: 50px;
        }
        #updateForm > table {
            border:1px solid white;
        }
        #updateForm input ,  #updateForm textarea {
            width:100%;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
    <div class=outer>
        <br>
        <h2 align="center">공지사항 수정하기</h2>
        <br>
    <form action="<%=getPath%>/update.no" id="updateForm" method="post">
    <input type="hidden" name="nno" value="<%=n.getNoticeNo()%>">
			<table align="center">
				<tr>
					<th width="50">제목</th>
					<td><input type="text" name="title" required
						value="<%=n.getNoticeTitle()%>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<th><textarea name="content" id="" cols="30" rows="10"
							required style="resize: none;"><%=n.getNoticeContent() %></textarea></th>
				</tr>
			</table>
			<br><br>

        <div align="center">
            <button type="button" onclick="history.back();">뒤로가기</button>
            <button type="submit" >변경하기</button>
        </div>
        
    </form>
    </div>
</body>
</html>