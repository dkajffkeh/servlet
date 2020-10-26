<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.home.notice.model.vo.Notice" %>    
    
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
            margin-top:50px;
        }
        #detail-area{border-color:white;}
    </style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
    
    <div class="outer">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>

        <table id="detail-area" align="center" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="300"><%= n.getNoticeTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=n.getNoticeWriter() %></td>
                <th>작성일</th>
                <td><%=n.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height:150px">
                        <%= n.getNoticeContent() %>
                    </p>
                </td>
            </tr>
        </table>
        <br><br>

        <div align="center">
            <a href="<%=getPath%>/list.no" class="btn btn-secondary btn-sm">목록가기</a>
            <% if(loginUser!=null&&loginUser.getUserId().equals(n.getNoticeWriter())){ %>
            <a href="<%=getPath%>/updateForm.no?nno=<%=n.getNoticeNo()%>" class="btn btn-warning btn-sm">수정하기 페이지</a>
            <a href="<%=getPath%>/delete.no?nno=<%=n.getNoticeNo()%>" class="btn btn-danger btn-sm">삭제하기</a>
             <% } %>
            <!-- ------------------------------- -->
        </div>
    </div>


</body>
</html>