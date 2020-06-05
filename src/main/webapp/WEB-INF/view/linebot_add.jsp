<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>新增機器人</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
		
	</head>
	<body>
				
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>
		
		<div id="main">
			<form:form action="saveLineBot" modelAttribute ="linebot"  method="post">
				<table border="0" >			
					<tr>
						<td>DisplayName : </td>
						<td><form:input path="displayName"/>
						<td><form:errors path="displayName" class="error-msg"/>
					</tr>	
					
					<tr>
						<td>Token : </td>
						<td><form:input path="token" />	
						<td><form:errors path="token" class="error-msg"/>
					</tr>
					
					<tr>
						<td>Secret : </td>
						<td><form:input path="secret" />
						<td><form:errors path="secret" class="error-msg" />
					</tr>
					
					<tr>						
						<td>BotUid : </td>
						<td><form:input path="botUid"/>
						<td><form:errors path="botUid" class="error-msg" />
					</tr>
					<tr>						
						<td>Type : </td>
						<td><form:input path="type"/>	
						<td><form:errors path="type" class="error-msg" />			
					</tr>
					
					<tr>
						<td><input type="submit" value="新增"></td>				
					</tr>
				</table>
				
			</form:form>
			<a href="${pageContext.request.contextPath}/linebot">回列表</a>	
			<a href="${pageContext.request.contextPath}/home">Home</a>	
	
		</div>
	<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
	 --%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
	
	</body>
</html>