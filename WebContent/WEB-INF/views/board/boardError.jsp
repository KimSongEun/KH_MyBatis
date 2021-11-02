<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류페이지</title>
</head>
<body>
<h1>오류페이지</h1>
${message }

<a href="javascript:history">이전페이지</a>
<% 
	// request.setAttribute("message", "게시글 페이지별 조회 실패");
%>
</body>
</html>