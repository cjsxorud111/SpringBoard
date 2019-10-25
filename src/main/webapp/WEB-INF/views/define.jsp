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
<title>신조어사전 헬로워드!!</title>
<link rel="shorcut icon" href="resource/img/logoImage2.ico" /> 
<meta name="naver-site-verification"
	content="b8b1382411790af26ef1553f419ce247d8465d24" />
<meta name="google-site-verification"
	content="SQWRkLaGa_ZfTcO7MTunO6zcad_-7bFPg_bdazI4Zk8" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="resources/css/defineStyle.css?after" rel="stylesheet"
	type="text/css">
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="<c:url value="resources/js/defineJavaScript.js" />"></script>
<script async
	src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>

<script type="text/javascript">
	
	var session = '<%=sessionId%>';
	var isSession = false;
	if (session != "null") {
		isSession = true;
	}
	
	function writeLoginCheck() {
		if(isSession == true) {
			window.location.href='newwordwrite';
		} else {
			alert("로그인이 필요합니다.");
			window.location.href='login?word=write(*)';
		}
	}
	
	function getQuerystring(paramName){ 
		var tempUrl = window.location.search.substring(1); //url에서 처음부터 '?'까지 삭제 
		var tempArray = tempUrl.split('&'); //'&'을 기준으로 분리하기 
		
		for(var i = 0; tempArray.length; i++) { 
			var keyValuePair = tempArray[i].split('='); // '=' 을 기준으로 분리하기 
			
			if(keyValuePair[0] == paramName){ // _keyValuePair[0] : 파라미터 명 // _keyValuePair[1] : 파라미터 값 
				return keyValuePair[1]; 
			} 
		} 
	}
	
</script>
<%
	//페이지 블록 계산 
	int thisPage = 0;
	int begin = 0;
	int end = 0;
	
	String pages = request.getParameter("page");
	
	if (pages == null) {
		thisPage = 1;
	} else {
		thisPage = Integer.parseInt(pages);
	}
	
	begin = thisPage * 10 - 10;
	end = thisPage * 10 - 1;
%>

<script>
	window.onload = function() {
		var num = 0;
		<c:forEach items="${MainDefineList}" var="a" begin="<%=begin%>" end="<%=end%>">
			num++;
		</c:forEach>
		
		if(num == 1){
			$('#innerSection').css('height','85%');
		}
		$('#loading').hide();
		
		var stringVal = window.location.href;
	    substring = "?";
	    
	    var divcon = "<span style='box-shadow:4px 4px 1px 1px gray;'>"; 
	    divcon += "<div style='text-align:center; background-color:white; padding:1.5rem; width:39rem; margin-bottom:0.6rem;'>"; 
	    divcon += "<div style='color:#F54708; margin-bottom: 1rem; font-size:30px; font-weight:10rem;'>신조어사전 HelloWord!에 오신것을 환영합니다.</div>"; 
	    divcon += "<div style='font-size: 18px;'>신조어사전HelloWord! 는 여러분이 직접 정의하는 사전입니다.</div>" ; 
	    divcon += "<div style='font-size: 18px;'>원하는 신조어를 무엇이든 자유롭게 정의하고 다른사람의 정의를 추천해주세요!";
	    divcon += "</div><form action='select'><select name='sort' style='margin-top:10px;'>";
	    divcon += "<option value=''>글 순서정렬</option>";
	    divcon += "<option value='최근 정의된 순서'>최근 정의된 순서</option>";
	    divcon += "<option value='댓글 많은순서'>댓글 많은순서</option>";
	    divcon += "<option value='추천수 순서'>추천수 순서</option>";
	    divcon += "<option value='정의 많은순서'>정의 많은순서</option>";
	    divcon += "</select><input type='submit' style='height:23px; margin-left:10px; position: relative; top:3px;' value='정렬'></form></div>";
	    divcon += "</span>";
	    
		if(stringVal.indexOf(substring) == -1){
			document.getElementById("explain").innerHTML = divcon;
		}
		
		var aboveCommentSection = document
				.getElementsByClassName("aboveCommentSection");
		var belowCommentSection = document
				.getElementsByClassName("belowCommentSection");
		var deleteTag = document.getElementsByClassName("deleteTag");
		
		for (var i = 0; i < deleteTag.length; i++) {
			deleteTag.item(i).style.display = "none";
		}
		if(session == 'null'){
			document.getElementById("log").innerHTML = "<a href='login?word=${textStatusVO.textStatus}' style='color: white; text-decoration: none;'>Login</a>";
		}else{
			document.getElementById("log").innerHTML = "<a href='javascript:logout();' id='logout' style='color: white; text-decoration: none;'>Logout</a>";
		}
	}
	function logout(){
		$.ajax({
			type : "GET", // 전송방식을 지정한다 (POST,GET)
			url : "logout",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
			dataType : "text",// 호출한 페이지의 형식xml,json,html,text
			data : {
			},
			error : function() {
				alert("error");
			},
			success : function(success) {
				location.reload();
			}
		});
	}
	
	//오른쪽창 스크롤 따라이동 함수    
	function scroll_follow(id){
		//스크롤이 움직일때마다 이벤트 발생
		$(window).scroll(function() {   
			 var scrollBottom = $(window).scrollTop() + $(window).height();
		     var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
		     $("#scroll").css('top',position ); //해당 오브젝트 위치값 재설정
		     
		});
	}
	
	function scroll_stop(id){
		//스크롤이 움직일때마다 이벤트 발생
		$(window).scroll(function() {   
			 var scrollBottom = $(window).scrollTop() + $(window).height();
		     var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
		     $("#scroll").css('top',2000 ); //해당 오브젝트 위치값 재설정
		     
		});
	}
	scroll_follow("#scroll");
	
</script>
<style>
.foot {
	padding: 15px;
	text-align: center;
}

.container-fluid {
	padding-right: 50px;
	padding-left: 50px;
	margin-right: auto;
	margin-left: auto;
}
</style>
</head>
<!-- 네브바 -->
<div style="background-color: #33353C; height: 3.8rem;">
	<div style="width: 1000px; width: 56rem; padding-top: 10px; margin: auto;">
		<!-- 로고 -->
		<div style="float: left;">
			<a href="http://www.helloword.kr/" id="title"
				style="color: white; margin-left: 0.1rem; margin-bottom: 1rem; position: relative; bottom: 10px; font-weight: 200;">
				<div id="logo"
					style="position: relative; z-index: 100; ">HelloWord!</div>
				<div
					style="width: 176px; height: 1px; position: absolute; bottom: 17.5px; background-color: #FFCC00;"></div>
				<div
					style="width: 176px; height: 1px; position: absolute; bottom: 19.5px; background-color: #FFAC07;"></div>
				<div
					style="width: 176px; height: 1px; position: absolute; bottom: 21.5px; background-color: #FF9C07;"></div>
			</a>
		</div>
		<!-- 검색창 -->
		<div style="float: left; margin-left: 5rem; margin-top: -1px;">
			<form class="form-inline" action="linkWord" method="get">
				<!-- 텍스트박스 -->
				<div>
					<input type='text' name=linkWord autocomplete="off" id='inputText'
						class='input_text' style="width: 310px; border: none;"
						onfocusout="loseFocus()" /> <div id="searchRecommendSection"
						style="position: absolute; width: 240px; top: 51px; z-index: 100;"></div>
				</div>

				<!-- 돋보기 -->
				<button type='submit'
					style="margin-bottom: 13px; margin-left: 10px;" class='sch_smit'>
					<span class="demoSpan1">
				</button>
			</form>
		</div>
		<!-- 검색창오른쪽링크 -->
		
		<div id="navLink" style="float: left; margin-left: 19px;">
			<a href="#" id="leftword" onclick="writeLoginCheck()"
				style="position: relative; left: 45px; top: 6px; color: white; margin-left: 0.1rem; font-size: 1.2rem; text-decoration: none; font-weight: 350;">새단어정의하기</a>
			<a href="#" id="log"
				style="position: relative; left: 57px; top: 6px; margin-left: 2rem; color: white; font-family: sans-serif; font-size: 1.25rem; text-decoration: none; font-weight: 400;"></a>
		</div>
	</div>
</div>

<!-- 전체배경 -->
<div class="container-fluid"
	style='position: relative; background: #FCB900;'>
	<!-- 내부배경 -->
	<div id='innerSection'
		style="width: 896px; padding-bottom: 0.3rem; padding-top: 17px; margin: auto;">

		<!-- 오른쪽창 -->
		<div id="scroll"
			style="background-color: #FCB900; position: relative; left: 0; top: 0; padding-bottom: 15px; width: 250px; float: right;">
			
			<div style="background-color: white; box-shadow:4px 4px 1px 1px gray; padding: 10px; margin-bottom: 25px;">
			
				<div
					style="padding-top: 0.7rem; margin-bottom: 1rem; margin-left: 2.5rem; font-weight: 700;">
					최근정의가 추가된 단어</div>
				<c:forEach items="${MainDefineList}" var="a" end="4">
					<div class="wordShortCut">
						<!-- 단어표시 -->
						<div class="myDIV">
							<a href="#" style="text-decoration: none; font-weight: 800;"
								onclick="document.getElementById('frm${a.num}').submit();">${a.word}</a>
						</div>
						<!-- 날짜표시 -->
						<div
							style="margin-left: 5px; font-size: 0.8rem; float: right; display: inline-block;">${a.currenttime}</div>
					</div>
				</c:forEach>
			</div>
			
			<div style="background-color: white; box-shadow:4px 4px 1px 1px gray; padding: 10px;">
				<div style="padding-top: 0.7rem; margin-bottom: 1rem; margin-left: 3rem; font-weight: 700;">단어를 정의한 회원랭킹</div>
				
				<c:forEach items="${memberRanking}" var="a" end="4" varStatus="status">
					<div class="wordShortCut">
						<!-- 단어표시 -->
						<div style="color:#F54708; font-weight: 700">
							${status.count}. ${a.id} 
							<div style="float:right; color:black; font-size: 15px; font-weight: 800">${a.count} 개</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- 웹사이트설명창 -->
		<div id="explain" style="font-weight: 500;"></div>
		
		<!-- 왼쪽창 -->
		<c:forEach items="${MainDefineList}" var="a" begin="<%=begin%>"
			end="<%=end%>">
			<div id="container03" style="">
				<!-- 왼쪽창에서 실제컨텐츠 표시부분 -->
				<div id="container04"
					style="width: 39rem; padding-left: 1.5rem; box-shadow:4px 4px 1px 1px gray; padding-right: 1.5rem; 
					padding-top: 1.5rem; margin-top:1.25rem; margin-bottom: 1.25rem; background-color: #FFFFFF;">
					<!-- 단어제목으로검색링크 -->
					<div>
						<div style="float: right;">
							<div>${a.currenttime}</div>
							<div style="">
								<a href="thiswordwrite?word=${a.word}"
									style="float: right; text-decoration: none;">새정의추가</a>
							</div>
							<div style="">&nbsp;</div>
							<!-- 글수정 글삭제버튼 -->
							<div id="delete">
								<div style="float: right;">
									<button
										onclick="deleteCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}');"
										class="defineDeleteButton" style="font-weight: 500;">삭제</button>
								</div>
								<div style="float: right;">
									<form action="defineContentModify" method="post"
										onsubmit="return modifyCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}')">
										<input type="hidden" name="conNum" value="${a.num}" /> <input
											type="hidden" name="textStatus"
											value="${textStatusVO.textStatus}" />
										<button class="modifyButton" style="font-weight: 500;">수정</button>
									</form>
								</div>
							</div>

							<script>
								function modifyCheck(num, id, textStatus){
									if (isSession == false) {
										alert("먼저로그인을 해주세요");
										return false;
									} else {
										/* 세션id와 글id대조하여 일치시삭제 */
										if (session == id) {
											var really = confirm("수정하시겠습니까?");
											if (really) {
												return true;
											}
										} else {
											alert("작성하신글만 수정할수있습니다.")
											return false;
										}
									}
								}
								function delConCheck(num, id, textStatus){
									if (isSession == false) {
										alert("먼저로그인을 해주세요");
										return false;
									} else {
										/* 세션id와 글id대조하여 일치시삭제 */
										if (session == id) {
											var really = confirm("삭제하시겠습니까?");
											if (really) {
												return true;
											}
										} else {
											alert("작성하신글만 삭제할수있습니다.")
											return false;
										}
									}
								}
								
								function deleteCheck(conNum, id, textStatus) {
									if (isSession == false) {
										alert("먼저로그인을 해주세요");
									} else {
										/* 세션id와 글id대조하여 일치시삭제 */
										if (session == id) {
											var really = confirm("삭제하시겠습니까?");
											if (really) {
												defineContentDelete(conNum, textStatus);
											}
										} else {
											alert("작성하신글만 삭제할수있습니다.")
										}
									}    
								}
								
								function defineContentDelete(conNum, textStatus) {
									$.ajax({
										type : "POST", // 전송방식을 지정한다 (POST,GET)
										url : "deleteDefineContent",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서
																	// 사용해도된다.
										dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
															// 있다.
										data : {
											conNum : conNum,
											textStatus : textStatus
										},
										error : function() {
										/* 	alert("error"); */
											if(textStatus == "main(*)"){
												window.location.href='/';
											}else{
												window.location.href='linkWord?linkWord='+textStatus;
											}
										},
										success : function(Parse_data) {
												alert("gheelo");
											if(textStatus == "main(*)"){
												window.location.href='/';
											}else{
												window.location.href='linkWord?linkWord='+textStatus;
											}
										}
									});
								}
							</script>
						</div>
						<!-- 오른쪽단어제목 -->
						<h1>
							<form action="linkWord" id="frm${a.num}" method="get">
								<input type="hidden" name="linkWord" value="${a.word}">
								<a href="#"
									onclick="document.getElementById('frm${a.num}').submit();"
									style="text-decoration: none; font-color: black; font-weight: 600;">${a.word}</a>
							</form>
						</h1>
					</div>
					<div>${a.info}</div>
					<div>글번호: ${a.num}</div>

					<!-- 추천, 비추천 버튼 -->
					<div id="rate" style="float: right;">
						<div>
							<button onclick="recommendUp(${a.up}, ${a.num});"
								id="recommendUp${a.num}" onmouseover="recommendUpOver(${a.num})"
								onmouseout="recommendUpOut(${a.num})" class="upVote">추천:
								${a.up}</button>
						</div>
						<div>
							<button onclick="recommendDown(${a.down}, ${a.num});"
								id="recommendDown${a.num}"
								onmouseover="recommendDownOver(${a.num})"
								onmouseout="recommendDownOut(${a.num})" class="downVote">비추천:
								${a.down}</button>
						</div>
					</div>

					<script>
						function recommendUpOver(num){
							var id = "recommendUp" + num;
							$('#' + id).css("background-color", "#5BB93F");
						}
						function recommendDownOver(num){
							var id = "recommendDown" + num;
							$('#' + id).css("background-color", "tomato");
						}
						function recommendUpOut(num){
							var id = "recommendUp" + num;
							$('#' + id).css("background-color", "white");
						}
						function recommendDownOut(num){
							var id = "recommendDown" + num;
							$('#' + id).css("background-color", "white");
						}
					</script>

					<div>글쓴이: ${a.id}</div>
					<br>
					<div>
						<a
							href="javascript:aboveComment(${a.num}, ${lastGroupNum.groupnum});"
							style="margin-top: 1rem; font-weight: 600; text-decoration: none;">댓글달기</a>
					</div>
					<table frame=void>
						<tr>
							<!-- 기본댓글달기 -->

							<td id="button${a.num}" class="aboveCommentSection"></td>
						</tr>
					</table>
					<script>
				function aboveComment(num, groupnum) {
					
					if(isSession == false) {
						alert("먼저로그인을 해주세요");
					} else{
						var sec = 'button' + num;
						var textA = 'textArea' + num;
						var arrowhtml = "<textarea id='"+textA+"' name='subcon' rows='2' cols='52' style='position: relative; top:0.5rem; right:1.7rem; margin-left: 2rem; margin-bottom: 1.7rem; width: 29rem; height: 55px; table-layout: fixed;'></textarea>";
						var innerhtml = "<div style='position: relative; top:0.5rem; right:1.7rem; font-size: 80%; float: right;'><div><input type='submit' style='height:1.7rem;' onclick=\"writeSub('"+textA+"','"+num +"', '0', '"+groupnum+"','<%=sessionPw%>', '<%=sessionId%>');\" value='댓글달기'/></div>";
						var temp = "<div><input type='submit' style='height: 1.73rem;' onclick=\"cancelSecondSub('"+sec+"');\" value='댓글취소'/></div></div>"
						var innerarrowhtml = arrowhtml + innerhtml + temp;
						$('#'+sec).html(innerarrowhtml); 
					}
				}
				
				function writeSub(textA, num, space, groupNum, pw, id) {
					var textValue = $('#'+textA).val();
					if(textValue == ""){
						alert("1글자이상 입력해주세요")
					}else{
						$.ajax({
							type : "POST", // 전송방식을 지정한다 (POST,GET)
							url : "defineWriteSub",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
							dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
							// 있다.
							data : {
								textValue : textValue,
								num : num,
								space : space,
								groupNum : groupNum,
								pw : pw,
								id : id
							},
							error : function() {
								alert("error");
							},
							success : function(success) {
								location.reload();
							}
						});
					}
				}
				</script>
					<!-- 댓글표시 -->
					<c:set var="num" value="${a.num}" />
					<div style="margin-top: 10px;"></div>
					<table class="table">
						<c:forEach items="${getDefinSubList}" var="b">

							<c:set var="connum" value="${b.connum}" />

							<c:if test="${num eq connum}">

								<tr>
									<div class="subViewSection"
										style="line-height: 110%; margin-top: -2%;">
										<c:set var="space" value="${b.space}" />
										<!-- jstl에서if else문 -->
										<c:choose>
											<c:when test="${space > 0}">
												<!-- 댓글표시 화살표 도형 -->
												<div id="diagram" style="position: relative; left: 30px;"></div>
												<div class="cont-box-pseudo"
													style="position: relative; left: 30px;"></div>
												<div style="position: relative; left: 43px; bottom: 10px;">${b.id}</div>
												<div id="subView" style="padding-left: 41px; width: 36rem;">
													${b.content}
													<!-- 댓글버튼 -->
												</div>
											</c:when>

											<c:otherwise>
												<div>&nbsp; &nbsp;</div>
												<div style="position: relative; left: 13px; bottom: 10px;">${b.id}</div>
												<div id="subView" style="padding-left: 1rem; width: 36rem;">
													${b.content}
													<!-- 댓글버튼 -->
												</div>

											</c:otherwise>

										</c:choose>
									</div>
									<!-- 답글삭제버튼 -->
									<div class="deleteShowTag"
										style="position: relative; bottom: 2rem;">

										<%-- <a href="javascript:belowCommentClick(${b.num});"> <input
										type="submit" value="답글" style="font-size: 80%" />
									</a>  --%>
										<!-- <a href="#" >  -->

										<input type="submit"
											onclick="ansCommentSec('${b.num}', '${a.num}', '${b.space}', '${b.id}', '${b.groupnum}');"
											value="답글" style="font-size: 80%" />
										<!-- 	</a>  -->

										<%-- (${b.num}, ${a.num}, ${b.space}, ${b.id}, ${b.groupnum} --%>

										<a href="javascript:deleteClick(${b.num});"> <input
											type="submit" id="deleteShowButton" value="삭제" />
										</a>
									</div>

								</tr>
								<tr>
									<!-- 답글 -->
									<div id="commentClickButton${b.num}"
										class="belowCommentSection">

										<div id="ansSec${b.num}"></div>

									</div>
									<script>
								function ansCommentSec(num, connum, space, id, groupnum){
									if(isSession == false) {
										alert("먼저로그인을 해주세요");
									} else{
										var sec = 'ansSec' + num;
										var spacenum = space + 1;
										var textA = 'textAreaSecond' + num;
										var arrowhtml = "<div class='textSection' style='margin-left:0.5rem; margin-bottom:5rem; position: relative; top: 0.8rem; left: 1.4rem;'><div id='diagram'/><div class='cont-box-pseudo'/></div>";
										var innerhtml = "<span style=''><textarea id='"+textA+"' name='subcon' rows='2' cols='55' style='position: relative; left: 2.2rem; margin-bottom: 1rem; margin-top: 0.3rem; height: 55px; table-layout: fixed; float: left;'></textarea></span>"; 
										var writebutton = "<span style='position: relative; bottom:0.8rem; left:2.1rem; bottom:1.2rem; margin-right:0.5rem; font-size: 80%; float: left;'><div><input type='submit' style='height:1.7rem;' onclick=\"writeSecondSub('"+num+"','"+connum+"','"+spacenum+"','"+id+"','"+groupnum+"','<%=sessionPw%>', '<%=sessionId%>');\" value='답글달기'/></div>";
										var calcelbutton = "<div><input type='submit' style='height:1.7rem;' onclick=\"cancelSecondSub('"+sec+"');\" value='답글취소'/></div></span>";
										var innerarrowhtml = arrowhtml + innerhtml + writebutton + calcelbutton;
										$('#'+sec).html(innerarrowhtml); 
									}
								}
								
								function cancelSecondSub(sec){
									$('#'+sec).html(""); 
								}
								
								function writeSecondSub(num, connum, space, answerId, groupNum, pw, id) {
									
									var textId = 'textAreaSecond' + num;
									var textValue = $('#' + textId).val();
									if(textValue == ""){
										alert("1글자이상 입력해주세요");
									}else{
										$.ajax({
											type : "POST", // 전송방식을 지정한다 (POST,GET)
											url : "defineSecondSub",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
											dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
											// 있다.
											data : {
												textVal : textValue,
												num : connum,
												answerId : answerId,
												space : space,
												groupNum : groupNum,
												pw : pw,
												id : id
											},
											error : function() {
												alert("error");
											},
											success : function(success) {
												location.reload();
											}
										});
									}
								}
								</script>

								</tr>

							</c:if>

						</c:forEach>

					</table>

				</div>
			</div>
		</c:forEach>
		
		<div id="paging"
			style="text-align: center; background-color: white; margin-bottom: 1rem;">
			<a href="?page=1">[처음]</a>
			
			<%
				Object totalPageNum01 = request.getAttribute("totalPageNum");
				int totalPageNum02 =  Integer.parseInt(totalPageNum01.toString());
				for (int i = 1; i <= totalPageNum02; i++) {
			%>
			<a href="?page=<%=i%>"><%=i%></a>&nbsp
			<%
				}
			%>
			<a href="?page=<%=totalPageNum02%>">[끝]</a>
		</div>
	</div>

</div>

<div class="foot" style="background-color: #2B2B2E;">
	<div style="color: white;">Copyright 2019. Tae kyoung. all rights
		reserved.</div>
</div>

<script>
//검색창에서 키보드 눌렀을때 동작

var number = 0;
var keynumber = 0;
function mover(num){
	var keyid = "num" + keynumber;
	$('#' + keyid).css("background-color", "white");
	number = num;
	var id = "num"+number;
	$('#' + id).css("background-color", "#E0E0E0");
}
function mout(num){
	number = num;
	var id = "num"+number;
	$('#' + id).css("background-color", "white");
}
/* 추천검색어 */
$('#inputText').keyup(function(event) {
	var keySet = "ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎㄲㄸㅃㅆㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㅐㅒㅔㅖ1234567890,.?/<>:;abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	if (event.keyCode == 38) { // 위방향키눌렀을때
		number--;
		keynumber = number;
		if (number <= 0) {
			number = endNum;
			var divId = "num" + 1;
			$('#' + divId).css("background-color", "white");
		}
		var id = "num" + number;
		var bottom = "num" + Number(number + 1);
		var text = document.getElementById(id).value;
		$("#inputText").val($('#' + id).text());
		$('#' + id).css("background-color", "#E0E0E0");
		$('#' + bottom).css("background-color", "white");

	} else if (event.keyCode == 40) { // 아래방향키눌렀을때
		number++;
		keynumber = number;
		if (number > endNum) {
			number = 1;
			var divId = "num" + endNum;
			$('#' + divId).css("background-color", "white");
		}
		var id = "num" + number;
		var up = "num" + Number(number - 1);

		var text = document.getElementById(id).value;
		$("#inputText").val($('#' + id).text());
		$('#' + id).css("background-color", "#E0E0E0");
		$('#' + up).css("background-color", "white");
	}
	
	if (keySet.indexOf(event.key) != -1) {
		num = 0;
		var inputText = $("#inputText").val();

		$.ajax({
			type : "POST", 
			url : "searchWord",
			dataType : "json",
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

//삭제시 비밀번호체크
function deleteClick(num) {
	var userInput = prompt("비밀번호를 입력해주세요" + "");
	deleteSub(num, userInput);
}

function deleteSub(num, userInput) {
	$.ajax({
		type : "POST", // 전송방식을 지정한다 (POST,GET)
		url : "deleteDefineSub",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
		dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용 가능
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
