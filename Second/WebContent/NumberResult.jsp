<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
입력하신 숫자는 <%=request.getParameter("number") %><br>
1부터 <%=request.getParameter("number") %>까지의 3또는 5의 배수들은<br>
<%=request.getAttribute("list") %>
</body>
</html>