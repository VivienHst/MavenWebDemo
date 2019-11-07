<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">


</head>
<body>
			
	<nav class="navbar">
		<span class="open-side">
			<a href="#" onclick="switchSideMenu()">
			  	<svg width="30" height="30">
					<path d="M0,5 30,5" stroke="#fff" stroke-width="5" />
					<path d="M0,14 30,14" stroke="#fff" stroke-width="5" />
					<path d="M0,23 30,23" stroke="#fff" stroke-width="5" />
			  	</svg>
			</a>
		</span>
	
		<ul class="navbar-nav">
			<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/keyword">Keyword</a></li>
			<li><a href="#">Member</a></li>
			<li><a href="#">Services</a></li>

		</ul>
	</nav>
	<div id="side-menu" class="side-nav">
		<a href="#" class="btn-close" onclick="closeSideMenu()">&times;</a>
		<a href="${pageContext.request.contextPath}/home">Home</a>
		<a href="${pageContext.request.contextPath}/keyword">Keyword</a>
		<a href="#">Member</a>
		<a href="#">Services</a>
	</div>

	<div id="main">
		<h1>Responsive Side Menu</h1>
		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
		tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
		quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
		consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
		cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
		proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

		m2/repository/org/springframework/spring-webmvc/5.2.0.RELEASE/spring-webmvc-5.2.0.RELEASE.jar' cannot be read or is not a valid ZIP file	coolbot		Build path	Build Path Problem

	</div>
<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
 --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>

</body>
</html>