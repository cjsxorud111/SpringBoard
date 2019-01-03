<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>수정</th>
			</tr>
		</thead>
		<tbody>
		
		
		
			 <form action="updating" method="get">
				<tr>
					
					<td>${param.ID}<input type="hidden" name="ID" value = "${param.ID}"></td>
				
					<td> <input type="text" name="PW" size = "5" value = "${param.PW}"> </td>
			
					<td> <input type="text" name="NAME" size = "5" value = "${param.NAME}"> </td>

					<td> <input type="submit" value="수정하기"> </td>
				</tr>
			</form>
		
		
		<%--
			<c:forEach items="${memberList}" var="member">
				<tr>
					<td>${member.id}</td>
					<td>${member.pw}</td>
					<td>${member.name}</td>
					<td><a href="update?ID=${member.id}">수정하기</a></td>
					
				</tr>
			</c:forEach>  --%>
			
			
		</tbody>
	</table>


</body>
</html>
