<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "lab.web.model.DeptVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<h2>부서 목록</h2>
<%ArrayList<DeptVO> list = (ArrayList<DeptVO>)request.getAttribute("list"); %>
<table>
<%for(DeptVO dept : list){ %>
<tr>
<td><a href="/Quiz/Dept.do?action=deptEmp&deptId=<%=dept.getDeptId()%>"><%=dept.getDeptId() %></a></td>

<td><%=dept.getDeptName() %></td>
<td><%=dept.getManagerId() %></td>
<td><%=dept.getLocationId() %></td>
</tr>
<%} %>
</table>
</body>
</html>