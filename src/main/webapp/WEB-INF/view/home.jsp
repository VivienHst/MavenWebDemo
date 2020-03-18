<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">


</head>
<body>
	<jsp:include page="navbar_and_side_menu.jsp"></jsp:include>

	<div id="main">
		<h1>Hello!</h1>
		<div>
			測試用帳號，使用Line掃描條碼加入好友後測試
		</div>
		<br>
		<div>
			<img src="https://i.imgur.com/sVhcsRf.png" width="200" height="200"/>
		</div>

	</div>
<%-- 	<script type="text/javascript" src="<c:url value="/resources/static/js/side_menu.js" />"></script>
 --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/side_menu.js"></script>

</body>
</html>