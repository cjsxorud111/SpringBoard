<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>

<style type="text/css">
#in {
	margin-left: 22rem;
}
</style>


</head>
<body>
	<div class="container">
		<%@ include file="nav.jsp"%>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home">포트폴리오</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="home">답글형
							게시판 </a></li>
					<li class="nav-item"><a class="nav-link" href="photo">사진
							게시판</a></li>
					<li class="nav-item active"><a class="nav-link" href="file">파일
							업로드게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
					</li>
				</ul>
			</div>
		</div>
		</nav>
		<br> <br> <br>
		
		<br />
		<%
			String id = (String) session.getAttribute("ID");
			System.out.println(id + "님 환영합니다.");
		%>
		<h2><%=id%>님 환영합니다!</h2>
		
		<table cellpadding="5px">
			<form action="fileupload" method="post" enctype="multipart/form-data">

				<tr>
					<td>아이디</td>
					<td><input type="text"class="form-control" name="ID" size="20" value="<%=id%>">
					</td>
				</tr>

				<tr>
					<td>글제목</td>
					<td><input type="text" class="form-control"name="TITLE" size="45"></td>
				</tr>

				<tr>
					<td>파일</td>
					<td><input type="file" name="uploadfile" placeholder="파일 선택" /></td>
				</tr>

				<tr>
					<td>글내용</td>
					<td><textarea name="CONTENTS" class="form-control" rows="20" cols="50"></textarea></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2"><input type="submit" class="btn btn-primary" value="업로드" id="in"></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>