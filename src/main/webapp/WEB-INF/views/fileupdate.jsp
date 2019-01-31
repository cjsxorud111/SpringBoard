<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.dto.GetContentVO"%>

<html>
<head>
<title>Home</title>
</head>
<body>
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
	<br>
	<br>
	<br>
	<div class="container">
		<%
			String id = (String) session.getAttribute("ID");
			System.out.println(id + "님 환영합니다.");
			String num = request.getParameter("num");
		%>
		<h1><%=id%>님 환영합니다!
		</h1>
		<br>
		<table cellpadding="5px">
			<tr>
				<td>글번호</td>
				<td><%=num%></td>
			</tr>
			<c:forEach items="${GetContentList}" var="a">

				<form action="fileupdating" method="post"
					enctype="multipart/form-data">

					<input type="hidden" name="num" value="<%=num%>" />
					<tr>
						<td>제목</td>
						<td><input type="text" class="form-control" name="title"
							size="45" value="${a.title}"></td>
					</tr>

					<tr>
						<td>글내용</td>
						<td><input type="text" class="form-control" name="content"
							size="45" value="${a.content}"></td>
					</tr>

					<tr>
						<td>첨부파일</td>
						<td>${a.save_file_name}</td>
					</tr>
			</c:forEach>
			<tr>
				<td>수정할파일</td>
				<td><input type="file" name="uploadfile" placeholder="파일 선택" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="수정하기" class="btn btn-primary"
					id="in"></input></td>
			</tr>
			</form>
		</table>


	</div>
</body>
</html>
