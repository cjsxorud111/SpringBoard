<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<html>
<head>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<title>Login</title>
<style>

</style>
</head>
<body id="loginBody" class="container-fluid" style="background-color: #FFCC00">
	<%@ include file="nav.jsp"%>
	<% String word = request.getParameter("word");%>

	<div id="loginInputSection">
		<div id="loginText">
			<h1>Login</h1>
		</div>
		<div class="row">
			<input type="text" id="id" style="width: "class="form-control" name="ID" placeholder=" ID">
		</div>
		<div class="row">
			<input type="password" id="pw" class="form-control" name="PW"
				placeholder=" PASSWORD">
		</div>
		<div class="row">
			<a href="javascript:loginClick();" class="btn btn-primary" id="login">로그인</a>
		</div>
		<div class="row">
			<a href="memberjoin" class="btn btn-primary" id="pw">회원가입</a>
		</div>
		<div class="row">
			<div id="naverIdLogin"></div>
		</div>
	</div>
	
</body>
<script>
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function loginClick(){
	var id = $('#id').val();
	var pw = $('#pw').val();
	var word = getParameterByName('word');
	$.ajax({
		type : "GET", // 전송방식을 지정한다 (POST,GET)
		url : "logining",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
		dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
		// 있다.
		data : {
			ID : id,
			PW : pw
		},
		error : function() {
			alert("error");
		},
		success : function(returnValue) {
			if(returnValue == 'success'){
				if(word == 'main(*)'){
					window.location.href = '/';
					//window.location.href = 'http://localhost:8090/controller/';
				}else if(word == 'write(*)'){
					window.location.href = 'newwordwrite';
				}else{
					window.location.href = 'linkWord?linkWord='+word;
				}
			}else if(returnValue == 'noId'){
				alert("일치하는 아이디가 없습니다.");
			}else{
				alert("패스워드가 다릅니다.");
			}
		}
	});
}

</script>
</html>
