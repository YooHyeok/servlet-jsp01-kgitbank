<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록페이지</title>
</head>
<body>
<%HashMap<String,String> map = (HashMap<String,String>)request.getAttribute("map"); %>

<%Set<String> keySet = map.keySet(); %>

<%for(String key : keySet) { %>
이름 : <%=key %>, 연락처 : <%=map.get(key) %><br>
<%}%>

<a href="/2/InputInfo.jsp">정보 입력</a>
</body>
</html>