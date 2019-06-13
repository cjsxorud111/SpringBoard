function recommendUp(upNumber, conNum) {
	$("#te").text("test");

	if (isSession == false) {
		alert("먼저로그인을 해주세요" + isSession + session);
	} else {
		$.ajax({
			type : "POST", // 전송방식을 지정한다 (POST,GET)
			url : "recommendUp",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
			dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을
								// 사용할 수 있다.
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
					var id = 'recommendUp' + conNum;
					var num = upNumber + 1;
					document.getElementById(id).innerHTML = "추천: " + num;
				}
			}
		});
	}
}

function recommendDown(downNumber, conNum) {
	if (isSession == false) {
		alert("먼저로그인을 해주세요");
	} else {
		$.ajax({
			type : "POST", // 전송방식을 지정한다 (POST,GET)
			url : "recommendDown",// 호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서
									// 사용해도된다.
			dataType : "text",// 호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을
								// 사용할 수 있다.
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
					var id = 'recommendDown' + conNum;
					var num = downNumber + 1;
					document.getElementById(id).innerHTML = "추천: " + num;
				}
			}

		});
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
				location.reload();
			}
		} else {
			alert("작성하신글만 삭제할수있습니다.")
		}
	}
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

var num = 0;
var endNum = 0;

function wordClick(word) {
	$("#inputText").val(word);
}

function loseFocus() {
	setTimeout(function() {
		textOut();
	}, 150);
}
// 포커스잃었을때추천창끔
function textOut() {
	var searchRecommendSection = document
			.getElementById("searchRecommendSection");
	searchRecommendSection.innerHTML = "";
}

function defineContentDelete(conNum, textStatus) {
	alert("hello");
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
			alert("error");
		},
//		설정하지 않았는데 왜define으로 가는지 이해못함 이해하고 수정필요
		success : function(Parse_data) {
			alert(Parse_data);
//			alert("삭제되었습니다.");
//			location.reload();
//			
//			if(textStatus == "main(*)"){
//				window.location.href='define';
//			}else{
//				window.location.href='linkWord?linkWord='+textStatus;
//			}
		}
	});
}
