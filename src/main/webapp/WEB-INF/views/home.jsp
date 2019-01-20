<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*" %>

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">

<meta http-equiv="Expires" content="-1">

<meta http-equiv="Pragma" content="no-cache">

<meta http-equiv="Cache-Control" content="no-cache">
<title>Home</title>
</head>
<body>

	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
		Object name = request.getAttribute("Cnum");

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
		out.println(pagenum2);
		/* 페이지 블록 계산 */
		
		
		int aaaa = Integer.parseInt(request.getParameter("page"));
		int begin = aaaa*10-10;
		int end = aaaa*10-1;
	%>
	<h1><%=id%>님 환영합니다!
	</h1>

	
	<br />
	<Cnum>총 글수: </Cnum>
	<c:out value="${Cnum}"></c:out>
	<script>document.write(aa);</script>
	<%
	
	%>
		<table>
			<thead>
				<tr>
					<th>글번호&nbsp&nbsp&nbsp</th>
					<th>글제목&nbsp&nbsp&nbsp</th>
					<th>아이디&nbsp&nbsp&nbsp</th>
					<th></th>
				</tr>
			</thead>
	
			<tbody>
				<c:forEach items="${HomeCList}" var="a" begin="<%=begin%>" end="<%=end%>">
					<tr>
						<td>&nbsp&nbsp${a.num}</td>
						<td><a href="content?num=${a.num}">${a.title}</a></td>
						<td>${a.id}</td>
						<td></td>
					</tr>
				</c:forEach>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="write">글쓰기</a></td>
				</tr>
			
			</tbody>
		</table>
		
	<div id="ss" style="display:none" >안녕</div>

	<a href="home?page=1">[처음]</a>
	<%
		for (int i = 1; i <= pagenum2; i++) {
	%>
	<a href="home?page=<%=i%>"><%=i%></a>&nbsp
	<%
		}
	%>
	<a href="home?page=<%=pagenum2%>">[끝]</a>
	
</body>
</html>
