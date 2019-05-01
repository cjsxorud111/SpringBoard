<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>

<html>
<head>

<script src="//code.jquery.com/jquery.min.js"></script>

<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script> -->
<title>Home</title>
<link href="resources/css/style.css?after" rel="stylesheet"
	type="text/css">

</head>
<body>
	<%@ include file="nav.jsp"%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="home">신조어사전</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="home">답글형
							게시판 </a></li>

					<li class="nav-item"><a class="nav-link" href="login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="container">
		<br> <br> <br>

	</div>

	<br>
	<div id="container">
		<div id="write">
			<%
				String id = (String) session.getAttribute("ID");
				System.out.println(id + "님 환영합니다.");
			%>
			<table>
				<form action="newword_writing" method="post"
					enctype="multipart/form-data">
					
					<tr>
					<td>
						단어명
						<br>
						<input type="text" name="WORD" size="20" value="">
						</td>
					</tr>
					
					<tr>
					<td>
						아이디
						<br>
						<input type="text" name="ID" size="20" value="<%=id%>">
						</td>
					</tr>
					
					<tr>

						<td>비밀번호<br> <input type="text" name="PW" size="20"
							value=""></td>
					</tr>

					<tr>

						<td>글내용<br> <textarea name="editor1" id="editor1"
								rows="10" cols="80"></textarea></td>
					</tr>

					<tr>
						<td><input type="submit" value="업로드" id="in"></td>
					</tr>
				</form>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {

			CKEDITOR.replace('editor1', {//해당 이름으로 된 textarea에 에디터를 적용
				width : '100%',
				height : '200px',
				filebrowserImageUploadUrl : 'img1' //여기 경로로 파일을 전달하여 업로드 시킨다.
			});

			CKEDITOR.on('dialogDefinition', function(ev) {
				var dialogName = ev.data.name;
				var dialogDefinition = ev.data.definition;

				switch (dialogName) {
				case 'image': //Image Properties dialog
					//dialogDefinition.removeContents('info');
					dialogDefinition.removeContents('Link');
					dialogDefinition.removeContents('advanced');
					break;
				}
			});

		});
	</script>
</body>
</html>
