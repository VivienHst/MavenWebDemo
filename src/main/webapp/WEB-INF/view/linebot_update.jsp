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
		<h1>linebot_update.jsp</h1>
	
		<div id="container">
			<form:form action="linebotUpdate" modelAttribute="linebot" method="post">
				<table>
					<tbody>
						<tr>
							<td>Token : </td>
							<td><form:input path="token" value="${linebot.token}"/></td>
						</tr>
						<tr>	
							<td>Secret : </td>
							<td><form:input path="secret" value="${linebot.secret}"/></td>
						</tr>
						<tr>
							<td>DisplayName : </td>
							<td><form:input path="displayName" value="${linebot.displayName}"/></td>
						</tr>
						<tr>	
							<td>Type : </td>
							<td><form:input path="type" value="${linebot.type}"/></td>
						</tr>
						
						<tr>	
							<td>Skill : </td>
							<td>
								<table>
									<c:forEach var="skill" items="${linebot.skills}">
										<tr>	
											<td>
												${skill.skillName}
											</td>
										</tr>
									</c:forEach>
								</table>
								
							</td>					
						</tr>
					</tbody>
				</table>
				<form:input type="hidden" path="botId" value="${linebot.botId}"/>
				<form:input type="hidden" path="createDate" value="${linebot.createDate}"/>
				<input type="submit" value="更新">
				
			</form:form>
		</div>
	</div>
	
</body>
</html>