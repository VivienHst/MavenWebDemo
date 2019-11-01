<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<table border="1" >		
		<tr>
			<th>CId</th>
			<th>ChatKey</th>
			<th>ChatValue</th>
			<th>CreateDate</th>
		</tr>
		
		<c:forEach var="chatKeyword" items="${chatKeywords}">
		
			<tr>
				<td>${chatKeyword.cId}</td>
				<td>${chatKeyword.chatKey}</td>
				<td>${chatKeyword.chatValue}</td>
				<td>${chatKeyword.createDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>