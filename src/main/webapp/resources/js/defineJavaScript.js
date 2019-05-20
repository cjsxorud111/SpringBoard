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

function deleteCheck(conNum, id) {
	if (isSession == false) {
		alert("먼저로그인을 해주세요");
	} else {
		/* 세션id와 글id대조하여 일치시삭제 */
		if (session == id) {
			var really = confirm("삭제하시겠습니까?");
			if (really) {
				defineContentDelete(conNum);
				location.reload();
			}
		} else {
			alert("작성하신글만 삭제할수있습니다.")
		}
	}
}

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