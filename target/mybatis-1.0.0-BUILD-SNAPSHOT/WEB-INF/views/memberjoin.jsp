<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>memberjoin</title>

    <link href="resources/css/style.css?after" rel="stylesheet"
          type="text/css">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"
          href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.css">
    <!-- Bootstrap core JavaScript -->
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script type="text/javascript">

        function check() {
            var docId = document.memberJoin;
            if (docId.id.value === '') {
                id.focus();
                return false;
            }
            if (docId.pw.value === '') {
                pw.focus();
                return false;
            }
            if (docId.name.value === '') {
                name.focus();
                return false;
            }
            if (docId.email.value === '') {
                email.focus();
                return false;
            } else return true;
        }

        function idValidationCheck() {
            var inputText = document.getElementById('id');
            var patternKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; //한글

            if (patternKor.test(inputText.value) || inputText.value.search(/\s/) !== -1 || inputText.value.length >= 20) {
                alert('ID는 영문, 숫자, 특수문자만 가능합니다.');
                id.focus();
                inputText.value = '';
            } else {
                callIdValidCheckApi(inputText.value);
            }
        }

        function callIdValidCheckApi(inputText) {
            $.ajax({
                type: "POST",
                url: "idValidCheck",
                dataType: "text",
                data: {
                    id: inputText
                },
                error: function () {
                    alert("error");
                },
                success: function (success) {
                    if (success === 'duplicateId') {
                        alert('이미 존재하는 ID입니다.');
                    }
                }
            });
        }

        function pwValidationCheck() {
            var inputText = document.getElementById('pw');
            var patternNum = /[0-9]/; // 숫자
            var patternEngLower = /[a-z]/; // 소문자
            var patternEngUpper = /[A-Z]/; // 대문자
            if (!(patternNum.test(inputText.value) || patternEngLower.test(inputText.value) || patternEngUpper.test(inputText.value) || inputText.value === '')) {
                alert('PW는 영문과 숫자만 가능합니다.');
                id.focus();
                inputText.value = '';
            }
        }

        function nameValidationCheck() {
            var inputText = document.getElementById('name');
            var patternEngLower = /[a-z]/; // 소문자
            var patternEngUpper = /[A-Z]/; // 대문자
            var patternKor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; //한글
            if (!(patternKor.test(inputText.value) || patternEngLower.test(inputText.value) || patternEngUpper.test(inputText.value) || inputText.value === '')) {
                alert('이름은 영문과 한글만 가능합니다.');
                name.focus();
                inputText.value = '';
            }
        }

        function emailValidationCheck() {
            var inputText = document.getElementById('email');
            var patternEngLower = /[a-z]/; // 소문자
            var patternEngUpper = /[A-Z]/; // 대문자는
            var patternAt = /[@]/; // @
            if (!(patternAt.test(inputText.value) || patternEngLower.test(inputText.value) || patternEngUpper.test(inputText.value) || inputText.value === '')) {
                alert('이메일은 영문만 가능합니다.');
                email.focus();
                inputText.value = '';
            }
            // TODO 이메일 중복검사
        }
    </script>

    <link href="resources/css/defineStyle.css?after" rel="stylesheet" type="text/css">
</head>
<body id="memberJoin">
<div id="signUp" style="color-background: grey;">
    <div id="notMember">${notMember}</div>

    <!--아이디영문요청 태그-->
    <div id="engg"></div>
    <div style="text-align:center;"><h1>Sign up</h1></div>

    <form action="memberjoining" name="memberJoin" onsubmit="return check()" method="get">
        <div style="margin-bottom:0.8rem;">
            <input type="text" onfocusout="idValidationCheck()" id="id" class="form-control" name="ID" size="30"
                   placeholder="ID" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" onfocusout="pwValidationCheck()" id="pw" class="form-control" name="PW" size="30"
                   placeholder="PASSWORD" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" onfocusout="nameValidationCheck()" id="name" class="form-control" name="NAME" size="30"
                   placeholder="NAME" style="width: 20rem;">
        </div>

        <div style="margin-bottom:0.8rem;">
            <input type="text" onfocusout="emailValidationCheck()" id="email" class="form-control" name="EMAIL"
                   size="30"
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