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
			window.location.href='login?word=write(*)';
		}
	}
</script>
<!-- 자바스크립트에서수정이안되기에onload자바스크립트만jsp에만듬 -->
<script>
	window.onload = function() {
		$('#loading').hide();
		var aboveCommentSection = document
				.getElementsByClassName("aboveCommentSection");
		var belowCommentSection = document
				.getElementsByClassName("belowCommentSection");
		var deleteTag = document.getElementsByClassName("deleteTag");
		
		/* for (var i = 0; i < aboveCommentSection.length; i++) {
			aboveCommentSection.item(i).style.display = "none";
		} */
		/* for (var i = 0; i < belowCommentSection.length; i++) {
			belowCommentSection.item(i).style.display = "none";
		} */
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
			dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수
			// 있다.
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
	
	function scroll_follow( id ){
		//스크롤이 움직일때마다 이벤트 발생
		$(window).scroll(function( )  { 
		     var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
		     $("#scroll").css('top',  position ); //해당 오브젝트 위치값 재설정
		});
	}
	scroll_follow( "#scroll" );
	</script>
<style>
footer {
	border-top: 1px solid gray;
	padding: 20px;
	text-align: center;
}

#test01 {
	border-right: 1px solid red;
}

main {
	padding: 10px;
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
	margin-right: auto;
	margin-left: auto;
}
</style>

</head>

<div style="background-color: #121629; height: 5.5rem;">
	<div
		style="width: 1000px; /* border: 1px solid red; */ padding-top: 20px; margin: auto;">
		<div style="float: left;">
			<a href="define" id="title"
				style="color: white; font-weigh: 50px; margin-left: 1rem; margin-bottom: 5rem; position: relative; bottom: 8px;">xx위키</a>
		</div>

		<div style="float: left; margin-left: 5rem;">
			<form class="form-inline" action="linkWord" method="get">
				<!-- 검색창 -->
				<div>
					<input type='text' name=linkWord autocomplete="off" id='inputText'
						class='input_text' style="width: 340px;" onfocusout="loseFocus()" />
					<span id="searchRecommendSection"
						style="position: absolute; width: 330px; top: 71px; z-index: 100; padding-left: 10px;"></span>
				</div>

				<!-- 돋보기 -->
				<button type='submit'
					style="margin-bottom: 13px; margin-left: 10px;" class='sch_smit'>
					<span class="demoSpan1">
				</button>
			</form>
		</div>
		<div id="navLink" style="float: left; margin-left: 3rem;">
			<a href="#" onclick="writeLoginCheck()"
				style="position: relative; bottom: 10px; color: white; font-size: 1.7rem; text-decoration: none;">새단어정의하기</a>
			<a href="#" id="log"
				style="position: relative; bottom: 10px; margin-left: 1rem; color: white; font-size: 1.7rem; text-decoration: none;"></a>
		</div>

	</div>

</div>

<div class="container-fluid" style='background-color: #CACCCE;'>
	<div
		style="width: 900px; /* border: 1px solid red; */ padding-top: 20px; margin: auto;">
		<!-- 오른쪽창 -->
		<div id="scroll"
			style="position: relative; width: 250px; padding: 10px; float: right; left: 0; top: 0;">
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
			<div id="container03" style="margin-top: 2px;">
				<!-- 왼쪽창에서 실제컨텐츠 표시부분 -->
				<div id="container04"
					style="width: 39rem; padding: 1rem; margin-bottom: 1.8rem; background-color: #FFFFFF;">
					&nbsp;&nbsp;
					<!-- 단어제목으로검색링크 -->
					<div>

						<div style="float: right;">
							<div>${a.currenttime}정의됨</div>
							<div style="">
								<a href="define_write?num=${a.num}" style="float: right;">새정의추가</a>
							</div>
							<div style="">&nbsp;</div>
							<!-- 글수정 글삭제버튼 -->
							<div id="delete" style="float: right;">
								<form action="defineContentModify" method="post" onsubmit="return modifyCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}')">
									<input type="hidden" name="conNum" value="${a.num}"/>
									<input type="hidden" name="textStatus" value="${textStatusVO.textStatus}"/>
									<button <%-- onclick="modifyCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}');" --%>
										class="modifyButton">수정</button>
								</form>
								<%-- <form action="deleteDefineContent" method="post" onsubmit="return delConCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}')">
									<input type="hidden" name="conNum" value="${a.num}"/>
									<input type="hidden" name="textStatus" value="${textStatusVO.textStatus}"/>
									<input type="hidden" name="linkWord" value="${textStatusVO.textStatus}"/> --%>
									
									
									<button onclick="deleteCheck(${a.num}, '${a.id}', '${textStatusVO.textStatus}');"
										class="defineDeleteButton">삭제</button>
								<!-- </form> -->
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
												window.location.href='define';
											}else{
												window.location.href='linkWord?linkWord='+textStatus;
											}
										},
										success : function(Parse_data) {
												alert("gheelo");
											if(textStatus == "main(*)"){
												window.location.href='define';
											}else{
												window.location.href='linkWord?linkWord='+textStatus;
											}
										}
									});
								}
							</script>
						</div>
						<h1>
							<form action="linkWord" id="frm${a.num}" method="get">
								<input type="hidden" name="linkWord" value="${a.word}">
								<a href="#"
									onclick="document.getElementById('frm${a.num}').submit();"
									style="text-decoration: none; font-color: black;">${a.word}</a>
							</form>
						</h1>
					</div>

					<div>${a.info}</div>
					<div>글번호: ${a.num}</div>

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
						<a
							href="javascript:aboveComment(${a.num}, ${lastGroupNum.groupnum});"
							style="margin-top: 1rem; text-decoration: none;">댓글+</a>
					</div>
					<table frame=void>
						<tr>
							<!-- 기본댓글달기 -->
							<!-- 기본댓글달기 -->
							<!-- 기본댓글달기 -->
							<!-- 기본댓글달기 -->
							<!-- 기본댓글달기 -->
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
						var arrowhtml = "<textarea id='"+textA+"' name='subcon' rows='2' cols='52' style='position: relative; left: 1rem; margin-left: 2rem; margin-bottom: 1.7rem; width: 29rem; height: 55px; table-layout: fixed;'></textarea>";
						var innerhtml = "<div style='position: relative; left: 1rem; font-size: 80%; float: right;'><div><input type='submit' style='height:1.7rem;' onclick=\"writeSub('"+textA+"','"+num +"', '0', '"+groupnum+"','<%=sessionPw%>', '<%=sessionId%>');\" value='댓글달기'/></div>";
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
													${b.groupnum}${b.content}
													<!-- 댓글버튼 -->
												</div>
											</c:when>

											<c:otherwise>
												<div>&nbsp; &nbsp;</div>
												<div style="position: relative; left: 13px; bottom: 10px;">${b.id}</div>
												<div id="subView" style="padding-left: 1rem; width: 36rem;">
													${b.groupnum}${b.content}
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
									<!-- 답글 -->
									<!-- 답글 -->
									<!-- 답글 -->
									<!-- 답글 -->
									<!-- 답글 -->
									<div id="commentClickButton${b.num}"
										class="belowCommentSection">

										<!-- <div class="textSection"
											style="position: relative; top: 0.8rem; left: 1.4rem;">
											댓글표시 화살표
											<div id="diagram"/>
											<div class="cont-box-pseudo"/>
										</div> -->
										<!-- 답글입력창 -->
										<!-- <div class="textSection"> -->
										<%-- <textarea id="textAreaSecond${b.num}" name="subcon" rows="2" cols="55" style="position: relative; left: 1rem; margin-left: 1.2rem; margin-bottom: 1.7rem; height: 55px; table-layout: fixed;"></textarea>
											
												<input type="submit"
											onclick="writeSecondSub('${b.num}','${a.num}','${b.space+1}','${b.id}','${b.groupnum}','<%=sessionPw%>', '<%=sessionId%>');"
											value="댓글달기" style="position: relative; left: 1rem; font-size: 80%; float: right;" />
										</div> --%>

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
										var arrowhtml = "<div class='textSection' style='margin-left:0.5rem; position: relative; top: 0.8rem; left: 1.4rem;'><div id='diagram'/><div class='cont-box-pseudo'/></div>";
										var innerhtml = "<span style=''><textarea id='"+textA+"' name='subcon' rows='2' cols='55' style='margin-left: 2.2rem; margin-bottom: 1rem; margin-top: 0.7rem; height: 55px; table-layout: fixed; float: left;'></textarea></span>"; 
										var writebutton = "<span style='position: relative; bottom:0.8rem; margin-right:0.5rem; font-size: 80%; float: left;'><div><input type='submit' style='height:1.7rem;' onclick=\"writeSecondSub('"+num+"','"+connum+"','"+spacenum+"','"+id+"','"+groupnum+"','<%=sessionPw%>', '<%=sessionId%>');\" value='답글달기'/></div>";
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

	</div>
</div>
<footer>
	<a href="https://opentutorials.org/course/1">홈페이지</a>
</footer>
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
