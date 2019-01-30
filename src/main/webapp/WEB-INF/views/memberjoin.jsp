<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberjoin</title>
</head>
<body>
	<div class="container">
		<br> <br> <br>
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
					<li class="nav-item"><a class="nav-link" href="file">파일
							업로드게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="login">로그인</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="memberjoin">회원가입</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<div id="join">
			<table class="table">
				<form action="memberjoining" method="get">
					<tr>

						<td><input type="text" class="form-control" name="ID"
							size="30" placeholder="ID"></td>
					</tr>
					<tr>

						<td><input type="text" class="form-control" name="PW"
							size="30" placeholder="PASSWORD"></td>
					</tr>
					<tr>

						<td><input type="text" class="form-control" name="NAME"
							size="30" placeholder="이름"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="EMAIL"
							size="30" placeholder="E-MAIL"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btn btn-primary"
							value="회원가입"> &nbsp;&nbsp;</td>
					</tr>
				</form>
			</table>
		</div>
	</div>
</body>
</html>