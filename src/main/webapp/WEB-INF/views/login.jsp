<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
			</tr>
		</thead>
		<tbody>
			 <form action="logining" method="get">
				<tr>
					<td> <input type="text" name="ID" size = "5" > </td>
				
					<td> <input type="text" name="PW" size = "5" > </td>

					<td> <input type="submit" value="로그인"> </td>
				</tr>
			</form>
		
		</tbody>
	</table>

</body>
</html>
