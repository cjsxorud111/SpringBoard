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
<title>새단어정의하기</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">

</head>
<body style="background-color: #FFCC00;">
	<%@ include file="nav.jsp"%>
	<%
		String id = (String) session.getAttribute("ID");
		String pw = (String) session.getAttribute("PW");
		System.out.println(id + "님 환영합니다.");
	%>
	<table id="cont">
		<form action="newwordWriting" method="post"
			enctype="multipart/form-data">
			<tr>
				<td style="text-align: center; font-size:30px; padding-bottom:2rem; font-weight:500;">
				<br>
				 <div style='color:#2AAE02; font-weight:700;'>새단어 정의하기</div>
				 
				</td>
			</tr>
			<tr>
				<td><br> <input type="text" class="wid" id="wid" name="WORD"
					size="20" style="border:none" placeholder="&nbsp;단어명"></td>
			</tr>
			<tr>
				<td>
					<br>
					<div style='font-size:22px; margin-bottom:13px;'>원하는 단어를 자유롭게 정의해주세요!</div>
					<div style='font-size:20px; color:tomato; font-weight:600;'>예) 단어명: 사전</div>
					<div style='font-size:20px; color:tomato; font-weight:600; margin-left: 1.8rem;'>정의: 단어의 뜻을 서술해놓은것</div>
				
				</td>
			</tr>
			<tr>
				<td><br> <textarea name="editor1" class="editor1"
						rows="10" cols="120" placeholder="글내용얼">
						</textarea></td>
			</tr>
			
			<tr style="margin-top: 1rem;">
				<input type="hidden" name="ID" value="<%=id%>">
				<input type="hidden" name="PW" value="<%=pw%>">
				<td>
				<div style="margin-top: 1rem; height: 3.9rem;">
				<input type="submit" value="단어정의" id="in"><a href="define"id="inde" style="color:white;">취소</a>
				</div>
				</td>
			</tr>
		</form>
		<tr>
			<td></td>
		</tr>
	</table>
	
	<!-- 이미지업로드 -->
	<!-- <script type="text/javascript">
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
	</script> -->
</body>
</html>
