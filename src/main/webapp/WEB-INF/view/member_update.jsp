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
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>
	
	</head>
	<body>
		<jsp:include page="navbar_and_side_menu.jsp"></jsp:include><!-- 第二種 -->
	
		<div id="main">
			<h1>member_update.jsp</h1>
	
			<div id="container">
				<form:form action="memberUpdate" modelAttribute="member" method="post">
					<table>
						<tbody>
							<tr>
								<td>Account : </td>
								<td><form:label  path="account" value="${member.account}"/>${member.account}</td>
							</tr>
							
							<tr>
								<td>FirstName : </td>
								<td><form:label  path="account" value="${member.firstName}"/>${member.firstName}</td>
							</tr>
							
							<tr>
								<td>FirstName : </td>
								<td><form:label  path="account" value="${member.lastName}"/>${member.lastName}</td>
							</tr>
							<c:forEach var="memberPermission" items="${memberPermissions}">
							
					
								<tr>
									<td>${memberPermission.memberPermissionPK.permission}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>					
					<form:input type="hidden" path="uid" value="${member.uid}"/>					
					<input type="submit" value="更新">
					
				</form:form>
			</div>
			<table>
				<tbody>
					
					
			
				</tbody>
			</table>
		</div>
		
	</body>
</html>