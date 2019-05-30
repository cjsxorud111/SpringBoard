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
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">

</head>
<body style="background-color: #CACCCE;">
	<%@ include file="nav.jsp"%>
	<%
		String id = (String) session.getAttribute("ID");
		String pw = (String) session.getAttribute("PW");
		System.out.println(id + "님 환영합니다.");
	%>
	<table id="cont">
	<script type="text/javascript">
		
	</script>
		<form action="newwordWriting" method="post"
			enctype="multipart/form-data">
			<tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td><br> <input type="text" class="wid" id="wid" name="WORD"
					size="20" placeholder="&nbsp;단어명"></td>
			</tr>
			<tr>
				<td><br> <textarea name="editor1" class="editor1"
						rows="10" cols="80" placeholder="글내용">
						</textarea></td>
			</tr>
			<br>
			<tr>
				<input type="hidden" name="ID" value="<%=id%>">
				<input type="hidden" name="PW" value="<%=pw%>">
				<td><input type="submit" value="단어정의" id="in"></td>
			</tr>
		</form>
		<tr>
			<td><a href="define"id="inde">취소</a></td>
		</tr>
		
	</table>
	<script type="text/javascript">
		$('#wid').keyup(function(event) {
			alert("ddd");	
		});
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
