<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/login/login.js"></script>
</head>



<div class="ui segment">

	<h3>Login</h3>


	<form:form action="${pageContext.request.contextPath}/authUser"
		method="POST">
		<!-- Λάθος στοιχεία -->
		<c:if test="${param.error != null}">
			<i>Λάθος Username ή password</i>
		</c:if>

		<!-- Έλεγχος για Logout -->
		<c:if test="${param.logout!=null}">

			<i> Έχεις αποσυνδεθεί επιτυχώς</i>

		</c:if>

		<div class="field">
			<label>User Name</label> <input type="text" name="username" />
		</div>
		<div class="field">
			<label>Password</label> <input type="password" name="password" />
		</div>
		<div class="field">
			<input type="submit" value="Login" />
		</div>
	</form:form>


	<div class="hiddenForm">
		<label>User Name</label> <input type="text" name="username" />
	</div>
	<div>
		<label>Password</label> <input type="password" name="password" />
	</div>
	<div>
		<input type="submit" value="Login" />
	</div>

</div>