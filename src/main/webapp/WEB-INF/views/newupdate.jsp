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
		<tr>
			<td>글번호</td>
			<td><%=num%></td>
		</tr>
		<c:forEach items="${GetContentList}" var="a">

			<form action="newupdating" method="post">

				<input type="hidden" name="NUM" value="<%=num%>" />
			<tr>
				<td>제목</td>
				<td><input type="text" name="TITLE" size="45"
					value="${a.title}"></td>
			</tr>

			<tr>
				<td>글내용</td>
				<td><input type="text" name="CONTENT" size="45"
					value="${a.content}"></td>
			</tr>
			
			
		</c:forEach>
		
	</table>
			<input type="submit" value="수정하기"></input>
		</form>
</body>
</html>
