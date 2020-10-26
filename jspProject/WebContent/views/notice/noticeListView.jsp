<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>   
<%@ page import="com.home.notice.model.vo.Notice" %> 
<% request.setCharacterEncoding("utf-8");

ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

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
            width: 800px;
            height:500px;
            margin:auto;
            margin-top:50px;
        }

        .list-area{
            border:1px solid white;
            text-align: center;
        }

        .list-area tbody>tr:hover {
            background-color: darkgray;
            cursor:pointer;
        }
    </style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <h2 align="center">공지사항</h2>
        <br>
        <!-- 관리자일 경우 보이는 div -->
        <% if(loginUser!=null&&loginUser.getUserId().equals("admin")){ %>
        <div align="right" style="width:700px;">
           <!--  <button onclick="location.href='';">글작성</button> --> 
           <a href="<%=getPath%>/enrollForm.no" class="btn btn-secondary btn-sm">글작성</a>
            <br><br>
           
        </div>
        <% }; %>
        <table class="list-area" align="center">
            <thead>
               <tr>
                   <th>글번호</th>
                   <th width="300">제목</th>
                   <th width="100">작성자</th>
                   <th>조회수</th>
                   <th width="100">작성일</th>
               </tr>
            </thead>
            <tbody>
                
                <% if(list.isEmpty()) { %>
                   <tr>
                    <td colspan="5">존재하는 공지사항이 없습니다.</td>
                </tr>
                <% } else {   
                   
                	for(Notice n : list) {
                 %> 
                    <tr>
                    <td><%= n.getNoticeNo() %></td>
                    <td><%= n.getNoticeTitle() %></td>
                    <td><%= n.getNoticeWriter() %></td>
                    <td><%= n.getCount() %></td>
                    <td><%= n.getCreateDate() %></td>
                </tr>

            </tbody>
			<% } 
                	} %>
		</table>
		
		
		<script> 
			$(function(){
				$(".list-area>tbody>tr").click(function(){
					
					//클릭할때 마다의 그 글번호를 추출.
					//클릭했을 때의 행에 존재하는 글 번호.
					let nno = $(this).children().eq(0).text();
					//location.href = "jsp/detail.no";
					
					// 쿼리스트링으로 만들어서 요청시 어떤 값을 전달할 수 있다!!
					location.href ="<%=getPath%>/detail.no?nno="+nno;
				})
			});
		</script>
        <br><br><br>
    </div>
</body>
</html>