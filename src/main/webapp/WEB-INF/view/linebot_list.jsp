<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
		
		
	</head>
	<body>
				
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>
		
		<div id="main">
			
			<table border="1" >		
				<tr>
					<th>BotId</th>
					<!-- <th>Token</th> -->
					<th>DisplayName</th>					
					<th>Type</th>
					<th>CreateDate</th>
					<th>UpdateDate</th>
					<th>Update</th>
					<th>Delete</th>
					<th>Members</th>
					
				</tr>
				
				<c:forEach var="lineBotItem" items="${lineBots}">
					<c:url var="dateilLink" value="/linebotDetail">
						<c:param name="botId" value="${lineBotItem.botId}" />
					</c:url>
					
					<c:url var="deleteLink" value="/linebotDelete">
						<c:param name="botId" value="${lineBotItem.botId}" />
					</c:url> 
					
					<c:url var="membersLink" value="/linebotMembers">
						<c:param name="botId" value="${lineBotItem.botId}" />
					</c:url>  
		
					<tr>
						<td>${lineBotItem.botId}</td>
						<td>${lineBotItem.displayName}</td>
						
						<%-- <td>${lineBotItem.token}</td> --%>
						<%-- <td>${lineBotItem.secret}</td> --%>			
						<td>${lineBotItem.type}</td>
						<td>
							<fmt:formatDate value="${lineBotItem.createDate}" pattern="yyyy-MM-dd HH:mm" />
						</td>
						<td>
							<fmt:formatDate value="${lineBotItem.updateDate}" pattern="yyyy-MM-dd HH:mm" />							
						</td>
						<td><a href="${dateilLink}">修改</a></td>				
						<td><a href="${deleteLink}">刪除</a></td>	
						<td><a href="${membersLink}">會員</a></td>	
							
					</tr>
				</c:forEach>
			</table>
			<a href="${pageContext.request.contextPath}/addLineBot">新增機器人</a>	
			
			<a href="${pageContext.request.contextPath}/home">Home</a>	
	
		</div>
	<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
	 --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
	
	</body>
</html>