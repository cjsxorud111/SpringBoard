<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.dto.GetContentVO" %>

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
		
		
				<c:forEach items="${GetContentList}" var="a">
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
	
	
	</body>
</html>
