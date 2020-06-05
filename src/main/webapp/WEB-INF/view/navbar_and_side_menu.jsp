<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
			<li><a href="${pageContext.request.contextPath}/home">首頁</a></li>
			<li><a href="${pageContext.request.contextPath}/keyword">關鍵字</a></li>
			<li><a href="${pageContext.request.contextPath}/linebot">機器人</a></li>	
			<li><a href="${pageContext.request.contextPath}/skill">技能</a></li>												
			<li><a href="${pageContext.request.contextPath}/member">Member</a></li>
			<%-- <li><a href="${pageContext.request.contextPath}/message">Message</a></li> --%>
			
			<!-- <li><a href="#">Services</a></li> -->
		    
			<li><a class="user-info" href="${pageContext.request.contextPath}/logout">Logout</a></li>
			<li class="user-info">
			 
					<c:if test="${pageContext.request.userPrincipal.name != null}">
					  		 Hi ${pageContext.request.userPrincipal.name}
					</c:if>
			</li>

		</ul>
	</nav>
	<div id="side-menu" class="side-nav">
		<a href="#" class="btn-close" onclick="closeSideMenu()">&times;</a>
		<a href="${pageContext.request.contextPath}/home">首頁</a>
		<a href="${pageContext.request.contextPath}/keyword">關鍵字</a>
		<a href="${pageContext.request.contextPath}/linebot">機器人</a>
		<a href="${pageContext.request.contextPath}/skill">技能</a>
		<a href="${pageContext.request.contextPath}/member">Member</a>
		<%-- <a href="${pageContext.request.contextPath}/message">Message</a> --%>		
		<!-- <a href="#">Services</a> -->
		
		
	</div>