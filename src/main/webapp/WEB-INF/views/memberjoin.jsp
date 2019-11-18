<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>memberjoin</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <link href="resources/css/style.css?after" rel="stylesheet"
          type="text/css">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"
          href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    <!-- Bootstrap core JavaScript -->
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">

        function getParameterByName(name, url) {
            if (!url)
                url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
                .exec(url);
            return results[2];
        }

        window.onload = function () {
            var a = getParameterByName('han');
            if (a === 'true') {
                $("#engg").html("아이디와 이름은 영문으로 입력해주세요");
            }
        };

        function idValidationCheck(str) {
            var pattern_num = /[0-9]/; // 숫자
            var pattern_eng_upper = /[A-Z]/; // 문자
            var pattern_eng_lower = /[a-z]/; // 소문자
            var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
            var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글
            return (pattern_num.test(str)) && (pattern_eng_lower.test(str)) && !(pattern_eng_upper.test(str)) && !(pattern_spc.test(str)) && !(pattern_kor.test(str));
        }

        function check() {
            var docId = document.memberJoin;
            if (docId.id.value === "") {
                id.focus();
                return false;
            }
            if (docId.pw.value === "") {
                pw.focus();
                return false;
            }
            if (docId.name.value === "") {
                name.focus();
                return false;
            }
            if (docId.email.value === "") {
                email.focus();
                return false;
            } else return true;
        }

        function idValidCheck() {
            var inputText = document.getElementById("id");
            var result = idValidationCheck(inputText);
            if (result === false) {
                alert("ID는 영문 소문와 숫자만 가능합니다.");
                id.focus();
                inputText.value = "";
            }
        };
    </script>

    <link href="resources/css/defineStyle.css?after" rel="stylesheet" type="text/css">
</head>
<body id="memberJoin">
<div id="signUp" style="color-background: grey;">
    <div id="notMember">${notMember}</div>

    <!-- 아이디영문요청 태그-->
    <div id="engg"></div>
    <div style="text-align:center;"><h1>Sign up</h1></div>

    <form action="memberjoining" name="memberJoin" onsubmit="return check()" method="get">
        <div style="margin-bottom:0.8rem;">
            <input type="text" onfocusout="idValidCheck()" id="id" class="form-control" name="ID" size="30"
                   placeholder="ID" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" id="pw" class="form-control" name="PW" size="30"
                   placeholder="PASSWORD" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" id="name" class="form-control" name="NAME" size="30"
                   placeholder="NAME" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" id="email" class="form-control" name="EMAIL" size="30"
                   placeholder="E-MAIL" style="width: 20rem;">
        </div>

        <div id="signUpButton" style="margin-bottom:0.8rem;">
            <input type="submit" style="width:20rem;" class="btn btn-primary" value="회원가입">
        </div>

        <div id="deleteButton">
            <a href="http://www.helloword.kr/" style="width:20rem;" class="btn btn-primary">취소</a>
        </div>
    </form>
</div>
</body>
</html>