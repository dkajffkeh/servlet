<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer {
        background-color: black;
        color: white;
        width: 600px;
        margin: auto;
    }

    #enrollForm table {
        margin: auto;
    }

    #enrollForm input {
        margin: 5px
    }
</style>

</head>
<body>
    <!-- 메뉴바 포함 -->
<%@ include file="../common/menubar.jsp"  %>
    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>

        <form action="<%=getPath%>/insert.me" method="POST" id="enrollForm">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" required></td>
                    <td><button type="button" onclick="idCheck();">중복확인</button></td>
                </tr>
              
                <tr>
                    <td>* 비밀번호</td>
                    <td><input type="password" name="userPwd" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 비밀번호 확인</td>
                    <td><input type="password" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="5" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="(-포함)"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="userEmail"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address"></td>
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
            <div align="center">
                <button type="submit" id="enrollBtn" disabled>회원가입</button>
                <button type="reset">초기화</button>
            </div>
        </form>
        <br><br>
    </div>
    
   			<script>
                function idCheck(){
            	//아이디 입력할수 있는 input 요소 객체 받아와야함.
            	let $userId = $("#enrollForm input[name=userId]");
        		let $enroll = $("#enrollBtn")
        		
            	$.ajax({
            	url:"idCheck.me",
            	type:"get",
            	data:{checkId:$userId.val()},
            	success:function(result){
    		
            		if(result=="fail"){
            			
            			alert("같은 아이디가 존재합니다.")
            			$userId.focus();
            			
            		} else {           			
            			if(confirm("유효한 아이디 입니다 사용하시겠습니까?")){
            				$userId.attr('readonly',true);
            				$enroll.removeAttr('disabled')
            			}        			
            		}	
            	},
            	error:function(){
            		console.log("코드를 어케 짰길래 이게 실패하냐")
            		
            	}

            	});
            	 	
                };
            </script>
</body>
</html>