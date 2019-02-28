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
		<br>
		<br>
		<br>
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
			Object name = request.getAttribute("Pnum");

			int number = Integer.parseInt(name.toString());
			System.out.println(number + "님 환영합니다.");

			double pagenum = (double) number / 9;
			double aa = pagenum - (int) pagenum;
			int pagenum2;
			if (aa == 0) {
				pagenum2 = (int) pagenum;
			} else {
				pagenum2 = (int) pagenum + 1;
			}
			
			/* 페이지 블록 계산 */
			int aaaa = 0;
			int begin = 0;
			int end = 0;
			String aaaaa = request.getParameter("page");
			if (aaaaa == null) {
				aaaa = 1;
			} else {
				aaaa = Integer.parseInt(aaaaa);
			}

			begin = aaaa * 9 - 9;
			end = aaaa * 9 - 7;
			int begin2 = begin + 3;
			int end2 = end + 3;
			int begin3 = begin2 + 3;
			int end3 = end2 + 3;
		%>

		<div id="LoginCheck"><%=id%>님 환영합니다!</div>	


		<br>
		<Cnum>총 글수: </Cnum>
		<c:out value="${Pnum}"></c:out>

		<%
			
		%>

		<table class="table">
			<tr>
				<c:forEach items="${PhotoCList}" var="a" begin="<%=begin%>"
					end="<%=end%>">

					<td>&nbsp&nbsp${a.num}<br> <a
						href="photopage?num=${a.num}"><img
							src="img/${a.save_file_name}" width="300" heigth="200"></img></a>
					</td>


				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${PhotoCList}" var="a" begin="<%=begin2%>"
					end="<%=end2%>">


					<td>&nbsp&nbsp${a.num}<br> <a
						href="photopage?num=${a.num}"><img
							src="img/${a.save_file_name}" width="300" heigth="200"></img></a>
					</td>


				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${PhotoCList}" var="a" begin="<%=begin3%>"
					end="<%=end3%>">


					<td>&nbsp&nbsp${a.num}<br>
					<a href="photopage?num=${a.num}"> <img
							src="img/${a.save_file_name}" width="300" heigth="200"></img></a>
							</td>


				</c:forEach>
			</tr>
			<tr>
				<td>총페이지수: <%=pagenum2-1%>&nbsp<a
			href="photo?page=1">[처음]</a>
		<%
			for (int i = 1; i <= pagenum2-1; i++) {
		%>
		<a href="photo?page=<%=i%>"><%=i%></a>&nbsp
		<%
			}
		%>
		<a href="photo?page=<%=pagenum2-1%>">[끝]</a></td>
				<td></td>
				<td></td>
				<td><a href="photo_write">글쓰기</a></td>
			</tr>
		</table>
		
		<br><br> 
	</div>
</body>
</html>
