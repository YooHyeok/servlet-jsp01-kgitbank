<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "lab.web.vo.EmpVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<h2>사원 목록</h2>
<table>
<c:forEach items="${list}" var="emp">

<tr>
<td><a href="/JDBC/JDBC.do?action=view&empId=${emp.employeeId}">${emp.employeeId}</a></td>
<td>${emp.firstName}</td>
<td>${emp.salary}</td>
<td>${emp.phoneNumber}</td>
<td>${emp.email}</td>
</tr>
</c:forEach>
</table>
</body>
</html>