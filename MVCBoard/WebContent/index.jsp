<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h6>게시판에 오신걸 환영합니다.</h6>
<br>
</td></tr>
<tr height = 50><td>
<jsp:include page="/incl/footer.jsp"></jsp:include>
</td></tr>
</table>
</body>
</html>