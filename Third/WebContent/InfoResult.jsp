<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<a href="/Quiz/Input.jsp">입력페이지</a><br>
<%ArrayList<String> list = (ArrayList<String>)request.getAttribute("list"); %>
<%for(String team : list) {%>
<%=team %>
<%} %>
</body>
</html>