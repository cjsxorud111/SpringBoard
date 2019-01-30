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
			<c:forEach items="${GetContentList}" var="a">
				<tr>
					<td>글번호</td>
					<td><%=num%></td>
				</tr>

				<tr>
					<td>제목</td>
					<td>${a.title}</td>
				</tr>

				<tr>
					<td>글내용</td>
					<td>${a.content}<br> <img src="img/${a.save_file_name}"></img></td>
				</tr>

				<tr>
					<td>첨부파일</td>
					<td><a href="download?filename=${a.save_file_name}">${a.save_file_name}</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>	</td>	
				<td>	</td>	
				<td><a href="newupdate?num=<%=num%>" class="btn btn-primary">수정하기</a>&nbsp<a href="delete?num=<%=num%>" class="btn btn-primary">삭제하기</a>
				</td>
			</tr>
		</table>
		<br>
		
	</div>
</body>
</html>
