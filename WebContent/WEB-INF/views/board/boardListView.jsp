<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis Board</title>
</head>
<body>
	<%
	//request.setAttribute("list", list);
	//request.setAttribute("currentPage", currentPage);
	//request.setAttribute("maxPage", maxPage);
	//request.setAttribute("startPage", startPage);
	//request.setAttribute("endPage", endPage);
	//request.setAttribute("listCount", listCount);
	%>

	<h1>게시판</h1>
	
	<p>페이지</p>
	<!-- for(int i = startPage; i<=endPage; i++) -->
	<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
		<c:if test="${currentPage == i}">
			<strong>
		</c:if>
		<a href="boardlist?page=${i}">${i }</a> 
		<c:if test="${currentPage == i}">
			</strong>
		</c:if>
	</c:forEach>
	
	
	<table>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일자</td>
		</tr>
		<c:forEach items="${list }" var="vo">
		<tr>
			<td>${vo.boardNum }</td>
			<td>${vo.boardTitle }</td>
			<td>${vo.boardWriter }</td>
			<td>${vo.boardDate }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>