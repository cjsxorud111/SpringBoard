<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>

<%@ include file="nav.jsp"%>
<html>
<head>
<link href="resources/css/style.css?after" rel="stylesheet"
	type="text/css">
<title>에러페이지</title>
<meta name="naver-site-verification"
	content="b8b1382411790af26ef1553f419ce247d8465d24" />
<meta name="google-site-verification"
	content="SQWRkLaGa_ZfTcO7MTunO6zcad_-7bFPg_bdazI4Zk8" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="<c:url value="resources/js/defineJavaScript.js" />"></script>
<script async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>

</head>
	<body style="background-color: #FCB900">
		<div id="container-fluid" style="background-color:white; border-radius: 9px 9px 9px 9px; padding: 1rem; padding-top:1.5rem; padding-bottom:1.5rem; width:45rem;">
			<div><h2>서비스이용에 불편을 드려 죄송합니다. </h2></div><br>
			<div><h2>요청하신 페이지가 존재하지 않거나 오류가 발생했습니다.</h2></div><br>
			<div><h2><a href="javascript:history.back()">뒤로가기</a></h2></div>
		
		</div>
		
	</body>
</html>
