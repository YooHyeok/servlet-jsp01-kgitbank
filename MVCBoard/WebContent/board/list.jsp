<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*" %>
<%@ page import = "lab.web.model.*" %>
<%@ taglib prefix="Elceil" uri="/WEB-INF/tlds/el-function.tld" %>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="./css/default.css" media="screen">
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<table class="layout">
<tr height=50><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<tr height=300 valign="top"><td>
<h3>게시판 목록입니다.</h3>
<table>
<c:forEach var="board" items="${list}">
<tr>
<td>${board.name}</td>
<td><a href='<c:url value="/Board.do?action=view&bbsno=${board.bbsno}"/>'>${board.subject}</a></td>
<td>${board.writeDate}</td>
<td>${board.readCount}</td>
</tr>
</c:forEach>
<tr>
<td colspan=4>
<h6 align="center">
	<c:set var="totalPageBlock" value="${Elceil:ElCeil(totalPageCount/10.0)}" />
	<c:set var="nowPageBlock" value="${Elceil:ElCeil(page/10.0)}" />
	<c:set var="startPage" value="${(nowPageBlock)*10+1}"/>
<c:choose>
	<c:when test="${totalPageCount gt nowPageBlock*10}">
	<c:set var="endPage" value="${(nowPageBlock)*10}"/>
	</c:when>
	<c:otherwise>
	<c:set var="endPage" value="${totalPageCount}"/>
	</c:otherwise>
</c:choose>
	<c:if test="${nowPageBlock gt 1}">
		<a href='<c:url value="/Board.do?page=${startPage-1}&action=list"/>'>◀</a>
	</c:if>
	<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="status">
	[<a href='<c:url value="/Board.do?page=${status.count}"/>'>${status.count}</a> }]
	</c:forEach>
	<c:if test="${nowPageBlock lt totalPageBlock}">
		<a href='<c:url value="/Board.do?page=${endPage+1}&action=list"/>'>▶</a>
		</c:if>	
</h6>
</td>
</tr>
</table>
<tr height = "50">
<td><jsp:include page="/incl/footer.jsp"></jsp:include></td>
</tr>
</table>
</body>
</html>