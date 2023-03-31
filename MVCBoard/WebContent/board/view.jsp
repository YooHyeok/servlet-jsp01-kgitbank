<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href="./css/default.css" media="screen">
<title>글 상세 내용 화면</title>
</head>
<body>
<table class="layout">
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<!-- 본문 표시 코드 -->
<tr height="500" valign="top"><td></head>
<h1>상세 내용</h1>
<table>
<tr>
<th>작성자 이름</th>
<td>${board.name}</td>
</tr>
<tr>
<th>제목</th>
<td>${board.subject}</td>
</tr>
<tr>
<th>내용</th>
<td>${board.content}</td>
</tr>
<tr>
<td colspan=2><h3 align=center><a href='<c:url value="/Board.do?action=list"/>'>목록</a>
<a href='<c:url value="/Board.do?action=reply&bbsno=${board.bbsno}"/>'>댓글</a>
<a href='<c:url value="/Board.do?action=update&bbsno=${board.bbsno}&userid=${board.userId}"/>'>수정</a>
<a href='<c:url value="/Board.do?action=delete&bbsno=${board.bbsno}&
replynumber=${board.replyNumber}&userid=${board.userId}"/>'>삭제</a></h3>
</td>
</tr>
</table>
</td></tr>
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
</table>
</body>
</html>