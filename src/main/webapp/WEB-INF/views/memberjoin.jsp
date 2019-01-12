<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>  
   회원가입을 해주세요!
<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="memberjoining" method="get">
			<tr>
				<td >아이디 </td>
				<td> <input type="text" name="ID" size = "30"> </td>
			</tr>
			<tr>
				<td> 비밀번호 </td>
				<td> <input type="text" name="PW" size = "30" > </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="NAME" size = "30" > </td>
			</tr>
			<tr>
				<td> 이메일 </td>
				<td> <input type="text" name="EMAIL" size = "30" > </td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="회원가입"> &nbsp;&nbsp; </td>
			</tr>
		</form>
</table>
</body>
</html>