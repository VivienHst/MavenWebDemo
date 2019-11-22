<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Member</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
</head>
<body>
			
	<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>	
	<div id="main">
		<form:form action="saveMember" modelAttribute ="newMember"  method="post">
			<table border="1" >		
				<tr>
					<td>Account</td>
					<td><form:input path="account"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" path="password" /></td>
				</tr>
				<tr>
					<td>FirstName</td>
					<td><form:input path="firstName"/></td>
				</tr>
				<tr>
					<td>LastName</td>
					<td><form:input path="lastName"/></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email"/></td>
				</tr>
				<%-- <tr>
					<td>PhoneCode</td>
					<td><form:input path="phoneCode"/></td>
				</tr>
				<tr>
					<td>PhoneNumber</td>
					<td><form:input path="phoneNumber"/></td>
				</tr> --%>
			</table>
			<input type="submit" value="新增">				
		</form:form>
		<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>

</body>
</html>