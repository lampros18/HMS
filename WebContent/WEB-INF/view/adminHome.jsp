<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h5>Admin home page</h5>

<a href="${pageContext.request.contextPath}/admin/createEmployee">Create employee</a>

	<!-- Υποστήριξη logout -->
	<form:form action="${pageContext.request.contextPath}/admin_logout"
		method="POST">
		<input type="submit" value="logout" />
		<br />
	</form:form>
	
</body>
</html>