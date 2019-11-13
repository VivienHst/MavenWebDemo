<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Member</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>


</head>
<body>
			
	<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>

	<div id="main">
		
		<table border="1" >		
			<tr>
				<th>Uid</th>
				<th>Account</th>
				<th>CreateDate</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			
			<c:forEach var="member" items="${members}">
				<c:url var="dateilLink" value="/memberDetail">
					<c:param name="uid" value="${member.uid}" />
				</c:url>
				
				<c:url var="deleteLink" value="/memberDelete">
					<c:param name="uid" value="${member.uid}" />
				</c:url>
	
				<tr>
					<td>${member.uid}</td>
					<td>${member.account}</td>
					<td>${member.createDate}</td>
					<td><a href="${dateilLink}">修改</a></td>				
					<td><a href="${deleteLink}">刪除</a></td>	
						
				</tr>
			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>

</body>
</html>