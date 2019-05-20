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

<title>새롭게추가된 단어</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">

<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>

<script src="<c:url value="resources/js/defineJavaScript.js" />"></script>
<script>
	// 댓글이나대댓글시 로그인검사위한변수test
	var session = '<%=sessionId%>';
</script>
</head>
<body class="backGround" style="position: relative;">
	<%@ include file="nav.jsp"%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		style="position: relative;">
		<div class="container">
			<div>
				<a href="define" id="title" style="color: white; font-weight: 30px;">신조어사전</a>
			</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">

					<form class="form-inline" action="search" method="get">
						<!-- 검색창 -->
						<span class='green_window'> 
							<input type='text' name=inputText autocomplete="off" id='inputText'
							class='input_text' onfocusout="loseFocus()"/>
							<div id="searchRecommendSection">
							</div>
						</span>
						<script type="text/javascript">
							
						</script>
						<button type='submit' class='sch_smit'>검색</button>
					</form>

					<div id="navLink">
						&nbsp;&nbsp;&nbsp; 
						<a href="define" style="color: white;">새롭게정의된단어</a>
						&nbsp;&nbsp;&nbsp;
						<a href="newword_write" style="color: white;">새로운단어정의하기</a>
						&nbsp;&nbsp;&nbsp;
						<a href="login" style="color: white;">Login</a>
						&nbsp;&nbsp;&nbsp; 
						<a href="memberjoin" style="color: white;">회원가입</a>
						&nbsp;&nbsp;&nbsp;
					</div>
					<div><%=sessionId%></div>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<c:forEach items="${MainDefineList}" var="a">
		<div id="container01" style="position: relative;">
			<div id="container02">
			&nbsp;&nbsp;
			<!-- 단어제목으로검색링크 -->
				<div>
					<h1>
					<form action="linkWord" id="frm${a.num}" method="get">
					<input type="hidden" name="linkWord" value="${a.word}">
					<a href="#" onclick="document.getElementById('frm${a.num}').submit();">${a.word}</a>
					</form>
					</h1>
				</div>
				
				<div>${a.info}</div>
				<div>글번호: ${a.num}</div>
				<div>글쓴이: ${a.id}</div>
				<div id="delete">
					&nbsp;&nbsp;
					<button onclick="deleteCheck(${a.num}, '${a.id}');">글삭제</button>
				</div>

				<div id="rate">
					<button onclick="recommendUp(${a.up}, ${a.num});"
						id="recommendUp${a.num}">추천: ${a.up}</button>
					<button onclick="recommendDown(${a.down}, ${a.num});"
						id="recommendDown${a.num}">비추천: ${a.down}</button>
				</div>

				<a href="define_write?num=${a.num}">이 단어의 새로운 정의 추가</a> <br> <a
					href="javascript:commentClick(${a.num});">댓글+</a>

				<table class="table">
					<tr>
						<form action="defineWriteSub" method="post">
							<td id="button${a.num}" class="aboveCommentSection"><textarea
									name="subcon" rows="2" cols="30"></textarea> <input
								type="hidden" name="num" value="${a.num}" /> <input
								type="hidden" name="space" value="0" /> <input type="hidden"
								name="pw" value="<%=sessionPw%>" /> <input type="submit"
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
										<div class="temp">&nbsp;&nbsp;</div>
										<!-- 댓글버튼 -->
										<div class="deleteShowTag">
											<a href="javascript:deleteClick(${b.num});"> <input
												type="submit" id="deleteShowButton" value="삭제" />
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

				<br> <br>
			</div>
		</div>
	</c:forEach>

</body>
</html>
