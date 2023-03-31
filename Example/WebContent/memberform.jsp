<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
</head>
<body>
<fieldset>
<legend>회원가입</legend>
<form action="/Ex/Login.do" method=post>
<table>
<tr>
<td>아이디</td> <td><input type=text name=id> </td>
</tr>
<tr>
<td>비밀번호</td> <td><input type=password name=pw> </td>
</tr>
</table>
<input type=hidden value=join name=action>
<input type=submit value=가입>&nbsp;&nbsp;
<input type=reset value=취소>
</form>

</fieldset>
</body>
</html>