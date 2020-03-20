<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">


</head>
<body>
			
	<jsp:include page="navbar_and_side_menu.jsp"></jsp:include><!-- 第二種 -->

	<div id="main">
		<form:form action="saveChatKeyword" modelAttribute ="keyword"  method="post">
		<table border="1" >				
			<tr>
				<td>Key : </td>
				<td>
					<form:input path="chatKey"/>
					<br>
					<form:errors path="chatKey" class="error-msg"/>
				</td> 
				<td>Value : </td>
				<td>
					<form:input path="chatValue" />
					<br>
					<form:errors path="chatValue" class="error-msg"/>
				</td>			
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
				<td>
					<fmt:formatDate value="${chatKeyword.createDate}" pattern="yyyy-MM-dd HH:mm" />
				</td>
											
				
				<td><a href="${dateilLink}">修改</a></td>				
				<td><a href="${deleteLink}">刪除</a></td>	
					
			</tr>
		</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/home">Home</a>	

	</div>
<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
 --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>

</body>
</html>