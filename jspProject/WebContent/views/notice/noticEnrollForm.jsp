<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        #enrollForm > table {
            border:1px solid white;
        }
        #enrollForm input ,  #enrollForm textarea {
            width:100%;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
    <div class=outer>
        <br>
        <h2 align="center">공지사항 작성하기</h2>
        <br>
    <form action="<%=getPath%>/insert.no" id="enrollForm" method="post">
    <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
        <table align="center">
            <tr>
                <th width="50">제목</th>
                <td ><input type="text" name="title" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <th><textarea name="content" id="" cols="30" rows="13" required style="resize: none;"></textarea></th>
            </tr>
        </table>
        <br><br>

        <div align="center">
            <button type="button" class="btn btn-secondary btn-sm onclick="history.back();">뒤로가기</button>
            <button type="submit" class="btn btn-success btn-sm" >등록하기</button>
        </div>
        
    </form>
    </div>
</body>
</html>