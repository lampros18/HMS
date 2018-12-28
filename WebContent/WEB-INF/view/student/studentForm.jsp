<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
</head>
<title>Insert Student</title>
<body>

	<form:form method="POST"
		action="${pageContext.request.contextPath}/admin/createStudent"
		accept-charset="UTF-8">


		<input type="text" placeholder="email" name="email" id="email">
		<br>
		<input type="text" placeholder="id" name="id" id="id">
		<br>
		<input type="text" placeholder="name" name="name" id="name">
		<br>
		<input type="text" placeholder="surname" name="surname" id="surname">
		<br>
		<input type="text" placeholder="password" name="password"
			id="password">
		<br>
		<input type="text" placeholder="birthdate" name="birthdate">
		<br>
		<input type="text" placeholder="yearOfEnrollment"
			name="yearOfEnrollment">
		<br>
		<input type="text" placeholder="isPostgraduate" name="isPostgraduate">
		<br>
		<input type="text" placeholder="department" name="department">
		<br>
		<input type="text" placeholder="phone" name="phone">
		<br>
		<input type="text" placeholder="address" name="address">
		<br>
		<input type="text" placeholder="employeeId" name="employeeId">
		<br>
		<input type="submit" value="Αποθήκευση φοιτητή" />
	</form:form>

	<script>
		$(document).ready(function() {
			$("#password").val(Math.random().toString(36).substring(2).replace("l","(").replace("I",")").replace("1","m"));
		});
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		function sendJson() {
			function success(data) {
				console.log("success");
			}
			function error(data) {
				console.log("error");
			}
			$.ajax({
				type : 'POST',
				contentType : "application/json;charset=UTF-8",
				url : '<c:url value="/admin/createStudent" />',
				data : JSON.stringify({
					email : document.getElementById("email").value,
					name : document.getElementById("name").value,
					surnname : document.getElementById("surname").value
				}),
				dataType : 'json',
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : success,
				error : error,
			});
		}

	</script>
</body>
</html>