<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 조회</title>
</head>
<body>
<h3>조회하려는 사원 번호를 입력하세요.</h3>
<form action="/JDBC/JDBC.do">
사원번호 : <input type=text name=empno>&nbsp;
<input type=hidden name=action value="search">
<input type=submit value=검색>
</form>
<br>
${emp}
</body>
</html>