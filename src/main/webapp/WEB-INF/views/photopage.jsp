<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">

<meta http-equiv="Expires" content="-1">

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="Cache-Control" content="no-cache">
<title>Photo</title>
<script>
	
</script>
</head>
<body>
<div class="container">
	<br><br><br>
<%@ include file="nav.jsp" %>
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
					<li class="nav-item active"><a class="nav-link" href="photo">사진
							게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="file">파일
							업로드게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
		



		
	%>
	
	<div id="LoginCheck"><%=id%>님 환영합니다!</div>


	<table class="table">
		<tr>
			<c:forEach items="${GetContentList}" var="a" >

				<td>&nbsp&nbsp${a.num}<br> <img
					src="img/${a.save_file_name}" width="1000" heigth="1000"></img> <br>${a.title}
				</td>


			</c:forEach>
		</tr>
		
	</table>


	
</div>
</body>
</html>
