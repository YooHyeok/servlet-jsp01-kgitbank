<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과 페이지</title>
</head>
<body>
<%String id = request.getParameter("id"); 
String pw = request.getParameter("pw");
if(id.equals("yjk7454")&&pw.equals("0000")){%>
로그인에 성공하셨습니다
아이디 : <%=request.getParameter("id") %>, 비밀번호 : <%=request.getParameter("pw") %>
<a href="/First.jsp">오늘의 운세</a> 
<%}else{ %>
로그인에 실패하셨습니다.
다시 로그인 하시려면 <a href="/Exam.jsp">로그인</a>을 눌러주세요.
<%} %>
</body>
</html>