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
<title>Home</title>
<script>
	
</script>
</head>
<body>

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
		out.println(pagenum2);
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
	<nav>
		<ul>
			<li><a href="home">답글형 게시판</a></li>
			<li><a href="photo">사진 게시판</a></li>
			<li><a href="file">파일 업로드게시판</a></li>
		</ul>
	</nav>
	<h1><%=id%>님 환영합니다!
	</h1>


	<br />
	<Cnum>총 글수: </Cnum>
	<c:out value="${Pnum}"></c:out>

	<%
		
	%>

	<table border="1">
		<tr>
			<c:forEach items="${PhotoCList}" var="a" begin="<%=begin%>"
				end="<%=end%>">

				<td>&nbsp&nbsp${a.num}<br> <img
					src="img/${a.save_file_name}" width="300" heigth="200"></img>
				<br>${a.title}
				</td>


			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${PhotoCList}" var="a" begin="<%=begin2%>"
				end="<%=end2%>">


				<td>&nbsp&nbsp${a.num}<br> <img
					src="img/${a.save_file_name}" width="300" heigth="200"></img>
					<br>${a.title}
					</td>


			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${PhotoCList}" var="a" begin="<%=begin3%>"
				end="<%=end3%>">


				<td>&nbsp&nbsp${a.num}<br>
				<img src="img/${a.save_file_name}" width="300" heigth="200"></img>
				<br>${a.title}</td>


			</c:forEach>
		</tr>
	</table>


	<%-- <table>
		<thead>
			<tr>
				<th>글번호&nbsp&nbsp&nbsp</th>
				<th>글제목&nbsp&nbsp&nbsp</th>
				<th>사진&nbsp&nbsp&nbsp</th>
				<th>아이디&nbsp&nbsp&nbsp</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${PhotoCList}" var="a" begin="<%=begin%>"
				end="<%=end%>">
				<tr>
					<td>&nbsp&nbsp${a.num}</td>
					<td><a href="content?num=${a.num}">${a.title}</a></td>
					<td>${a.save_file_name}<img src="img/${a.save_file_name}"
						width="200" heigth="200"></img></td>



					<td>${a.id}<img src="img/KakaoTalk_20181218_181601333.jpg" width="200" heigth="200"/></td>
					<td></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="photo_write">글쓰기</a></td>
			</tr>

		</tbody>
	</table> --%>

	<div id="ss" style="display: none">안녕</div>
	<br>
	<a href="photo_write">글쓰기</a>
	<br>
	
	<a href="photo?page=1">[처음]</a>
	<%
		for (int i = 1; i <= pagenum2; i++) {
	%>
	<a href="photo?page=<%=i%>"><%=i%></a>&nbsp
	<%
		}
	%>
	<a href="photo?page=<%=pagenum2%>">[끝]</a>

</body>
</html>
