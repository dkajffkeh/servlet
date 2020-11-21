<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	
	<h1>AJAX의 개요</h1>
	
	우리가 기존에 href 로 요청을 한다거나 form 을 통해서 요청했던 방식은 전부 동기식 요청임. 서버에서 데이터를 불러 와야지만 데이터 출력이 가능했음.
	
	=> 응답페이지가 돌아와야만 볼 수 있음 (페이지 깜빡거림)
	=> 비동기식 요청을 위해서는 ajax 기술이 필요함.
	
	* 동기식/비동기식
	-> 동기식:요청처리 후에 그에 해당하는 응답페이지가 돌아와야만 그 다음 작업을 할 수 있음
	- 만약 서버에 호출된 결과까지 시간의 딜레이되면 무한정 계속 기다려야함
	->비동기식: 현재 페이지를 유지하면서 중간중간마다 추가적인 필요한 요청을 보낼 수 있음 
			  요청 한다고 해서 페이지가 넘어가지 않음.
			 ex) 실시간 검색어, 댓글, 검색어 자동완성, 아이디 중복체크, 찜하기,해제하기, UI 가 깔끔 . 너무 난발해서도 안됨. 페이지가 느려질 수 있음
			 
	->dom 요소를 만들어내는것을 잘 익여야함.
	
	*ajax 구현 방식 -> JavaScript 방식 / jQuery 방식	(코드가 간결하고 사용하기 쉽다.)	 
	<!--
	<p>
	동기식 표현-> href => </p>
	<a href="test.do">ㅋㅋㅋ</a>
	<form action="test.do">
	<button type="submit">ㅋㅋㅋ</button>
	</form>
	  -->
	  
	  <!-- 
	  *jQuery 방식에서의 ajax 통신
	  $.ajax({
	  속성명:속성값,
	  속성명:속성값,
	  .....
	   
	  }) 메소드 호출.
	   -->
	<h1>jQuery 방식을 이용한 ajax 테스트</h1>
	form 테그 필요가 없음.
	<h3>1. 버튼 클릭시 GET방식으로 서버에 요청 및 데이터 전달.</h3>
	 
	입력: <input type="text" id="input1">
	<button id="btn1">전송</button>
	
	<br>
	
	응답: <label id="output1">현재 응답 없음</label>
	응답: <label id="output2">현재 응답 없음</label>
	
	<div onclick="testFunc()">A테그 테스트</div>
	
	<script>
	$(function(){
	
		$("#btn1").click(function(){
			
			let html = "<div>진짜 되는건가?</div>"
			$("#output2").html(html);		
			//동기식 방식 
			//location.href="test.do?input="+$("#input").val();
			
			//비동기식 방식
		const promise = new Promise((resolve,reject)=>{			
			$.ajax({
			url:"jqAjax1.do", //url 은 무조건 있어야함.
		    data:{input:$("#input1").val()
		    	  }, //key=value	
			type:"post",      //type 은 요청 전송방식 type.
			success:function(result){ //success : ajax 통신 성공시 실행할 함수 정의
			
				$("#output1").text(result);
		    	  resolve(result)
		    	 
			},
			}) //여기까지 묶어줘야함
		})	
			promise.then((message)=>{
				console.log(message)
			});		
		})	
		})

	function testFunc(){
		
		$.ajax({
			url:"jqAjax1.do", //url 은 무조건 있어야함.
		    data:{input:$("#input1").val(),
		    	  bno:50}, //key=value	
			type:"post",      //type 은 요청 전송방식 type.
			success:function(result){ //success : ajax 통신 성공시 실행할 함수 정의
			
				$("#output1").text(result);
		    
			},
			complete:function(){
				console.log("모든 사이클 종료.")
			}
			
			});
		
	}
	
	</script>
		<h3>2. 버튼 클릭시 post 방식으로 서버에 여러개의 데이터 전송 및 응답.</h3>
		이름:<input type="text" id="input2_1"> <br>
		나이:<input type="number" id="input2_2">
		
		<button id="btn2">전송</button> <br>
		응답: <label id="output2"></label>
		
		<script>
		$(function(){
			
			$("#btn2").click(function(){
				
				$.ajax({
					url:"jqAjax2.do",
					data:{
						 name:$("#input2_1").val()
						,age:$("#input2_2").val()
					},
					type:"post",
					success:function(result){
						
						$("#output2").text(result);
					},
					error:function(){
						console.log("통신실패")
					}
					
					
				})
				
			})
			
		})
		</script>
		<h3>3. 객체응답. 서버로부터 데이터 전송 후 그에 해당하는 응답데이터.그에 해당하는 응답데이터 받기</h3>
		
		<h4>조회하고자 하는 회원번호 입력 후 전달하고 그에  해당하는 조회된 데이터 받기.(조회된 회원 객체.)</h4>
		
		조회할 회원번호 : <input type="number" id="input3">
					 <button id="btn3">조회</button>
					 
					 <div id="output3">
					 <div>1</div>
					 </div>
		<script>
		$(function(){
			
			$("#btn3").click(function(){
			//jqAjax.do?num=
	       $.ajax({
	    	   url:"jqAjax3.do",
	    	   data:{num:$("#input3").val()},
	    	   type:"post",
	    	   success:function(result){
	    		  
	    		console.log(result);
	    		  
	    		let str = "<ul>" + 
					 "<li>" + result.no 	+ "</li>" +
					 "<li>" + result.name 	+ "</li>" +
					 "<li>" + result.age 	+ "</li>" +
					 "<li>" + result.gender + "</li>" +
					 "</ul>";
		   	
					 $("#output3").append(str);  
				 
	    	   },
	    	   error:function(){
	    		   console.log("통신 실패")
	    	   }
	    	   
	    	   
	    	   
	    	   
	       })
				
			})
			
		})
		
		</script>
				
		<h4>4. Gson 라이브러리 이용해서 vo 객체 받아오기 </h4>
		
		조회할 회원 <input type="number" id="input4" required>
		<button id="button4">조회</button>
		
		<div id="output4"></div>
		
		<script>
		$(function(){
			
			$("#button4").click(function(){
				
				$.ajax({
					url:"jqAjax4.do",
					data:{num:$("#input4").val()},
					success:function(result){
						console.log(result)
						let str = "<ol>" +
								  "<li>" + result.name + "</li>" +	
								  "<li>" + result.age + "</li>" +
								  "<li>" + result.gender + "</li>" +
								  "</ol>"
						$("#output4").html(str);
						
						
					},
					error:function(){
						
					}
	
				})
				
			})
			
		})
		</script>
		
		<h4>5. 응답 데이터로 여러개의 객체들이 담겨있는 List전달 받기.</h4>
		<button id="btn5">회원 전체요소.</button>
		
		<table id="userTable" border=1>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody>
		 <th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
		</tbody>
	</table>
		
		
		<script>
		$(function(){
			
			$("#btn5").click(function(){
				
				$.ajax({
					url:"jqAjax5.do",
					success:function(list){
						
						let str="";
						for(let i = 0 ; i<list.length; i++){

							str +=  "<tr>" +
									"<td>" + list[i].no + "</td>" +
									"<td>" + list[i].name + "</td>" +
									"<td>" + list[i].age + "</td>" +
									"<td>" + list[i].gender + "</td>" +
									"<tr>"
								
								console.log(`\${list[i].no}`);
								
							}
						$("#userTable tbody").append(str);
						
					},
					error:function(){
						console.log("니코드가 븅신이라 에러가 난거야 ㅇㅋ?");
					
					}
				})
								
			})
	
		})
		</script>
		
		<h1>사진업로드</h1>
		<form action="upload.do" method ="post" enctype="multipart/form-data">
		<input type="file" name="file" multiple>
		<button type="submit">업로드</button>
		</form>
		
		
</body>
</html>