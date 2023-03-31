<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/default.css" media="screen">

<title>${message}</title>
</head>
<body>
<table class="layout">
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<!-- 본문 표시 코드 -->
<tr height="500" valign="top"><td>
<h1>${message}</h1>
<h3 align=center> 글내용을 작성해 주세요.</h3>
<form action='<c:url value="/Board.do"/>' method="post">
<table>
<tr>
<td>제목</td>
<td><input type="text" name="subject" size="20" value="${board.subject}"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="password" value="${board.password}"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea cols="30" rows="5" name="content">${board.content}</textarea></td>
</tr>
<tr>
<td colspan="2"><input type="hidden" name="action" value="${action}">
<input type="hidden" name="bbsno" value="${board.bbsno}">
<input type="hidden" name="masterid" value="${board.masterId}">
<input type="hidden" name="replynumber" value="${board.replyNumber}">
<input type="hidden" name="replystep" value="${board.replyStep}">
<input type="hidden" name="userid" value="${board.userId}">
<input type="submit" value="저 장"><input type="reset" value="삭 제"></td>
</tr>
</table>
</form>
</td>
</tr>
<!-- 본문 표시 코드 -->
<tr heighr="50"><td>
<jsp:include page="/incl/footer.jsp"/>
</td></tr>
</table>
</body>
</html>