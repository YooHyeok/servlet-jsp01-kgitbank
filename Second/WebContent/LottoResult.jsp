<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>행운의 숫자!</title>
</head>
<body>
<%ArrayList<Integer> list = (ArrayList<Integer>)request.getAttribute("list"); %>
오늘의 행운 숫자는 :<%for(int i=0; i<6; i++){ %> <%=list.get(i) %><%} %>
</body>
</html>