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
            일반게시판 작성하기
        </h2>
        <form action="<%=getPath%>/insert.bo" id="enrollForm" method="post" 
              enctype="multipart/form-data">
            <!-- 카테고리 , 제목 , 내용 , 첨부파일 , -->
            <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
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
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="content" id="" cols="30" rows="10"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td><input type="file" name="upfile"></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <button type="submit">등록하기</button>
                <button type="reset">취소하기</button>
            </div>

        </form>
    </div>
</body>
</html>