<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*, java.util.*" %>
<html>
<head>

<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<title>Login</title>
<!-- 네이버아디디로로그인 초기화 Script -->
	
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
			</tr>
		</thead>
		<tbody>
			<form action="logining" method="get">
				<tr>
					<td><input type="text" name="ID" size="5"></td>

					<td><input type="text" name="PW" size="5"></td>

					<td><input type="submit" value="로그인"></td>
				</tr>
			</form>

		</tbody>
	</table>

	<div id="naveeedLogin">eee</div>

	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "uvuGSXSNJnTZI3FdmGwO",
			callbackUrl: "http://192.168.210.69:8090/controller/home",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>
<!-- // 네이버아이디로로그인 초기화 Script -->
</body>
</html>
