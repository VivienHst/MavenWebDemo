<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
	
	</head>
	<body>
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include><!-- 第二種 -->
	<div id="main">
		<a href="${pageContext.request.contextPath}/animal/add">新增</a>
	
	
		<table >
			<tr>
				<th>Name</th>
				<th>Species</th>
				<th>Personality</th>
			</tr>
		
			<c:forEach var="animal" items="${animalVOs}" begin="1" end="10">
				<tr>
					<td>${animal.name}</td>
					<td>${animal.species}</td>
					<td>${animal.personality}</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<c:if test="${!isFirstPage}">
			<a href="${pageContext.request.contextPath}/animal/all?page=${currentPage-1}">上一頁</a>
		</c:if>
		
		<c:if test="${!isLastPage}">
			<a href="${pageContext.request.contextPath}/animal/all?page=${currentPage+1}">下一頁</a>
		</c:if>
	</div>
	
</body>
</html>