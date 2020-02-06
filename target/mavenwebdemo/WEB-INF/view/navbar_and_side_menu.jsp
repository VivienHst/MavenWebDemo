<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->
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
			<li><a href="${pageContext.request.contextPath}/linebot">Bot</a></li>	
			<li><a href="${pageContext.request.contextPath}/skill">Skill</a></li>												
			<li><a href="${pageContext.request.contextPath}/member">Member</a></li>
			<li><a href="${pageContext.request.contextPath}/message">Message</a></li>
			
			<li><a href="#">Services</a></li>

		</ul>
	</nav>
	<div id="side-menu" class="side-nav">
		<a href="#" class="btn-close" onclick="closeSideMenu()">&times;</a>
		<a href="${pageContext.request.contextPath}/home">Home</a>
		<a href="${pageContext.request.contextPath}/keyword">Keyword</a>
		<a href="${pageContext.request.contextPath}/linebot">Bot</a>
		<a href="${pageContext.request.contextPath}/skill">Skill</a>
		<a href="${pageContext.request.contextPath}/member">Member</a>
		<a href="${pageContext.request.contextPath}/message">Message</a>		
		<a href="#">Services</a>
		
	</div>