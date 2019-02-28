<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<html>
<head>

<script type="text/javascript"
	src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"
	charset="utf-8"></script>
<title>Login</title>

<!-- 네이버아디디로로그인 초기화 Script -->

</head>
<body>
	<div class="container">

		<br> <br> <br>
		<%@ include file="nav.jsp"%>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="home">포트폴리오</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="home">답글형
								게시판 </a></li>
						<li class="nav-item"><a class="nav-link" href="photo">사진
								게시판</a></li>
						<li class="nav-item"><a class="nav-link" href="file">파일
								업로드게시판</a></li>
						<li class="nav-item active"><a class="nav-link" href="login">로그인</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<div id="content">
			<form action="logining" method="get">
				<div class="row">
					<input type="text" class="form-control" name="ID" placeholder=" ID">
				</div>
				<div class="row">

					<input type="text" class="form-control" name="PW" placeholder=" PASSWORD">

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
			<!-- <table class="table">



				<form action="logining" method="get">



					<tr>
						<td><input type="text" class="box" name="ID"
							placeholder=" ID"></td>
					</tr>
					<tr>
						<td><input type="text" class="box" name="PW"
							placeholder=" PASSWORD"></td>
					</tr>
					<tr>
						<td><input type="submit" class="btn btn-primary" value="로그인"></td>
						<td><a href="memberjoin" class="btn btn-primary">회원가입</a></td>
					</tr>

				</form>
				<tr>
					<td>
						
					</td>
				</tr>


			</table> -->




			<!-- //네이버아이디로로그인 버튼 노출 영역 -->
		</div>
	</div>
	<!-- 네이버아디디로로그인 초기화 Script -->
	<!-- <script type="text/javascript">
		var naverLogin = new naver.LoginWithNaverId({
			clientId : "uvuGSXSNJnTZI3FdmGwO",
			callbackUrl : "http://192.168.210.69:8090/controller/home",
			isPopup : false, /* 팝업을 통한 연동처리 여부 */
			loginButton : {
				color : "green",
				type : 3,
				height : 40
			}
		/* 로그인 버튼의 타입을 지정 */
		});

		/* 설정정보를 초기화하고 연동을 준비 */
		naverLogin.init();
	</script> -->
	<!-- // 네이버아이디로로그인 초기화 Script -->

</body>
</html>
