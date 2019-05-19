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
<script>
	
	window.onload = function() {
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
	}
	
	var session = '<%=sessionId%>';
	
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
		var userInput = prompt("비밀번호를 입력해주세요" + "");
		deleteSub(num, userInput);
	}

	function deleteSub(num, userInput) {
		$.ajax({
			type : "POST", //전송방식을 지정한다 (POST,GET)
			url : "deleteDefineSub",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
			dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
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
	
	function deleteCheck(conNum, id){ 
		if (isSession == false) {
			alert("먼저로그인을 해주세요");
		} else{
			/* 세션id와 글id대조하여 일치시삭제 */
			if(session == id){
				var really = confirm("삭제하시겠습니까?");
				if(really){
					defineContentDelete(conNum);
					location.reload();
				}
			}else{
				alert("작성하신글만 삭제할수있습니다.")
			}
		}
	}
	
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
							var num = 0;
							var endNum = 0;
							
							function wordClick(word){
								$("#inputText").val(word);
							}
							
							$('#inputText').keyup(function(event){
								var keySet = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㄲㄸㅃㅆㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㅐㅒㅔㅖ1234567890,.?/<>:;abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
								if(event.keyCode == 38){ //위방향키눌렀을때
									num--;
									if(num <= 0){
							    		num = endNum;
							    		var divId = "num"+ 1;
							    		$('#' + divId).css( "background-color", "white" );
							    	}
									var id = "num"+ num;
									var bottom = "num" + Number(num + 1);
							    	var text = document.getElementById(id).value;
							    	$("#inputText").val($('#' + id).text());
							    	$('#' + id).css( "background-color", "#99FF99" );
							    	$('#' + bottom).css( "background-color", "white" );
							    	
							    } else if(event.keyCode == 40){ //아래방향키눌렀을때
							    	num++;
							    	if(num > endNum){
							    		num = 1;
							    		var divId = "num"+ endNum;
							    		$('#' + divId).css( "background-color", "white" );
							    	}
							    	var id = "num"+ num;
							    	var up = "num" + Number(num - 1);
							    	
							    	var text = document.getElementById(id).value;
							    	$("#inputText").val($('#' + id).text());
							    	$('#' + id).css( "background-color", "#99FF99" );
							    	$('#' + up).css( "background-color", "white" );
							    }
								
								if(keySet.indexOf(event.key)!= -1){
									num = 0;
									var inputText = $("#inputText").val();
									
									$.ajax({
										type : "POST", //전송방식을 지정한다 (POST,GET)
										url : "searchWord",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
										dataType : "json",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
										data : {
											inputText : inputText
										},
										error : function() {
											alert("error");
										},
										success : function(parseData) {
											$("#searchRecommendSection").html(parseData.show);
											endNum = parseData.num;
										}
									});
								}
							});
							
							function loseFocus(){
								setTimeout(function() {
									textOut();
								}, 150);
							} 
							//포커스잃었을때추천창끔
							function textOut(){
								var searchRecommendSection = document.getElementById("searchRecommendSection");
								searchRecommendSection.innerHTML="";
							}
							
						</script>
						<button type='submit' class='sch_smit'>검색</button>
					</form>

					<div id="navLink">
						&nbsp;&nbsp;&nbsp; 
						<a href="define" style="color: white;">새롭게정의된단어</a>
						&nbsp;&nbsp;&nbsp;
						<a href="newword_write" style="color: white;">새로운단어정의하기</a>
						&nbsp;&nbsp;&nbsp;
						<a href="login" style="color: white;">로그인</a>
						&nbsp;&nbsp;&nbsp; 
						<a href="memberjoin" style="color: white;">회원가입</a>
						&nbsp;&nbsp;&nbsp;
					</div>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<c:forEach items="${MainDefineList}" var="a">
		<div id="container01" style="position: relative;">
			<div id="container02">
				<div>
					<h1>
					<form action="linkWord" id="frm${a.num}" method="get">
					<input type="hidden" name="linkWord" value="${a.word}">
					<a href="#" onclick="document.getElementById('frm${a.num}').submit();">${a.word}</a>
					</form>
					</h1>
				</div>
				<div>
					<h2>단어정의</h2>
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
				<script>

				function defineContentDelete(conNum){
					$.ajax({
						type : "POST", //전송방식을 지정한다 (POST,GET)
						url : "deleteDefineContent",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
						dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
						data : {
							conNum : conNum
						},
						error : function() {
							alert("error");
						},
						success : function(Parse_data) {
								alert("삭제되었습니다.");
						}
					});
				}
				
				function recommendUp(upNumber, conNum){
					$("#te").text("test");
					
					if (isSession == false) {
						alert("먼저로그인을 해주세요");
					} else{
						$.ajax({
							type : "POST", //전송방식을 지정한다 (POST,GET)
							url : "recommendUp",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
							dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
							data : {
								upNumber : upNumber,
								conNum : conNum
							},
							error : function() {
								alert("error");
							},
							success : function(Parse_data) {
								
								if (Parse_data == 'yes') {
									alert("추,비추천은 한번만 가능합니다.");
								} else {
									alert("추천 되었습니다.");
									var id = 'recommendUp'+conNum;
									var num = upNumber + 1;
									document.getElementById(id).innerHTML="추천: " + num;
								}
							}
						});
					}
				}
				function recommendDown(downNumber, conNum){
					if (isSession == false) {
						alert("먼저로그인을 해주세요");
					} else{
						$.ajax({
							type : "POST", //전송방식을 지정한다 (POST,GET)
							url : "recommendDown",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
							dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
							data : {
								downNumber : downNumber,
								conNum : conNum
							},
							error : function() {
								alert("error");
							},
							success : function(Parse_data) {
								if (Parse_data == 'yes') {
									alert("추,비추천은 한번만 가능합니다.");
								} else {
									alert("비추천 되었습니다.");
									var id = 'recommendDown'+conNum;
									var num = downNumber + 1;
									document.getElementById(id).innerHTML="추천: " + num;
								}
							}
							
						});
					}
				}
				
				</script>

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
