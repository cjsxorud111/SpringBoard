<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*"%>
<%
	String sessionId = (String) session.getAttribute("ID");
	String sessionPw = (String) session.getAttribute("PW");
%>
<%@ include file="nav.jsp"%>
<html>

<head>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />

<title>새롭게추가된 단어</title>
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="<c:url value="resources/js/defineJavaScript.js" />"></script>
<script type="text/javascript">
	
	// 댓글이나대댓글시 로그인검사위한변수testtest
	var session = '<%=sessionId%>';
	var isSession = false;
	if (session != "null") {
		isSession = true;
	}
	
	function writeLoginCheck(){
		if(isSession == true){
			window.location.href='newword_write';
		} else {
			alert("로그인이 필요합니다.");
			window.location.href='login';
		}
	}
	
</script>

</head>
<body id="backGround"
	style="background-color: #CACCCE; position: relative;">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		style="position: relative; height: 5rem; background: blue">
		<div class="container">
			<div>
				<a href="define" id="title"
					style="color: white; font-weight: 50px; margin-left: 1rem;">한줄피디아</a>
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
						<span class='green_window'> <input type='text'
							name=inputText autocomplete="off" id='inputText'
							class='input_text' onfocusout="loseFocus()" />
							<div id="searchRecommendSection"></div>
						</span>
						<!-- 돋보기 -->
						<button type='submit' class='sch_smit'>
							<span class="demoSpan1">
						</button>
					</form>
					<div id="navLink">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> &nbsp;&nbsp;&nbsp; <a
							href="#" onclick="writeLoginCheck()"
							style="position: relative; top: 2px; color: white; font-size: 1.7rem; text-decoration: none;">새단어정의하기</a>
						&nbsp;&nbsp;&nbsp; <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <a
							href="" id="log"
							style="position: relative; top: 2px; color: white; font-size: 1.7rem; text-decoration: none;"></a>
						&nbsp;&nbsp;&nbsp;
					</div>

				</ul>
			</div>
		</div>
	</nav>
	<!-- 자바스크립트에서수정이안되기에onload자바스크립트만jsp에만듬 -->
	<script>
	window.onload = function() {
		$('#loading').hide();
		var aboveCommentSection = document
				.getElementsByClassName("aboveCommentSection");
		var belowCommentSection = document
				.getElementsByClassName("belowCommentSection");
		var deleteTag = document.getElementsByClassName("deleteTag");
		
		for (var i = 0; i < aboveCommentSection.length; i++) {
			aboveCommentSection.item(i).style.display = "none";
		}
		for (var i = 0; i < belowCommentSection.length; i++) {
			belowCommentSection.item(i).style.display = "none";
		}
		for (var i = 0; i < deleteTag.length; i++) {
			deleteTag.item(i).style.display = "none";
		}
		if(session == 'null'){
			document.getElementById("log").innerHTML = "<a href='login' style='color: white; text-decoration: none;'>Login</a>";
		}else{
			document.getElementById("log").innerHTML = "<a href='logout' id='logout' style='color: white; text-decoration: none;'>Logout</a>";
		}
	}
	
	function scroll_follow( id ){
		//스크롤이 움직일때마다 이벤트 발생
		$(window).scroll(function( )  { 
		     var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
		     $("#scroll").css('top',  position ); //해당 오브젝트 위치값 재설정
		});
	}
	scroll_follow( "#scroll" );
	</script>
	<!-- 오른쪽창 -->
	<div id="scroll" style="position: absolute; left: 0; top: 0;">
		<div style="margin-bottom: 1rem;">최근정의가 추가된 단어</div>
		<c:forEach items="${MainDefineList}" var="a" end="13">
			<div class="wordShortCut">
				<div class="myDIV">
					<a href="#"
						onclick="document.getElementById('frm${a.num}').submit();">${a.word}</a>
				</div>
				<div
					style="margin-left: 5px; font-size: 0.8rem; float: right; display: inline-block;">${a.currenttime}</div>
			</div>
		</c:forEach>
	</div>

	<c:forEach items="${MainDefineList}" var="a">
		<!-- 왼쪽창 -->
		<div id="container01" style="position: relative;">
			<!-- 왼쪽창에서 실제컨텐츠 표시부분 -->
			<div id="container02">
				&nbsp;&nbsp;
				<!-- 단어제목으로검색링크 -->
				<div>

					<div style="float: right;">
						<div>${a.currenttime}정의됨</div>
						<div>
							<a href="define_write?num=${a.num}" style="float: right">새정의추가</a>
						</div>
						<div>&nbsp;</div>
						<!-- 글수정 글삭제버튼 -->
						<div id="delete" style="float: right;">
							<button class="modifyButton">수정</button>
							<button onclick="deleteCheck(${a.num}, '${a.id}');"
								class="defineDeleteButton">삭제</button>

						</div>

					</div>
					<h1>
						<form action="linkWord" id="frm${a.num}" method="get">
							<input type="hidden" name="linkWord" value="${a.word}"> <a
								href="#"
								onclick="document.getElementById('frm${a.num}').submit();"
								style="text-decoration: none; font-color: black">${a.word}</a>
						</form>
					</h1>
				</div>

				<div>${a.info}</div>
				<%-- <div>글번호: ${a.num}</div> --%>

				<!-- 추천, 비추천 버튼 -->
				<div id="rate" style="float: right;">
					<div>
						<button onclick="recommendUp(${a.up}, ${a.num});"
							id="recommendUp${a.num}" class="upVote">추천: ${a.up}</button>
					</div>
					<div>
						<button onclick="recommendDown(${a.down}, ${a.num});"
							id="recommendDown${a.num}" class="downVote">비추천:
							${a.down}</button>
					</div>
				</div>
				<div>글쓴이: ${a.id}</div>
				<br>
				<div>
					<a href="javascript:commentClick(${a.num});"
						style="margin-top: 1rem; text-decoration: none;">댓글+</a>
				</div>
				<table frame=void>
					<tr>
						<form action="defineWriteSub" method="post">
							<td id="button${a.num}" class="aboveCommentSection"><textarea
									name="subcon" rows="2" cols="30"></textarea> <input
								type="hidden" name="num" value="${a.num}" /> <input
								type="hidden" name="space" value="0" /> <input type="hidden"
								name="pw" value="<%=sessionPw%>" /> <input type="hidden"
								name="id" value="<%=sessionId%>" /><input type="submit"
								value="댓글달기"></td>
						</form>
					</tr>
				</table>

				<!-- 댓글표시 -->
				<c:set var="num" value="${a.num}" />
				<div style="margin-top: 10px;"></div>
				<table class="table">
					<c:forEach items="${getDefinSubList}" var="b">

						<c:set var="connum" value="${b.connum}" />

						<c:if test="${num eq connum}">

							<tr>
								<div class="subViewSection"
									style="line-height: 110%; margin-top: -2%; margin-bottom: -1%;">

									<c:set var="space" value="${b.space}" />
									<!-- jstl에서if else문 -->
									<c:choose>
										<c:when test="${space > 0}">
									       <!-- 댓글표시 화살표 도형 -->
											<div id="diagram" style="position: relative; left: 30px;"></div>
											<div class="cont-box-pseudo" style="position: relative; left: 30px;"></div>
											<div style="position:relative; left:43px; bottom:10px;">${b.id}</div>
											<div id="subView"
												style="padding-left: 41px; width: 36rem;">
												${b.content}
												<!-- 댓글버튼 -->
											</div>
									    </c:when>

										<c:otherwise>
											<div>&nbsp;
											&nbsp;
											  </div>
									        <div style="position:relative; left:13px; bottom:10px;">${b.id}</div>
									   		<div id="subView"
												style="padding-left: 1rem; width: 36rem;">
												${b.content}
												<!-- 댓글버튼 -->
											</div>
									   
									    </c:otherwise>
	
									</c:choose>
								</div>
								<div class="deleteShowTag" style="position:relative; bottom: 2rem;">
									<a href="javascript:belowCommentClick(${b.num});"> <input
										type="submit" value="답글" style="font-size: 80%" />
									</a> <a href="javascript:deleteClick(${b.num});"> <input
										type="submit" id="deleteShowButton" value="삭제" />
									</a>
								</div>

							</tr>

							<tr>
								<!-- 댓글의댓글 -->
								<form action="defineSecondSub" method="post">

									<div id="commentClickButton${b.num}" class="belowCommentSection">
										
										<div class="textSection" style="position:relative; top: 0.8rem; left: 1.4rem;">
											<!-- 댓글표시 화살표 -->
											<div id="diagram"></div>
											<div class="cont-box-pseudo"></div>
										</div>

										<div class="textSection">
											<textarea name="subcon" rows="2" cols="55"
												style="position:relative; left:1rem; margin-left:1.2rem; margin-bottom: 1.7rem; height: 55px; table-layout: fixed;"></textarea>
											<input type="hidden" name="subnum" value="${b.num}" /> <input
												type="hidden" name="space" value="${b.space+1}" /> <input
												type="hidden" name="connum" value="${a.num}" /> <input
												type="hidden" name="id" value="<%=sessionId%>" /> <input
												type="hidden" name="pw" value="<%=sessionPw%>" /> 
										</div>
										<input type="submit" value="댓글달기" style="position:relative; left:1rem; font-size: 80%; float:right;" />
									</div>
								</form>
							</tr>
							

						</c:if>

					</c:forEach>

				</table>

			</div>
		</div>
	</c:forEach>

</body>
<script>
$('#inputText').keyup(function(event) {
	var keySet = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㄲㄸㅃㅆㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㅐㅒㅔㅖ1234567890,.?/<>:;abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	if (event.keyCode == 38) { // 위방향키눌렀을때
		num--;
		if (num <= 0) {
			num = endNum;
			var divId = "num" + 1;
			$('#' + divId).css("background-color", "white");
		}
		var id = "num" + num;
		var bottom = "num" + Number(num + 1);
		var text = document.getElementById(id).value;
		$("#inputText").val($('#' + id).text());
		$('#' + id).css("background-color", "#99FF99");
		$('#' + bottom).css("background-color", "white");

	} else if (event.keyCode == 40) { // 아래방향키눌렀을때
		num++;
		if (num > endNum) {
			num = 1;
			var divId = "num" + endNum;
			$('#' + divId).css("background-color", "white");
		}
		var id = "num" + num;
		var up = "num" + Number(num - 1);

		var text = document.getElementById(id).value;
		$("#inputText").val($('#' + id).text());
		$('#' + id).css("background-color", "#99FF99");
		$('#' + up).css("background-color", "white");
	}

	if (keySet.indexOf(event.key) != -1) {
		num = 0;
		var inputText = $("#inputText").val();

		$.ajax({
			type : "POST", // 전송방식을 지정한다 (POST,GET)
			url : "searchWord",// 호출 URL을 설정한다. GET방식일경우 뒤에
								// 파라티터를 붙여서 사용해도된다.
			dataType : "json",// 호출한 페이지의 형식이다.
								// xml,json,html,text등의 여러 방식을
								// 사용할 수 있다.
			data : {
				inputText : inputText
			},
			error : function() {
				alert("error");
			},
			success : function(parseData) {
				$("#searchRecommendSection").html(
						parseData.show);
				endNum = parseData.num;
			}
		});
	}
});

function deleteClick(num) {
	var userInput = prompt("비밀번호를 입력해주세요" + "");
	deleteSub(num, userInput);
}

function deleteSub(num, userInput) {
	$.ajax({
		type : "POST", // 전송방식을 지정한다 (POST,GET)
		url : "deleteDefineSub",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
		dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
		// 있다.
		data : {
			pw : userInput,
			num : num
		},
		error : function() {
			alert("error");
		},
		success : function(Parse_data) {

			if (Parse_data == 'yes') {
				location.reload();
			} else {
				alert("비밀번호가 다릅니다.");
			}
		}
	});
}

</script>
</html>
