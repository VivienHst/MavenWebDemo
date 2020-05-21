<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
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
		<a href="${pageContext.request.contextPath}/animal/all">回列表</a>

		<form:form action="addAnimal" method="post" modelAttribute="animalVO" >
			<table>
				<tr>
					<td>Name : </td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" class="error-msg"/></td>
				</tr>
				<tr>
					<td>Name(English) : </td>
					<td><form:input path="nameEN" /></td>
					<td><form:errors path="nameEN" class="error-msg"/></td>
				</tr>
				
				<tr>
					<td>Gender : </td>
					<td><form:input path="gender" /></td>
					<td><form:errors path="gender" class="error-msg"/></td>
				</tr>
				
				<tr>
					<td>Species : </td>
					<td><form:input path="species" /></td>
					<td><form:errors path="species" class="error-msg"/></td>
					
				</tr>
				
				<tr>
					<td>Personality : </td>
					<td><form:input path="personality" /></td>
					<td><form:errors path="personality" class="error-msg"/></td>
				</tr>
				
				<tr>
					<td>InitialPhrase : </td>
					<td><form:input path="initialPhrase" /></td>
					<td><form:errors path="initialPhrase" class="error-msg"/></td>
				</tr>
				
				<tr>
					<td>Birthday : </td>
					<td>
						<form:input type="number" path="birthdayMonth" />月
						
						<form:input type="number" path="birthdayDay" />日
					</td>
					<td><form:errors path="birthdayMonth" class="error-msg"/></td>
				</tr>
				<tr>
					<td>Picture : </td>
					<td><form:input path="picture" /></td>
					<td><form:errors path="picture" class="error-msg"/>
				</tr>
			
			</table>
				
			<input type="submit" value="新增"/>
		</form:form>
		
	</div>
	
</body>
</html>