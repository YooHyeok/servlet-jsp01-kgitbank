<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력</title>
</head>
<body>
<form action="/2/Info.do" method=post>
이름 : <input type=text name=name> <br>
연락처 : <input type=text name=tel> <br>
<input type=submit value="전송">
</form>
<br><br>
<a href="/2/Info.do"><input type=button value=목록조회></a>
</body>
</html>