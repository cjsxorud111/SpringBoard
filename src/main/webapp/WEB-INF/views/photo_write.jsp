<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
#in {
	margin-left: 22rem;
}
</style>
<script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>



</head>
<body>
	<h1>글쓰기!</h1>
	<br />
	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
	%>
	<h2><%=id%>님 환영합니다!
	</h2>
	<table>
		<form action="photoupload" method="post" enctype="multipart/form-data">
			
			<tr>
				<td >아이디 </td>
				<td> <input type="text" name="ID" size = "20" value = "<%= id %>"> </td>
			</tr>
			
			<tr>
				<td>글제목</td>
				<td> <input type="text" name="TITLE" size = "45"> </td>
			</tr>
			
			<tr>
				<td>파일</td>
				<td><input type="file" name="uploadfile" placeholder="파일 선택" /></td>
			</tr>
			
			<tr>
				<td> 글내용 </td>
				<!-- <td> <textarea name="CONTENTS" rows="20" cols="50"></textarea></td> -->
				<td><textarea name="editor1" id="editor1" rows="10" cols="80">
	                This is my textarea to be replaced with CKEditor.
	            	</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="업로드" id="in"></td>
			</tr>
		</form>
	</table>
	<script>
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		// Macintosh HD⁩/Users/taeky/eclipse-workspace/SpringBoard/src/main/webapp/⁨resources⁩/
		CKEDITOR.replace('editor1', {
			filebrowserImageUploadUrl : 'img'
		});
	</script>
</body>
</html>