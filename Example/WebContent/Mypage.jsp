<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<%=session.getId() %>

<%if(session.getAttribute("userid")!=null) { %>
	
<%=session.getAttribute("userid") %>님, 환영합니다.<Br>

<%Cookie[] cookies = request.getCookies(); %>
<%for(Cookie c : cookies){ %>
쿠키이름:<%=c.getName() %>, 쿠기 값: <%=c.getValue() %>, 쿠키 도메인: <%=c.getDomain() %> <br>
<% } %>
<a href="/Ex/Login.do"><input type=button value=로그아웃></a>
<% } else{ response.sendRedirect("/Ex/Login.jsp"); } %>
</body>
</html>