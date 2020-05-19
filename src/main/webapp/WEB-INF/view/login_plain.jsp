<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/css/style.css">

</head>
<body class="login">
	<h1>登入</h1>

	<form:form action="${pageContext.request.contextPath}/authenticateTheUser"
				method="POST" >
							
 		<c:if test="${param.error!=null}">
 			<h3 class="error-msg">帳號或密碼錯誤，請重新輸入</h3>		
		</c:if>
		<c:if test="${param.logout!=null}">
 			<h3>You have been logged out.</h3>		
		</c:if>
		
		 <p>		
        	 Account  : <input type="text" name="username" placeholder="Account"/>
	     </p>		
    	 <p>		
    		 Password : <input type="password" name="password" placeholder="Password"/>
 		 </p>		
 		 <p>
  			 <input type="submit" value="Login"/> 
 		 </p>
 		 <%-- <input type="hidden" 
 		 		name="${_csrf.parameterName}"
 		 		value="${_csrf.token}" /> --%>
	</form:form>
</body>
</html>