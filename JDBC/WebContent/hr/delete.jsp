<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 페이지</title>
</head>
<body>
<h2>회원 정보 삭제</h2>
<form action="JDBC.do" method=post>
이메일 : <input type=text name=email><br>
<input type=hidden name=empId value="${empId}">
<input type=hidden name=action value=delete>
<input type=submit value=삭제>
</form>

</body>
</html>