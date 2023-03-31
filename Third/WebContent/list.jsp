<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "lab.web.model.EmpVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<h2>사원 목록</h2>
<%ArrayList<EmpVO> list = (ArrayList<EmpVO>)request.getAttribute("list"); %>
<table>
<%for(EmpVO emp : list){ %>
<tr>
<td><a href="/JDBC/JDBC.do?action=view&empId=<%=emp.getEmployeeId()%>"
><%=emp.getEmployeeId() %></a></td>
<td><%=emp.getFirstName() %></td>
<td><%=emp.getLastName() %></td>
<td><%=emp.getSalary() %></td>
<td><%=emp.getPhoneNumber() %></td>
<td><%=emp.getEmail() %></td>
<td><%=emp.getDepartmentId() %></td>
</tr>
<%} %>
</table>
</body>
</html>