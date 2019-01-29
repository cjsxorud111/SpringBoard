<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.example.dto.GetContentVO"%>
<%
	String id = (String) session.getAttribute("ID");
	System.out.println(id + "님 환영합니다.");
	String num = request.getParameter("num");
%>
<html>
<head>
<title>Home</title>
<script>
window.onload=function(){ 
	/* document.getElementByClassName('ttt').style.display="none"; */
	var section2s = document.getElementsByClassName("ttt");

	for( var i = 0; i < section2s.length; i++ ){
	    var section2 = section2s.item(i);
	    section2.style.display="none";
	}
}

function button1_click(a) {
	var aa = 'button' + a;
	document.getElementById(aa).style.display="block";
} 

function sub_del(a) {
	var aa = 'button' + a;
	document.getElementById(aa).style.display="none";
} 

</script>
</head>
<body>
<div class="container">
	<br><br><br>
	<%@ include file="nav.jsp" %>
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
					<li class="nav-item active"><a class="nav-link" href="home">답글형
							게시판 </a></li>
					<li class="nav-item"><a class="nav-link" href="photo">사진
							게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="file">파일
							업로드게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="login">로그인</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="memberjoin">회원가입</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<h1><%=id%>님 환영합니다!
	</h1>
	글목록

	<br />


	<table>
		<c:forEach items="${GetContentList}" var="a">
			<tr>
				<td>글번호</td>
				<td><%=num%></td>
			</tr>

			<tr>
				<td>제목</td>
				<td>${a.title}</td>
			</tr>

			<tr>
				<td>글내용</td>
				<td>${a.content}</td>
			</tr>
		</c:forEach>


	</table>
	<br>
	<br>


	<a href="newupdate?num=<%=num%>">수정하기</a>
	<a href="delete?num=<%=num%>">삭제하기</a>
	<br>
	<br>
	<br>
	<br>
	<table class="table">
		<tr>

			<td>댓글달기<%=num%></td>
			<form action="writesub" method="post">
				<td>
				<textarea name="subcon" rows="5" cols="140"></textarea>
				</td> 
				<input type="hidden" name="num" value="<%=num%>" />
				<input type="hidden" name="space" value="0" /> 
				
				<td><input type="submit" value="댓글달기"></td>
			</form>
		</tr>
	</table>
	<table class="table">
		<c:forEach items="${GetSubList}" var="a">
			<tr>

				<td>${a.num}</td>
				<td><c:forEach begin="0" end="${a.space}">ㅡ</c:forEach>${a.content}</td>
				<td>${a.space}${a.uid}</td>
					
				<td><input type="button" id="button"
					onclick="button1_click(${a.num});" value="대댓글" /></td>

				<form action="writesub" method="post">
					<td id="button${a.num}" class="ttt">
					<textarea name="subcon" rows="5" cols="140"></textarea> 
					<input type="hidden" name="num" value="<%=num%>" /> 
					<input type="hidden" name="subnum" value="${a.num}" /> 
					<input type="hidden" name="space" value="${a.space+1}" /> 
					<input type="hidden" name="uuid" value="${a.uid}" /> 
					<input type="hidden" name="num" value="<%=num%>" /> 
					<input type="hidden" name="ssuborno" value="ssub" /> 
					
					<input type="submit" value="댓글달기" /> 
					<input type="button" id="sub${a.num}" onclick="sub_del(${a.num});" value="대댓글취소"/></td>
				</form>
			</tr>
		</c:forEach>
	</table> 
	</div>
	</body>
</html>
