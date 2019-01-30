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
<title>File</title>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script>

    $(document).ready(function () {
        if(${not empty result}){
            alert('${result} 파일 저장 성공');
        } else {
           /*  alert('파일 저장 실패'); */
        }
    })
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

	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
		Object name = request.getAttribute("Fnum");

		int number = Integer.parseInt(name.toString());
		System.out.println(number + "님 환영합니다.");

		double pagenum = (double) number / 10;
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

		begin = aaaa * 10 - 10;
		end = aaaa * 10 - 1;
	%> 

	<h2><%=id%>님 환영합니다!</h2> 

	<br>
	<Cnum>총 글수: </Cnum><c:out value="${Fnum}"></c:out><br>
	<table class="table">
		<thead>
			<tr>
				<th>글번호&nbsp&nbsp&nbsp</th>
				<th>글제목&nbsp&nbsp&nbsp</th>
				<th>아이디&nbsp&nbsp&nbsp</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${FileCList}" var="a" begin="<%=begin%>"
				end="<%=end%>">
				<tr>
					<td>&nbsp&nbsp${a.num}</td>
					<td><a href="file_content?num=${a.num}">${a.title}</a></td>
					<td>${a.id}</td>
					<td></td>
				</tr>
			</c:forEach> 

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="file_write" class="btn btn-primary">글쓰기</a></td>
			</tr>

		</tbody>
	</table>

	<div id="ss" style="display: none">안녕</div>

	<a href="file?page=1">[처음]</a>
	<%
		for (int i = 1; i <= pagenum2; i++) {
	%>
	<a href="file?page=<%=i%>"><%=i%></a>&nbsp
	<%
		}
	%>
	<a href="file?page=<%=pagenum2%>">[끝]</a> 
</div>
</body>
</html>
