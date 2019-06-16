<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<html>
<head>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<title>글수정</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
</head>
<body style="background-color: #CACCCE; display: table;">
	<%@ include file="nav.jsp"%>
	<%
		String id = (String) session.getAttribute("ID");
		String pw = (String) session.getAttribute("PW");
	%>
	<div style='background-color: yellow; width: 50rem; display: table-cell;'>
	<table id="cont" style='margin-top: 2.7rem;'>
		<tr>
			<td>
			<br>
			</td>
		</tr>
		<tr>
			<td style="font-size: 2.1rem; text-align: center; padding-bottom: 3rem; font-weight: 700;">글수정하기</td>
		</tr>
		<tr>
			<td><input type="text" class="wid" id="wid" name="WORD"
				size="20" placeholder="&nbsp;단어명" style="padding-left:1rem;  margin-bottom: 2rem;" value="${modifyContentVO.word}">
			</td>
		</tr>
		<tr>
			<td>
				<textarea name="editor1" class="editor1" id="textValue"
				rows="10" cols="80" placeholder="글내용" style="text-align: left; ">
				${modifyContentVO.info}
				</textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" style="margin-top:2rem;" onclick="defineContentModify(${modifyContentVO.num} ,'${textStatusVO.textStatus}')" value="수정하기" id="in">
			</td>
		</tr>
		<tr>
			<td><a href="define" id="inde" style="margin-top:2rem;">취소</a></td>
		</tr>
		<tr>
			<td>
			<br>
			</td>
		</tr>
	</table>
	</div>
	<script type="text/javascript">
	
		function defineContentModify(conNum,textStatus) {
			var word = $('#wid').val();
			var textValue = $('#textValue').val();
			$.ajax({
				type : "POST", // 전송방식을 지정한다 (POST,GET)
				url : " modifyWriting",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서
											// 사용해도된다.
				dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
									// 있다.
				data : {
					conNum : conNum,
					WORD : word,
					textValue : textValue
				},
				error : function() {
					alert("error");
				},
				success : function(Parse_data) {
					if(textStatus == 'main(*)'){
						window.location.href = 'define';
					} else {
						window.location.href ='linkWord?linkWord='+textStatus;
					}
				}
			});
		}
	
		/* $(function() {
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
		}); */
	</script>
</body>
</html>
