<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 운세</title>
<% int a = (int)(Math.random()*10+1);
if(a>5){ %>
오늘은 운수 좋은 날
<%}else{ %>
오늘은 가만히 계세요.
<%} %>
</head>
<body>

</body>
</html>