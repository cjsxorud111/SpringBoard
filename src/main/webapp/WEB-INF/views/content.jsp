<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.dto.GetContentVO"%>

<html>
<head>
<title>Home</title>
</head>
<body>
	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
		String num = request.getParameter("num");
	%>
	<h1><%=id%>님 환영합니다!
	</h1>
	글목록

	<br />
	
	
	<table>
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
				<td>${a.content}</td>
			</tr>
		</c:forEach>


	</table>
	<br>
	<br>


	<a href="newupdate?num=<%=num%>">수정하기</a>
	<a href="delete?num=<%=num%>">삭제하기</a>
	<br>
	<br>
	<br>
	<br>
	<table>
		<tr>

			<td>댓글달기<%=num%></td>
			<form action="writesub" method="post">
				<td><textarea name="subcon" rows="5" cols="140"></textarea></td> <input
					type="hidden" name="num" value="<%=num%>" />
				<td><input type="submit" value="댓글달기"></td>
			</form>
		</tr>
	</table>
	<table>
		<c:forEach items="${GetSubList}" var="a">
			<tr>
				<td>z${a.num}</td>
				<td>z${a.content}</td>
			</tr>
		</c:forEach>
	</table>
	
</html>
