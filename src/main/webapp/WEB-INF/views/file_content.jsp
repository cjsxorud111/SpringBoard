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
			String num = request.getParameter("num");
		%>
		<h1><%= id %>님 환영합니다!</h1>
		글목록
				
		<br/>
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
						<td>${a.content}<br><img src="img/${a.save_file_name}"></img></td>
					</tr>
					
					<tr>
						<td>첨부파일</td>
						<td><a href="download?filename=${a.save_file_name}">${a.save_file_name}</a></td>
					</tr>
            	</c:forEach>
		    </table>
		<a href="newupdate?num=<%=num%>">수정하기</a>
		<a href="delete?num=<%=num%>">삭제하기</a>

	</body>
</html>
