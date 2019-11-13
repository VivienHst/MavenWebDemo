<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Skill</title>
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
			
			
	</head>
	<body>
				
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>
		
		<div id="main">
			<form:form action="saveSkill" modelAttribute ="newSkill"  method="post">
			<table border="1" >				
				<tr>
					<td>Skill Name : </td>
					<td><form:input path="skillName"/></td>
					
					<td>SKill Desc : </td>
					<td><form:input path="skillDesc" /></td>
					
					<td><input type="submit" value="新增"></td>				
				</tr>
			</table>
				
			</form:form>
			
			<table>
			</table>
			
			<table border="1" >		
				<tr>
					<th>SkillId</th>
					<th>SKillName</th>
					<th>SkillDesc</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
				
				<c:forEach var="skill" items="${skills}">
					<c:url var="dateilLink" value="/skillDetail">
						<c:param name="skillId" value="${skill.skillId}" />
					</c:url>
					
					<c:url var="deleteLink" value="/skillDelete">
						<c:param name="skillId" value="${skill.skillId}" />
					</c:url>
		
					<tr>
						<td>${skill.skillId}</td>
						<td>${skill.skillName}</td>
						<td>${skill.skillDesc}</td>
						<td><a href="${dateilLink}">修改</a></td>				
						<td><a href="${deleteLink}">刪除</a></td>	
							
					</tr>
				</c:forEach>
			</table>
		<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>
<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
 --%>
</body>
</html>