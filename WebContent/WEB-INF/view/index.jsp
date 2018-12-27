<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
<h4> Home Page</h4>
<a href="${pageContext.request.contextPath}/admin/home">Admin Login</a>
<br/>
<a href="${pageContext.request.contextPath}/student/home">Student Login</a>
<br/>
<a href="${pageContext.request.contextPath}/employee/home">Employee Login</a>
</body>
</html>