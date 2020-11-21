<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.home.board.model.vo.Board" %>  
<%@ page import="com.home.board.model.vo.Attachment" %>  

    <% 
    
    Board b = (Board)request.getAttribute("b");
    ArrayList<Attachment> list = (ArrayList)request.getAttribute("list");
    
    Attachment titleImg = list.get(0); // 대표이미지에 대한 이미지는 뽑아놓기.
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
 <style>
     .outer {
         background-color: black;
         color:white;
         width:900px;
         height:800px;
         margin:auto;
         margin-top:50px;
     }
     .detailArea{
         border:1px solid white;
     }
     .detailArea td {
        border:1px solid white;
     }
 </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
  <div class="outer">
      <br>
      <h2>사진 상세조회</h2>
      <br>

      <table class="detailArea" align="center">
        <tr>
            <td width="70">제목</td>
            <td colspan="3" width="600"><%= b.getBoardTitle() %></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><%=b.getBoardWriter() %></td>
            <td>작성일</td>
            <td><%=b.getBoardDate() %></td>
        </tr>
        <tr>
            <td>내용</td>
            <td colspan="3">
                <p style="height:50px"><%=b.getBoardContent() %></p>
            </td>
        </tr>
        <tr>
            <td>대표사진</td>
            <td colspan="3" align="center">
               
                    <img src="<%=getPath%>/<%=titleImg.getFilePath()+titleImg.getChangeName()%>" width="500" height="300" alt="">
                
            </td>
            
        </tr>
        <tr>
            <td>상세사진</td>
            <td colspan="3" align="center">
            <% for(int i = 1 ; i<list.size(); i++){ %>
                <img src="<%=getPath%>/<%=list.get(i).getFilePath()+list.get(i).getChangeName()%>" width="200" height="150" alt="">
                <% } %>
            </td>
        </tr>
      </table>

  </div>
</body>
</html>