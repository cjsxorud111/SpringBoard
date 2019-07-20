<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>

<html>

<head>
<meta name="naver-site-verification"
	content="b8b1382411790af26ef1553f419ce247d8465d24" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />

<meta name="google-site-verification"
	content="SQWRkLaGa_ZfTcO7MTunO6zcad_-7bFPg_bdazI4Zk8" />
<title>웹 사이트 설명</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="<c:url value="resources/js/defineJavaScript.js" />"></script>
<script async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
</head>
<!-- 네브바 -->
<body>
	<iframe src="testpdf.pdf" style="width:700px; height:700px;" frameborder="0"></iframe>
	<ol>
		<li>사이트를 소개</li>
		<li>이 사이트를 만든 목적</li>
		<li>이 사이트의 기대효과</li>
		<li>사용한 기술</li>
		<li>사이트 구조, 작동방식</li>
		<li>데이터베이스 구조</li>
		<li>crud 방식 ?</li>
		<li>추천검색어자동완성</li>
		<li>댓글 기능 설명</li>
		<li>페이징 설명</li>
	</ol>
	
	<div id="introduce">
	
	사용자가 정의하는 단어사전 HelloWord!<br><br>
	
	사용자가 직접 정의하는 단어사전 입니다.<br><br>
	
	회원가입과 로그인 후 단어를 정의하고 다른사람의 단어 정의를 추천하면 추천을 많이받은 단어가 상위로 올라갑니다.<br><br>
	
	사용자는 추천을 많이받은 더 정확한 단어를 알 수 있고 다른사람의 단어 정의도 볼 수 있습니다.<br><br>
	
	</div>

	<div id="purpose">
	
	이 사이트는 포트폴리오용으로 만들어졌습니다.<br><br>
	
	하지만 만들다보니 서비스 욕심도 생겨서 계속해서 개발하고 있습니다.<br><br>
	
	</div>
	
	<div id="">
	
	이 사이트는 사용자가 여러 사람들의 정의를 볼 수 있습니다. <br><br>
	
	일반 사전 사이트는 어떤 단어의 뜻이 잘 이해가 되지 않으면 <br><br>
	
	다른 곳을 찾아봐야하지만 이 사전은 여러사람의 정의를 읽을 수 있기 때문에 이해도를 높일 수 있습니다. <br><br>
	
	</div>
	
	<div id="">
		이 사이트의 구조는 
	</div>

스프링MVC로 개발했으며 데이터베이스는 MyBatis를 사용해서 다루고 있습니다.

DB는 AWS RDS를 사용했으며 카페24호스팅을 이용해 서비스하고 있습니다.

BootStrap을 사용하여 프론트엔드를 개발했습니다.

사용기술: SpringMVC, Java, JavaScript, Html, BootStrap, CSS, MySQL
</body>
</html>
