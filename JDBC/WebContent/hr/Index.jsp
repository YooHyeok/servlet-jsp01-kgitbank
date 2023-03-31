<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<h3>메뉴선택</h3><br>
<table>
<tr>
<td>
<a href="/JDBC/JDBC.do?action=list"><Input type=button value=목록조회></a></td>&nbsp;&nbsp;
<td>
<a href="/JDBC/JDBC.do?action=search"><Input type=button value="겁색"></a></td>&nbsp;&nbsp;
<td>
<a href="/JDBC/JDBC.do?action=insert"><Input type=button value="정보입력"></a></td>
</tr>
</table>
<form action="/JDBC/Member.do" method=post>
<input type=hidden name=action value=logout>
<input type=submit value=로그아웃>
</form>
</body>
</html>