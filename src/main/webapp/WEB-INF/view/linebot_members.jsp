<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<table border="1" >		
				<tr>
   					<th>LinePicture</th>
					<th>LineId</th>
					<th>LineName</th>
					<th>MemberStatus</th>
					<th>CreateDate</th>
					<th>SendMessage</th>
					
				</tr>
				
				<c:forEach var="linemember" items="${linemembers}">
		
					<tr>
						<td>
		                	<img src="${linemember.linePicture}" width=40 height=40/>
						</td>
						<td>${linemember.lineId}</td>
						<td>${linemember.lineName}</td>
						
						<td>${linemember.memberStatus}</td>
						<td>							
							<fmt:formatDate value="${linemember.createDate}" pattern="yyyy-MM-dd HH:mm" />							
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/message?lineId=${linemember.lineId}">message</a>	
						</td>
							
					</tr>
				</c:forEach>
			</table>			
			<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>
		
	</body>
</html>