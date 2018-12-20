<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students result</title>
</head>
<body>
	<c:forEach var="tempStudent" items="${students}">
		${tempStudent.toString()}
	</c:forEach>
</body>
</html>