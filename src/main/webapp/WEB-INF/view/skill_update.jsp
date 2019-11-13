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
	</head>
	<body>
				
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>
		
		<div id="main">
	
			<div id="container">
				<form:form action="skillUpdate" modelAttribute="skill" method="post">
					<table>
						<tbody>
							<tr>
								<td>Skill Name : </td>
								<td><form:input path="skillName" value="${skill.skillName}"/></td>
							</tr>
							<tr>	
								<td>Skill Desc : </td>
								<td><form:input path="skillDesc" value="${skill.skillDesc}"/></td>
							</tr>
						
							<tr>					
						</tbody>
					</table>
					<form:input type="hidden" path="skillId" value="${skill.skillId}"/>
					<input type="submit" value="更新">
					
				</form:form>
			</div>	
		</div>	
	
	</body>
</html>