<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<!-- Υποστήριξη logout -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST" accept-charset="utf-8">
		<input type="submit" value="logout" />
		<br />
	</form:form>

	<c:if test="${param.error!=null}">

		<i>Λάθος user name ή password</i>

	</c:if>


	<!-- Έστω μια φόρμα εισαγωγής στοχείων φοιτητή 
Κανονικά θα πρέπει να επιλέξει ο διαχειριστής μια λειτουργία από το menu για να κάνει 
κάτι τέτοιο.-->

	<form:form method="POST"
		action="${pageContext.request.contextPath}/createStudent" accept-charset="ISO-8859-1" >

		<table>
			<tr>
				<td>Name:</td>
				<td><input name="name" type="text" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" type="text" /></td>
			</tr>
		
		

			<tr>
				<td></td>
				<td><input value="Αποθήκευση φοιτητή" type="submit" /></td>
			</tr>
		</table>

	</form:form>







</body>
</html>