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
		
		<div>
${notMember}
</div>
		<div id="join">
			
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