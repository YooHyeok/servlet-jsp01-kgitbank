<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form action="/JDBC/Member.do" method=post>
<input type=hidden name=action value="${action}">>
<fieldset>
<legend>회원 정보 입력</legend>
<table>
<tr>
<td>아이디</td><td><input type=text name=userid></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=text name=password></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=name></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email></td>
</tr>
<tr>
<td>주소</td><td><input type=text name=address></td>
</tr>
<tr><td>
<input type=submit value=입력><input type=reset value=취소>
</td></tr>

</table>
</fieldset>

</form>
</body>
</html>