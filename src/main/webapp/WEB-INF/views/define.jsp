<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<%
	String sessionId = (String) session.getAttribute("ID");
	String sessionPw = (String) session.getAttribute("PW");
%>
<html>
<head>


<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script> -->
<title>새롭게추가된 단어</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<c:set var="isPwTrue" value="${b.connum}" />
<script>
	window.onload = function() {
		var aboveCommentSection = document
				.getElementsByClassName("aboveCommentSection");
		var belowCommentSection = document
				.getElementsByClassName("belowCommentSection");
		var deleteTag = document
		.getElementsByClassName("deleteTag");

		for (var i = 0; i < aboveCommentSection.length; i++) {
			aboveCommentSection.item(i).style.display = "none";
		}
		for (var i = 0; i < belowCommentSection.length; i++) {
			belowCommentSection.item(i).style.display = "none";
		}
		for (var i = 0; i < deleteTag.length; i++) {
			deleteTag.item(i).style.display = "none";
		}
		
		var isPwTrue = new Array();
		
		<c:forEach items="${isPwTrue}" var="isPwTrue">
			var json = new Object();
			json.isPwTrue = "${isPwTrue.isPwTrue}"
			result.push(json);
		</c:forEach>
		
		if (isPwTrue == true){
			alert("비밀번호가 다릅니다.");
		}
		if (isPwTrue ==	false){
			alert("비밀번호가 다릅니다.");
		}
		
	}

	var session =
<%=sessionId%>
	;
	var isSession = false;
	if (session != null) {
		isSession = true;
	}

	function commentClick(num) {
		var commentSection = 'button' + num;
		var con = document.getElementById(commentSection);

		if (isSession == false) {
			alert("먼저로그인을 해주세요");
		} else {
			if (con.style.display == "none") {
				con.style.display = 'block';
			} else {
				con.style.display = 'none';
			}
		}
	}

	function belowCommentClick(num) {
		var commentSection = 'commentClickButton' + num;
		var con = document.getElementById(commentSection);

		if (isSession == false) {
			alert("먼저로그인을 해주세요");
		} else {
			if (con.style.display == "none") {
				con.style.display = 'block';
			} else {
				con.style.display = 'none';
			}
		}
	}
	
	function deleteClick(num) {
		var deleteSection = 'deleteSection'
				+ num;
		var con = document
				.getElementById(deleteSection);

		if (isSession == false) {
			alert("먼저로그인을 해주세요");
		} else {
			if (con.style.display == "none") {
				con.style.display = 'block';
			} else {
				con.style.display = 'none';
			}
		}
	}

</script>
</head>
<body class="backGround">
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
					<li class="nav-item"><a class="nav-link" href="newword_write">새로운단어정의하기</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 네브바에 가려서 위쪽안보이기때문에br태그 -->
	<br>
	<br>
	<br>
	<br>
	<div class="container01">
		<div id="newWord">
			<a href="newword_write">새로운단어 정의 추가</a>
		</div>

		<c:forEach items="${MainDefineList}" var="a">
			<div>
				<h1>${a.word}</h1>
			</div>
			<div>
				<h2>단어정의</h2>
			</div>
			<div>${a.info}</div>
			<div>글번호 ${a.num}</div>
			<div>아이디 ${a.id}</div>
			<div>추천수 ${a.up}</div>
			<div>비추천수 ${a.down}</div>
			<a href="define_write?num=${a.num}">이 단어의 새로운 정의 추가</a>
			<br>

			<a href="javascript:commentClick(${a.num});">comment</a>

			<table class="table">
				<tr>
					<form action="defineWriteSub" method="post">
						<td id="button${a.num}" class="aboveCommentSection"><textarea
								name="subcon" rows="2" cols="30"></textarea> <input
							type="hidden" name="num" value="${a.num}" /> <input
							type="hidden" name="space" value="0" /> <input
							type="hidden" name="pw" value="<%=sessionPw%>" /> <input type="submit"
							value="댓글달기"></td>
					</form>
				</tr>
			</table>

			<!-- 댓글표시 -->

			<c:set var="num" value="${a.num}" />

			<table class="table">
				<c:forEach items="${getDefinSubList}" var="b">

					<c:set var="connum" value="${b.connum}" />

					<c:if test="${num eq connum}">
						<tr>
							<td>
								<div>
									<div class="subViewSection">
										<c:forEach begin="0" end="${b.space}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
									</div>
									<c:set var="space" value="${b.space}" />
									<div class="subViewSection">
										<c:if test="${space > 0}">
										
											<!-- 댓글표시 화살표 도형 -->
											<div id="diagram"></div>
											<div class="cont-box-pseudo"></div>
										</c:if>
									</div>
									<div class="subViewSection">
										<a href="javascript:belowCommentClick(${b.num});"
											style="color: black;">
											<div id="subView">&nbsp;&nbsp;${b.content}</div>
										</a>
									</div>
									
									<!-- 공백 -->
									<div class="temp">
									&nbsp;&nbsp;
								 	</div>

									<!-- 댓글삭제 입력창과 삭제버튼 -->
									<div class="deleteTag" id="deleteSection${b.num}">
										<form action="deleteDefineSub" method="post">
											<div>
												<input type="text" id="subPw" name="PW" placeholder="비밀번호">
												<input type="hidden" name="NUM" value="${b.num}">
											</div>
											<div>
												<input type="submit" id="deleteButton" value="댓글삭제" />
											</div>
										</form>
									</div>
									
									<!-- 댓글버튼 -->
									<div class="deleteShowTag">
									<a href="javascript:deleteClick(${b.num});">
										<input type="submit" id="deleteShowButton" value="댓글삭제" />
									</a>
									</div>
								</div>
							</td>
						</tr>

						<tr>
							<!-- 댓글의댓글 -->
							<form action="defineSecondSub" method="post">

								<td id="commentClickButton${b.num}" class="belowCommentSection">
									<div class="textSection">
										<c:forEach begin="0" end="${b.space}">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
									</div>
									<div class="textSection">

										<!-- 댓글표시 화살표 -->
										<div id="diagram"></div>
										<div class="cont-box-pseudo"></div>
									</div>
									<div class="textSection">&nbsp;&nbsp;</div>
									<div class="textSection">
										<textarea name="subcon" rows="2" cols="40"></textarea>
										<input type="hidden" name="subnum" value="${b.num}" /> <input
											type="hidden" name="space" value="${b.space+1}" /> <input
											type="hidden" name="connum" value="${a.num}" /> <input
											type="hidden" name="pw" value="<%=sessionPw%>" /> <input
											type="submit" value="댓글달기" />
									</div>
								</td>
							</form>
						</tr>
					</c:if>

				</c:forEach>

			</table>

			<br>
			<br>
		</c:forEach>
	</div>

</body>
</html>
