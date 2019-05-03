<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<%
	String sessionId = (String) session.getAttribute("ID");
%>
<html>
<head>


<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script> -->
<title>새롭게추가된 단어</title>
<link href="resources/css/style.css?after" rel="stylesheet"
	type="text/css">

<script>
	window.onload = function() {
		/* alert("로딩 완료"); */
		var section2s = document.getElementsByClassName("ttt");
		
		for (var i = 0; i < section2s.length; i++) {
			
			section2s.item(i).style.display = "none";
		}
	}
	
	var session=<%=sessionId%>;
	var isSession = false;
	if(session != null){
		isSession = true;
	}
	
	function commentClick(num) {
		var commentSection = 'button' + num;
		var con = document.getElementById(commentSection);
		
		if(isSession == false){
			alert("먼저로그인을 해주세요");
		} else {
			if(con.style.display == "none"){
				con.style.display = 'block';
		    }else{
		    	con.style.display = 'none';
		    }
			/* show(con);  */
			
		} 
	}
/* 
	function show(con) {
		if(con.style.display == "none"){
			con.style.display = 'block';
	    }else{
	    	con.style.display = 'none';
	    }
	}
	 */
	
	function sub_del(a) {
		var aa = 'button' + a;
		document.getElementById(aa).style.display = "none";
	} 
	
	
</script>
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


	<div class="container">
		<br> <br> <br>

	</div>
	<div id="newword">
		<a href="newword_write">새로운단어 정의 추가</a>
	</div>
	<div id="gogo">댓글달기</div>
	<input type="button" onclick="butt();" value="글" />
	<br>
	<div class="container01">
		<c:forEach items="${MainDefineList}" var="a">
			<div>
				<h1>${a.word}</h1>
			</div>
			<div>
				<h2>단어정의</h2>
			</div>
			<div>${a.info}</div>
			글번호<div>${a.num}</div>
			아이디<div>${a.id}</div>
			추천수<div>${a.up}</div>
			비추천수<div>${a.down}</div>
			<a href="define_write?num=${a.num}">이 단어의 새로운 정의 추가</a>
			<br>

			<a href="javascript:commentClick(${a.num});">comment</a>

			<table class="table">
				<tr>
					<form action="define_sub" method="post">
						<td id="button${a.num}" class="ttt"><textarea name="subcon"
								rows="2" cols="30"></textarea> <input type="hidden" name="num"
							value="${a.num}" /> <input type="hidden" name="space" value="0" />
							<input type="submit" value="댓글달기"></td>
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
							<%-- <td>${a.num}</td> --%>
							<td><c:forEach begin="0" end="${b.space}">ㅡ</c:forEach>${b.content}
						</tr>
						<tr>
							<!-- 댓글의댓글 -->
							<form action="defineSecondSub" method="post">

								<td id="button${b.num}"class="subTextArea">
								<textarea name="subcon" rows="2" cols="40"></textarea> 
								<input type="hidden" name="subnum" value="${b.num}"/> 
								<input type="hidden" name="space" value="${b.space+1}"/> 
								<input type="hidden" name="connum" value="${a.num}"/> 
								<input type="submit" value="댓글달기"/>

								</td>
							</form>
						</tr>
					</c:if>

				</c:forEach>
			</table>
	</div>
	<br>
	<br>
	</c:forEach>
	</div>

</body>
</html>
