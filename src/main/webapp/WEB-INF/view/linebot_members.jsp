<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
				<!-- LineId varchar(50) not null,
			     LineName nvarchar(50) null,
			     LinePicture nvarchar(200)  null,
			     BotId int not null,
			     MemberStatus [int] not null,
			     CreateDate datetime not null,
			     UpdateDate datetime null, -->
					<th>LineId</th>
					<th>LineName</th>
					<th>LinePicture</th>
					<th>MemberStatus</th>
					<th>CreateDate</th>
					
				</tr>
				
				<c:forEach var="linemember" items="${linemembers}">
		
					<tr>
						<td>${linemember.lineId}</td>
						<td>${linemember.lineName}</td>
						<td>${linemember.linePicture}</td>
						<td>${linemember.memberStatus}</td>
						<td>${linemember.createDate}</td>
						
							
					</tr>
				</c:forEach>
			</table>			
			<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>
		
	</body>
</html>