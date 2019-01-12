<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
	<body>
		<% 
			String id = (String)session.getAttribute("ID");
			System.out.println(id+"님 환영합니다."); 
		%>
		<h1><%= id %>님 환영합니다!</h1>
		글목록
		<br/>
		<table>
			<thead>
				<tr>
					<th>글번호&nbsp&nbsp&nbsp</th>
					<th>글제목&nbsp&nbsp&nbsp </th>
					<th>아이디&nbsp&nbsp&nbsp </th>
					<th>수정 &nbsp&nbsp&nbsp</th>
					<th>삭제&nbsp&nbsp&nbsp </th>
					<th> </th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>f</td>
					<td>f</td>
				</tr>
				
				<tr>
					<td>d</td>
					<td>d</td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="write">글쓰기</a></td>
				</tr>
				
			</tbody>
			
			
		</table>
	
	
	</body>
</html>
