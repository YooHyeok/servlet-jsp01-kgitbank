<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int number = Integer.parseInt(request.getParameter("number"));%>
입력하신 숫자는 <%=number %><br>
1부터 <%=number %>까지의 3또는 5의 배수들은 <br>
<%for (int i=0; i<number; i++){ %>
<%if((i+1)%3==0||(i+1)%5==0){ %>
<%out.print(i+1);%>
<%} %>
<%} %>

</body>
</html>