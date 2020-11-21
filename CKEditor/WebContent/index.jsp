<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
</head>
<body>

<form action="fileupload.do" method="post">

<textarea class="form-control" id="abc" name="content"></textarea>
<button type="submit">제출</button>
</form>

<script type="text/javascript">
 CKEDITOR.replace('abc',{height: 500,filebrowserUploadUrl:"fileupload.do?bno=1"});
</script>



</body>
</html>