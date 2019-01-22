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
		<form action="fileupload" method="post" enctype="multipart/form-data">
			
			<tr>
				<td>파일</td>
				<td><input type="file" name="uploadfile" placeholder="파일 선택" /></td>
			</tr>
			<tr>

				<td colspan="2"><input type="submit" value="업로드" id="in"></td>
			</tr>
		</form>
	</table>
</body>
</html>