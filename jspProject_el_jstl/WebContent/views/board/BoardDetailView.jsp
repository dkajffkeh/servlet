<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.home.board.model.vo.Attachment"%> 
 <%@ page import="com.home.board.model.vo.Board"%> 

<%
Board b = (Board)request.getAttribute("b");

Attachment at = (Attachment)request.getAttribute("at");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .outer {
            background: black;
            color:white;
            width:1000px;
            /*height:550px;*/
            margin:auto;
            margin-top:50px;

        }
    </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세조회</h2>
        <br>

        <table id="detail_area" align="center" border="1px">
            <tr>
                <th width="70">분야</th>
                <th width="70"><%=b.getCategory() %></th>
                <th width="70">제목</th>
                <td width="350"><%=b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <th><%=b.getBoardWriter()%></th>
                <th>작성일</th>
                <td><%=b.getBoardDate()%></td>
            </tr>
            <tr>
                <th>내용</th>
            <td colspan="3">
                <p style="height:200px"><%=b.getBoardContent() %></p>
            </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                	<% if(at==null) { %>
                	첨부파일이 없습니다.
					<% } else { %>
                   
                    <a download="<%=at.getOriginName() %>" href="<%=getPath%>/<%=at.getFilePath() + at.getChangeName()%>"><%=at.getOriginName() %></a>
                    <% } %>
                </td>
            </tr>
        </table>
        <br><br>
        <!-- 본인일 경우만 보여짐.  -->
        <div align="center">
        <% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) { %>
            <a href="<%=getPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>"class="btn btn-warning btn-sm">수정하기페이지</a>
            <a href="" class="btn btn-danger btn-sm">삭제하기</a>
            <% } %>
        </div>

		<div id="replyArea">
			<table border="1" align="center">
				<thead>
					<tr>
						<th>댓글 작성</th>
						<% if(loginUser==null) { %>
						<td><textarea cols="50" rows="2" style="resize: none"
								readonly>로그인 후 이용가능한 서비스 입니다.</textarea></td>

						<td><button disabled>댓글등록</button></td>
						<% } else { %>
						<td>
						<input type="hidden" value="<%=loginUser.getUserNo()%>" id="userNo">
						<textarea cols="50" rows="2" style="resize: none"
								id="replyContent"></textarea></td>
						<td><button onclick="addReply();">댓글등록</button></td>
						<% } %>
					</tr>
				</thead>
				<tbody id="commentArea">
					
				</tbody>
			</table>
		</div>
			
		<script>
		$(function(){
			selectReplayList();
			
			window.setInterval(selectReplayList,1000*20);
		})		
		function addReply(){ //댓글 등록ajax;
		
			$.ajax({
				url:"rinsert.bo",
				type:"post",
				data:{
					content:$("#replyContent").val(),
					bno:"<%=b.getBoardNo()%>",
					userNo:$("#userNo").val(),
					
				},
				success:function(result){
					selectReplayList();
					
					$("#replyContent").val("");
				},
				error:function(){
					
				}
				
			})
			
		}
		
		function selectReplayList(){ //해당 이 게시글에 딸려있는 
			
			$.ajax({
				url:"rlist.bo",
				data:{bno:"<%=b.getBoardNo()%>"},
				success:function(list){
					
					let comment = "";
					
					for(let i in list){
						
						 comment +=  `<tr>
									  <td>\${list[i].replyWriter}</td>
						              <td>\${list[i].replyContent}</td>
						              <td>\${list[i].createDate}</td>
					                  </tr>`				
					}			
					$("#commentArea").html(comment);								
				},
				error:function(){				
				}			
			})
		 }

		</script>
		<br><br>
        
    </div>
</body>
</html>
