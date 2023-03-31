<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h2>로그인</h2>
<%=session.getId() %>
<form action="/Ex/Login.do" method=post>
아이디 :<input type=text name=id> <br>
비밀번호 : <input type=password name=pw> <br>
<input type=hidden value=login name=action>
<input type=submit value=로그인>
</form>
아이디가 없으실 경우 <a href="/Ex/memberform.jsp">회원가입</a>을 눌러주세요
</body>
</html>