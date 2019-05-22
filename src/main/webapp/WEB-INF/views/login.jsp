<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<html>
<head>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<title>Login</title>
</head>
<body id="loginBody">
	<%@ include file="nav.jsp"%>
	<div id="loginInputSection">
		<div id="loginText">
			<h1>Login</h1>
		</div>
		<form action="logining" method="get">
			<div class="row">
				<input type="text" class="form-control" name="ID" placeholder=" ID">
			</div>
			<div class="row">
				<input type="text" class="form-control" name="PW"
					placeholder=" PASSWORD">
			</div>
			<div class="row">
				<input type="submit" class="btn btn-primary" value="로그인" id="login">
			</div>
			<div class="row">
				<a href="memberjoin" class="btn btn-primary" id="pw">회원가입</a>
			</div>

			<div class="row">
				<div id="naverIdLogin"></div>
			</div>
		</form>
	</div>

</body>
</html>
