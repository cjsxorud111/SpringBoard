<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberjoin</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<link href="resources/css/style.css?after" rel="stylesheet" type="text/css">

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
<!-- Bootstrap core JavaScript -->	
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	window.onload = function() {
		var section2 = document.getElementById("LoginCheck");
		$("#engg").html("eeeee");
		var sessionValue= $("#LoginCheck").html();
		
		if(sessionValue=='null님 환영합니다!'){
			section2.style.display = "none";	
		}else{
			section2.style.display = "block";	
		}

	}
</script>

<script>
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    return results[2];
}

window.onload = function() {
	var a = getParameterByName('han');
	
	if(a == 'true'){
		$("#engg").html("아이디와 이름은 영문으로 입력해주세요");
	}

}
	
</script>

</head>
<body>
	<div class="container">
		<br> <br> <br>
		<%-- <%@ include file="nav.jsp"%> --%>
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
					<li class="nav-item"><a class="nav-link" href="login">로그인</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="memberjoin">회원가입</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<div id="join">
			
			<!-- <table class="table">
				<form action="memberjoining" method="get">
					<tr>

						<td><input type="text" class="form-control" name="ID"
							size="30" placeholder="ID"></td>
					</tr>
					<tr>

						<td><input type="text" class="form-control" name="PW"
							size="30" placeholder="PASSWORD"></td>
					</tr>
					<tr>

						<td><input type="text" class="form-control" name="NAME"
							size="30" placeholder="이름"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="EMAIL"
							size="30" placeholder="E-MAIL"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btn btn-primary"
							value="회원가입"> &nbsp;&nbsp;
						</td>	
					</tr>
					

						<input type="text" class="form-control" name="ID"
							size="30" placeholder="ID">
					

						<input type="text" class="form-control" name="PW"
							size="30" placeholder="PASSWORD">
					

						<input type="text" class="form-control" name="NAME"
							size="30" placeholder="이름">
					
						<input type="text" class="form-control" name="EMAIL"
							size="30" placeholder="E-MAIL">
					
						<input type="submit" class="btn btn-primary"
							value="회원가입"> &nbsp;&nbsp;
				</form>		
			</table> -->
			<div id="engg"></div>
			<form action="memberjoining" method="get">
				<input type="text" class="form-control" name="ID" size="30"
					placeholder="ID"> <br> <input type="text"
					class="form-control" name="PW" size="30" placeholder="PASSWORD">

				<br> <input type="text" class="form-control" name="NAME"
					size="30" placeholder="이름"> <br> <input type="text"
					class="form-control" name="EMAIL" size="30" placeholder="E-MAIL">
				<br> <input type="submit" class="btn btn-primary" value="회원가입">
				&nbsp;&nbsp;<a href="home" class="btn btn-primary">취소</a>
				
			</form>
			
		</div>
	</div>
	
	
</body>
</html>