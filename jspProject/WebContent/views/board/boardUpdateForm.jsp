<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.home.board.model.vo.Attachment"%> 
 <%@ page import="com.home.board.model.vo.Board"%> 
<%
Board b = (Board)request.getAttribute("b");
//게시글 번호 , 카테고리명 , 내용 , 작성자 아이디, 작성일
Attachment at = (Attachment)request.getAttribute("at");
//null
//파일번호, 원본명 , 실제서버에 업로드된 명, 저장경로,
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
            width:1000px;
            height:550px;
            margin:auto;
            margin-top:50px;
        }
        #enrollForm  table {
            border:1px solid white;
        }
        #enrollForm input, #enrollForm textarea {
            width:100%;
            box-sizing: border-box;
        }

    </style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <h2 align="center">
            일반게시판 수정하기
        </h2>
        <form action="update.bo" id="enrollForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
            <!-- 카테고리 , 제목 , 내용 , 첨부파일 , -->
            <table align="center">
                <tr>
                    <th width="70">분야</th>
                    <td width="500">
                        <select name="category" id="">
                            <option value="10">공통</option>
                            <option value="20">운동</option>
                            <option value="30">등산</option>
                            <option value="40">게임</option>
                            <option value="50">낚시</option>
                            <option value="60">요리</option>
                            <option value="70">기타</option>
                        </select>
                        <script>
                        $(function(){
                        	
                        	$("#enrollForm option").each(function(){
                        		
                        		if($(this).text()=="<%=b.getCategory()%>"){
                        			$(this).attr("selected",true);
                        		}             	
                        	});
                        	
                        });
                        </script>
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required value="<%=b.getBoardTitle()%>"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" id="" cols="30" rows="10" ><%=b.getBoardContent() %></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                       <% if(at!=null) { %>
                       <%= at.getOriginName() %>
                       <input type="hidden" name="originFileNo" value="<%=at.getFileNo()%>">
                       <!-- 기존 첨부파일이 실제 서버에 업로드된 이름. -->
                       <input type="hidden" name="originFileName" value="<%=at.getChangeName()%>">
                       <% } %>
                        <input type="file" name="reupfile">
                        
                    </td>
                </tr>
            </table>
            <br>

            <div align="center">
                <button type="submit">수정하기</button> 
            </div>

        </form>
    </div>
</body>
</html>