<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="UTF-8">
<title>About page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/menu.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/hamburgers.css">
	<script src="${pageContext.request.contextPath}/resources/hamburger.js"></script>
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300);
body{
	background-color:#e4eaec;
	font-family: 'Roboto Condensed';
}
</style>
</head>
<body>
	<div style="position:fixed; left: 10px; top: 65px;">
		Foreman / <a href="${pageContext.request.contextPath}/foreman/home">Home</a>
	</div>
	<div
		style="background-color: #3e8ef7; width: 130%; height: 56px; position: fixed; top: 0;">
		<button class="hamburger hamburger--3dy" type="button" id="hamburger">
			<span class="hamburger-box"> <span class="hamburger-inner"></span>
			</span>
		</button>
		<div style="color: white; font-size: 2em; display: inline; pointer-events:none; user-select: none;">HMS</div>
	</div>
	<div id='cssmenu'>
		<ul style="user-select: none;">
			<li style="pointer-events:none;"><a href='#'><i class="fas fa-user-tie"> <span style="text-transform: none; font-family: 'Roboto Condensed';"><c:out value="${username}" /></span></i></a></li>
			
			<c:choose>
				<c:when test="${authorities.size()>1}">
					<li class='active has-sub'><a href='#'><span>Home</span></a>
						<ul>
							<c:forEach var="i" begin="0" end="${authorities.size()-1}">
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_ADMIN\")}">
										<li><a href="${pageContext.request.contextPath}/admin/editUsers"><span>Admin</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_FOREMAN\")}">
										<li><a href="${pageContext.request.contextPath}/foreman/home"><span>Foreman</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
										<li><a href="${pageContext.request.contextPath}/employee/employeeHome"><span>Employee</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_STUDENT\")}">
										<li><a href="${pageContext.request.contextPath}/student/studentHome"><span>Student</span></a></li>
									</c:if>
							</c:forEach>
						</ul></li>
				</c:when>
				<c:otherwise>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_ADMIN\")}">
						<li><a href="${pageContext.request.contextPath}/admin/editUsers"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_FOREMAN\")}">
						<li><a href="${pageContext.request.contextPath}/foreman/home"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
						<li><a href="${pageContext.request.contextPath}/employee/employeeHome"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_STUDENT\")}">
						<li><a href="${pageContext.request.contextPath}/student/studentHome"><span>Home</span></a></li>
					</c:if>
					
				</c:otherwise>
			</c:choose>

			<li><a href='${pageContext.request.contextPath}/about'><span>About</span></a></li>
			<li><a href='${pageContext.request.contextPath}/contact'style="font-weight: 900;"><span>Contact</span></a></li>
			<li><a href='#' id="logoutBtn"><span>Logout</span></a></li>

			<li
				style="position: fixed; top: -1000; left: -1000; display: none; visibility: hidden; width: 0px; height: 0px;"><form:form
					action="${pageContext.request.contextPath}/logout" method="POST">
					<button type="submit" id="logoutSubmit">Logout</button>
				</form:form></li>
		</ul>
	</div>
</body>
</html>