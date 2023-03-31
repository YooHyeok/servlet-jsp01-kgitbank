<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "lab.web.vo.DmVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록조회</title>
</head>
<body>
<h2>부서 목록</h2>
<%ArrayList<DmVO> list = (ArrayList<DmVO>)request.getAttribute("list"); %>
<table>
<%for(DmVO dm : list){ %>
<tr>
<td><a href="/hw_departments/DM.do?action=view&dmId=<%=dm.getDepartmentId()%>"><%=dm.getDepartmentId() %></a></td>
<td><%=dm.getDepartmentName() %></td>
<td><%=dm.getManagerId() %></td>
<td><%=dm.getLocationId() %></td>
</tr>
<%} %>
</table>

</body>
</html>