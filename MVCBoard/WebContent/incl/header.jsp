<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
<table>
<c:choose>
	<c:when test="${!empty userid}">
	<tr>
	<td colspan=5>${name}님 환영합니다.</td>
	</tr>
	<tr>
	<td><a href='<c:url value="/"/>'>홈으로</a></td>
	<td><a href='<c:url value="/Board.do?action=write"/>'>게시글쓰기</a></td>
	<td><a href='<c:url value="/Board.do?action=list"/>'>게시글 목록</a></td>
	<td><a href='<c:url value="/Login.do?action=logout"/>'>로그아웃</a></td>
	<td><a href='<c:url value="/Board.do?action=member"/>'>마이페이지</a></td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
	<td><a href='<c:url value="/Member.do?action=insert"/>'>회원가입</a></td>
	<td><a href='<c:url value="/login.jsp"/>'>로그인</a></td>
	</tr>
	</c:otherwise>
</c:choose>
</table>
</header>