<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
 --><style type="text/css">
#in {
	margin-left: 22rem;
}
</style>


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
		<form action="writing" method="post">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="ID" size="20" value="<%=id%>">
				</td>
			</tr>

			<tr>
				<td>글제목</td>
				<td><input type="text" name="TITLE" size="45"></td>
			</tr>

			<tr>
				<td>글내용</td>
				<!-- <td><textarea name="editor1" id="editor1" rows="10" cols="80">
	                This is my textarea to be replaced with CKEditor.
	            	</textarea></td> -->
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="글입력" id="in"></td>
			</tr>
		</form>

	</table>
	<!-- 위지윅에디터 -->


	<!-- <script>
		// Replace the <textarea id="editor1"> with a CKEditor
		// instance, using default configuration.
		CKEDITOR.replace('editor1', {
			filebrowserImageUploadUrl : 'ckeditorImageUpload'
		});
	</script> -->

</body>
</html>