<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
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
			<li><a href="${pageContext.request.contextPath}/hoem">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/keyword">Keyword</a></li>
			<li><a href="${pageContext.request.contextPath}/linebot">Bot</a></li>
			<li><a href="${pageContext.request.contextPath}/skill">Skill</a></li>			
			<li><a href="#">Member</a></li>
			<li><a href="#">Services</a></li>

		</ul>
	</nav>
	<div id="side-menu" class="side-nav">
		<a href="#" class="btn-close" onclick="closeSideMenu()">&times;</a>
		<a href="${pageContext.request.contextPath}/home">Home</a>
		<a href="${pageContext.request.contextPath}/keyword">Keyword</a>
		<a href="${pageContext.request.contextPath}/linebot">Bot</a>
		<a href="${pageContext.request.contextPath}/skill">Skill</a>
		<a href="#">Member</a>
		<a href="#">Services</a>
	</div>

	<div id="main">
		<form:form action="saveLineBot" modelAttribute ="linebot"  method="post">
		<table border="1" >				
			<tr>
				<td>Token : </td>
				<td><form:input path="token" /></td>
				
				<td>Secret : </td>
				<td><form:input path="secret" /></td>
				
				<td>DisplayName : </td>
				<td><form:input path="displayName"/></td>
				
				<td>Type : </td>
				<td><form:input path="type"/></td>			
				<td><input type="submit" value="新增"></td>				
			</tr>
	</table>
		
	</form:form>
	
	<table>
	</table>
	
	<table border="1" >		
		<tr>
			<th>BotId</th>
			<th>Token</th>
			<th>Secret</th>
			<th>DisplayName</th>
			<th>Type</th>
			<th>CreateDate</th>
			<th>UpdateDate</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		
		<c:forEach var="lineBotItem" items="${lineBots}">
			<c:url var="dateilLink" value="/linebotDetail">
				<c:param name="botId" value="${lineBotItem.botId}" />
			</c:url>
			
			<c:url var="deleteLink" value="/linebotDelete">
				<c:param name="botId" value="${lineBotItem.botId}" />
			</c:url>  

			<tr>
				<td>${lineBotItem.botId}</td>
				<td>${lineBotItem.token}</td>
				<td>${lineBotItem.secret}</td>			
				<td>${lineBotItem.displayName}</td>
				<td>${lineBotItem.type}</td>
				<td>${lineBotItem.createDate}</td>
				<td>${lineBotItem.updateDate}</td>
				<td><a href="${dateilLink}">修改</a></td>				
				<td><a href="${deleteLink}">刪除</a></td>	
					
			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>
<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
 --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>

</body>
</html>