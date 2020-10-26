<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
 
  <style>
        .outer {
        background-color: black;
        color: white;
        width: 600px;
        margin: auto;
    }
    
     td {
    
        color: white;
      
    }

    #myPageForm table {
        margin: auto;
    }

    #myPageForm input {
        margin: 5px
    }
    </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>

<%
String userId = loginUser.getUserId();
String userName = loginUser.getUserName();

String phone = (loginUser.getUserPhone()==null) ? "" : loginUser.getUserPhone();

String userEmail = (loginUser.getUserEmail()==null)    ? ""   : loginUser.getUserEmail();
String userAdd   = (loginUser.getUserAddress()==null)  ? ""   : loginUser.getUserAddress();
String interest  = (loginUser.getUserInterest()==null) ? ""   : loginUser.getUserInterest();
//3항 연산자

%>
    
    <div class="outer">
        <br>
        <h2 align="center">
            마이페이지
        </h2>
        <form action="update.me" method="POST" id="myPageForm">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" required  value="<%= userId %>" readonly></td>
                    <td></td>
                </tr>
                
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="5" required value="<%= userName %>" ></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="(-포함)" value="<%= phone %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="userEmail" value="<%= userEmail %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address" value="<%= userAdd %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <!-- (input[type=checkbox id name=interest value]+label)*6 -->
                        <input type="checkbox" id="exersize" name="interest" value="운동">
                        <label for="exersize">운동</label>
                        <input type="checkbox" id="climbing" name="interest" value="등산">
                        <label for="climbing">등산</label>
                        <input type="checkbox" id="fishing" name="interest" value="낚시">
                        <label for="fishing">낚시</label>
                        <br>
                        <input type="checkbox" id="cook" name="interest" value="요리">
                        <label for="cook">요리</label>
                        <input type="checkbox" id="game" name="interest" value="게임">
                        <label for="game">게임</label>
                        <input type="checkbox" id="etc" name="interest" value="기타">
                        <label for="etc">기타</label>
                    </td>
                </tr>
            </table>
            
            <script>
            
          
        	$(function(){
        		var interest = "<%=interest%>";
        		// "" / "운동,요리,게임"
        		
        		// 총 6개의 체크박스 요소들 선택
        		$("input[type=checkbox]").each(function(){
        			
      
        			// 순차적으로 접근한 요소의 value값이 interest라는 변수값에 포함되어있을 경우
        			// 그때의 해당 input요소에 checked 속성 부여
        			if(interest.search($(this).val()) != -1){
        				$(this).attr("checked", true);
        			}
        			
        		});
        		
        	});
        	
        
         
            
            </script>
            
            <div align="center">
            <button type="submit" class="btn btn-primary btn-sm">정보변경</button>
            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#updatePwdForm" >비밀번호 변경</button>
            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#deleteForm">회원 탈퇴</button>
        </div>
        </form>
        </div>
        <br><br>
  <!--========================================= ↓ 비밀번호 변경시 사용되는 Modal ↓ ============================================ -->
        <div class="modal" id="updatePwdForm">
            <div class="modal-dialog">
              <div class="modal-content">
              
                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title">비밀번호 변경</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body">
                  <form action="<%=getPath%>/updatePwd.me" method="post">
                  <input type="hidden" name="userId" value="<%=userId%>">
                      <table>
                          <tr>
                            <th>현재 비밀번호</th>
                          
                          <td><input type="password" name="userPwd" required></td>
                        </tr>
                          <tr>
                              <th>
                                  변경할 비밀번호
                              </th>
                              <td>
                                  <input type="password" name="updatePwd" required>
                              </td>
                          </tr>
                          <tr>
                              <th>
                              비밀번호 재입력
                            </th>
                          <td>
                              <input type="password" name="checkPwd" required>
                          </td>
                        </tr>
                      </table>
                      <br>
                      <button type="submit" class="btn btn-secondary btn-sm">변경</button>
                  </form>
                </div>
             
                
              </div>
            </div>
          </div>
        <!--========================================= ↑ 비밀번호 변경시 사용되는 Modal ↑  ============================================ -->

         <!--========================================= ↓ 회원 탈퇴시 사용되는 Modal ↓ ============================================ -->
          
          <div class="modal" id="deleteForm">
            <div class="modal-dialog">
              <div class="modal-content">
              
                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title">회원 탈퇴</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body" style="text-align: center;">

                    <b style="font-size: 0.8rem;">
                        탈퇴후 복구가 불가능 합니다. <br>
                        정말로 탈퇴하시겠습니까?
                        
                    </b>
                   

                  <form action="<%=getPath%>/delete.me" method="post">
                     <br>
                   <input type="hidden" name="userId" value="<%=userId%>">
                    비밀번호 : <input type="password" name="userPwd" required>
                    <br><br>
                    <button type="submit" class="btn btn-danger btn-sm">탈퇴하기</button>
                  </form>
                </div>
             
                
              </div>
            </div>
          </div>
    <!--=========================================  ↑ 회원 탈퇴시 사용되는 Modal  ↑ ============================================ -->   

</body>
</html>