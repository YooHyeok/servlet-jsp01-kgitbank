<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h2>로그인</h2>
<c:choose>
<c:when test="${empty userid}">
<form action="/JDBC/Member.do" method=post>
${message}<br>
아이디: <input type=text name=userid><br>
비밀번호 : <input type=password name=password><br>
<input type=hidden name=action value=login>
<input type=submit value=로그인>
</form>
아이디가 없으시면 <a href="/JDBC/Member.do?action=insert">회원가입</a>을 눌러주세요
</c:when>
<c:otherwise>
<c:redirect url="/hr/index.jsp">
</c:redirect>
</c:otherwise>
</c:choose>
</body>
</html>