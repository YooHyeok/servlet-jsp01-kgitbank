<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="lab.web.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 ${message}</title>
</head>
<body>

<h2>사원 정보 ${message}</h2>
<form action="" method=post>
<input type=hidden name=action value="${action}">
<table>

<tr>
<td>사원번호</td><td><input type=text name=empId value="${emp.employeeId}" ${empty emp ?"": readonly}></td>
</tr>
<tr>
<td>이름,성</td><td><input type=text name=firstName value="${emp.firstName}">
<input type=text name=lastName value="${emp.lastName}"></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email value="${emp.email}"></td>
</tr>
<tr>
<td>연락처</td><td><input type=text name=phoneNumber value="${emp.phoneNumber}"></td>
</tr>
<tr>
<td>입사일</td><td><input type=date name=hireDate value="${emp.hireDate}"></td>
</tr>


<tr>
<td>직무</td><td>
<select name=jobId>
<c:forEach items="${jobList}" var="job">
<option value="${job.jobId}" ${job.jobId eq emp.jobId ? "selected" : ""}>${job.jobTitle}
</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>급여</td><td><input type=text name=salary value="${emp.salary}"></td>
</tr>
<tr>
<td>보너스율></td><td><input type=number min=0 max=1 step=0.01 name=commissionPct value="${emp.commissionPct}"></td>
</tr>

<tr>
<td>매니저</td><td>
<select name=managerId>
<c:forEach items="${manList}" var="man">
<option value="${man.employeeId}" ${man.employeeId eq emp.employeeId ? "selected" : "" }> ${man.firstName}
</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>부서</td><td>
<select name=departmentId>
<c:forEach items="${deptList}" var="dept" >
<option value="${dept.departmentId}" ${dept.departmentId eq emp.departmentId ? "selected" : ""}> 
${dept.departmentName}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>
<input type=submit value="${message}">&nbsp;&nbsp;<input type=reset value=취소>
</td>
</tr>
</table>
</form>
</body>
</html>