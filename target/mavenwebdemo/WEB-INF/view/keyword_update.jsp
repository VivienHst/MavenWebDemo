<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
	<h1>keyword_update.jsp</h1>
	
	<div id="container">
		<form:form action="keywordUpdate" modelAttribute="keyword" method="post">
			<table>
				<tbody>
					<tr>
						<td>Key : </td>
						<td><form:input path="chatKey" value="${keyword.chatKey}"/></td>
					</tr>
					<tr>
						<td>Value : </td>
						<td><form:input path="chatValue" value="${keyword.chatValue}"/></td>
					</tr>
				</tbody>
			</table>
			<form:input type="hidden" path="cId" value="${keyword.cId}"/>
			<form:input type="hidden" path="createDate" value="${keyword.createDate}"/>
			<input type="submit" value="更新">
			
		</form:form>
	</div>
	
</body>
</html>