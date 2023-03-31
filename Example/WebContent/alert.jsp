<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류페이지</title>
</head>
<body>
<script type="text/javascript">
alert("<%=request.getAttribute("message") %>");
history.back();
</script>
</body>
</html>