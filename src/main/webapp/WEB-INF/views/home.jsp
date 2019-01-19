<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>Home</title>
<script type="text/javascript">
	var aa = null;
	var bb = null;
 	function paging(var a) {
		aa = a * 10 - 10;
		bb = a * 10 - 1;
	}
	
</script>
</head>
<body>
	<%
		String id = (String) session.getAttribute("ID");
		System.out.println(id + "님 환영합니다.");
		Object name = request.getAttribute("Cnum");

		int number = Integer.parseInt(name.toString());
		System.out.println(number + "님 환영합니다.");

		double pagenum = (double) number / 10;
		double aa = pagenum - (int) pagenum;
		int pagenum2;
		if (aa == 0) {
			pagenum2 = (int) pagenum;
		} else {
			pagenum2 = (int) pagenum + 1;
		}

		out.println(pagenum2);
	%>
	<h1><%=id%>님 환영합니다!
	</h1>

	글목록

	<%=number%>
	<br />
	<Cnum>총 글수: </Cnum>
	<c:out value="${Cnum}"></c:out>
	<script>document.write(aa);</script>
	<%
	
	%>
	<table>
		<thead>
			<tr>
				<th>글번호&nbsp&nbsp&nbsp</th>
				<th>글제목&nbsp&nbsp&nbsp</th>
				<th>아이디&nbsp&nbsp&nbsp</th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${HomeCList}" var="a" begin="0" end="<%=pagenum2%>">
				<tr>
					<td>&nbsp&nbsp${a.num}</td>
					<td><a href="content?num=${a.num}">${a.title}</a></td>
					<td>${a.id}</td>
					<td></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>

				<td><a href="write">글쓰기</a></td>
			</tr>
			
			<%-- <% 
			Object HomeCList = request.getAttribute("HomeCList");
			for(int i=0; i< 20; i++ ){
				HomeContentVO vd = (HomeContentVO)HomeClist.getClass(i);
				out.println(HomeCList);
			}
			/* String[] HomeCList1 = new String[19];
			HomeCList1 */
			
			
			
			%>
			 --%>
			
			
		</tbody>
	</table>

	<a href="#" onClick="paging(1)">[처음]</a>
	<%
		for (int i = 0; i < pagenum2; i++) {
	%>
	<a href="#" onClick="paging('<%=i%>')"><%=i%></a>&nbsp
	<%
		}
	%>
	<a href="#" onClick="paging('<%=pagenum2%>')">[끝]</a>
	
	
	
	
	
	<%-- <a href="#" onClick="paging(1)">[처음]</a>
			<c:forEach items="${HomeCList}" var="a">
				<a href="#" onClick="paging('${a.id}')">${a.id}</a>&nbsp
			</c:forEach>
		<a href="#" onClick="paging( )">[끝]</a>  --%>

</body>
</html>
