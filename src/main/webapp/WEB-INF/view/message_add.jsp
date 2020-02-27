<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>New Message</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
</head>
<body>
			
	<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>	
	<div id="main">
	
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/newTextMessage?lineId=${lineId}">Text Message</a>	
			</li>
			
			<li>
				<a href="${pageContext.request.contextPath}/newImageMessage?lineId=${lineId}">Image Message</a>	
			</li>
			
			<li>
				<a href="${pageContext.request.contextPath}/newTemplateMessage?lineId=${lineId}">Template Message</a>	
			</li>
			
			<li>
				<a href="${pageContext.request.contextPath}/newPlaceMessage?lineId=${lineId}">Place Message</a>	
			</li>
			
		</ul>
		
		<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>

</body>
</html>