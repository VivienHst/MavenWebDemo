<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link type="text/css" 
	rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	<h1>keyword_list.jsp</h1>
	<form:form action="saveChatKeyword" modelAttribute ="keyword"  method="post">
		<table border="1" >				
			<tr>
				<td>Key : </td>
				<td><form:input path="chatKey"/></td>
				
				<td>Value : </td>
				<td><form:input path="chatValue" /></td>
				
				<td>Key</td>
			
				<td><input type="submit" value="新增"></td>				
			</tr>
	</table>
		
	</form:form>
	
	<table>
	</table>
	
	<table border="1" >		
		<tr>
			<th>CId</th>
			<th>ChatKey</th>
			<th>ChatValue</th>
			<th>CreateDate</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		
		<c:forEach var="chatKeyword" items="${chatKeywords}">
			<c:url var="dateilLink" value="/keywordDetail">
				<c:param name="cId" value="${chatKeyword.cId}" />
			</c:url>
			
			<c:url var="deleteLink" value="/keywordDelete">
				<c:param name="cId" value="${chatKeyword.cId}" />
			</c:url>

			<tr>
				<td>${chatKeyword.cId}</td>
				<td>${chatKeyword.chatKey}</td>
				<td>${chatKeyword.chatValue}</td>
				<td>${chatKeyword.createDate}</td>
				<td><a href="${dateilLink}">修改</a></td>				
				<td><a href="${deleteLink}">刪除</a></td>		
			</tr>
		</c:forEach>
	</table>
</body>
</html>