<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="./css/default.css" media="screen">
<meta charset="UTF-8">
<title>첫 화면</title>
</head>
<body>
<table class="layout">
<tr height=50><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<tr height=300 valign="top"><td>
<c:choose>
<c:when test="${empty userid}">
<h1>로그인 화면</h1>
	${message}<br>
	<h3>아이디와 비밀번호를 입력하세요.</h3>
	<form action='<c:url value="/Login.do"/>' method=post>
	<input type=text name=userid>
	<input type=password name=password> <br>
	<input type=submit value=로그인>
	</form>
</c:when>
<c:otherwise>
<c:redirect url="/Board.do?action=list"/>
</c:otherwise>
</c:choose>
</td></tr>
<tr height = 50><td>
<jsp:include page="/incl/footer.jsp"></jsp:include>
</td></tr>
</table>
</body>
</html>