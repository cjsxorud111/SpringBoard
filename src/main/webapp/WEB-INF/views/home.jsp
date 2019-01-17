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
					
					
					<th> </th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${HomeCList}" var="a">
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
	
	
	</body>
</html>
