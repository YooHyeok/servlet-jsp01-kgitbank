<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="./css/default.css" media="screen">
<title>글 상세 내용 화면</title>
</head>
<body>
<table class="layout">
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/></td>
</tr>
<tr height="500" valign="top">
<td>
<h1>글 삭제 화면</h1>
<h3>글 삭제 비밀번호 입력</h3>
<form action='<c:url value="/Board.do"/>' method="post">

<input type="hidden" name="action" value="${action}">
<input type="hidden" name="bbsno" value="${bbsno}">
<input type="hidden" name="replynumber" value="${replynumber}">
<input type="password" name="password">
<input type="submit" value="삭 제">
</form>
</td>
</tr>
<tr height="50">
<td><jsp:include page="/incl/footer.jsp"/>
</td></tr>
</table>
</body>
</html>