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
<title>Contact</title>
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
		 / <a href="${pageContext.request.contextPath}/contact">Contact</a>
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
		
	<div class="container">

		<div id="loginbox">

			<div class="panel panel-info body-style">

				<div class="panel-heading">
					<div class="panel-title">Contact Us</div>
				</div>

				<div style="margin-top: 75px;padding: 30px;background-color: #fff;" class="panel-body body-style">

					<!-- Login Form -->
					<form id="contact-form" method="post" role="form" onsubmit="Submit(event); return false;">

					<div class="messages"></div>

					<div class="controls">

						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_email">Username</label>
									<input id="form_email" type="email" name="email" class="form-control"  disabled  value='<c:out value="${username}" />'>
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form_message">Message *</label>
									<textarea id="form_message" name="message" class="form-control" placeholder="Message *" rows="4" required="required" data-error="Please, leave us a message."></textarea>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-12">
								<input type="submit" class="btn btn-success btn-send" value="Send message">
							</div>
						</div>
						
					</div>
					<!-- Place for messages: error, alert etc ... -->
					<div class="form-group body-style" style="margin-top: 1rem;">
						<div class="col-xs-15">
							<div>
								<div class="alert alert-success" role="alert" id="success" style="display: none;">
									Your form was submitted successfully!
								</div>
								<div class="alert alert-warning" role="alert" id="fail" style="display: none;">
									Something went wrong.
								</div>
								<div class="alert alert-warning" role="alert" id="empty" style="display: none;">
									Please fill in all the required fields.
								</div>
								<div class="alert alert-info" role="alert" id="wait" style="display: none;">
									Please wait...
								</div>
							</div>
						</div>
					</div>
				</form>
				</div>

			</div>

		</div>

	</div>
<script>
	function Submit(e){
		e.preventDefault();
		document.getElementById("fail").style.display = "none";
		document.getElementById("success").style.display = "none";
		document.getElementById("wait").style.display = "block";
		var xhttp = new XMLHttpRequest();
		xhttp.open("POST", '${pageContext.request.contextPath}'+'/mail/sendMailContact', true);
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4)
				document.getElementById("wait").style.display = "none";
			if (this.readyState == 4 && this.status == 200) {
				var response = JSON.parse(xhttp.responseText);
				if(response.status != "success"){
				   document.getElementById("fail").style.display = "block";
			   }
			   else if(response.status == "error"){
				   document.getElementById("fail").style.display = "block";
				   document.getElementById("empty").style.display = "none";
			   }
			   else if(response.status == "success"){
				   document.getElementById("fail").style.display = "none";
				   document.getElementById("success").style.display = "block";
			   }
			}
			else if (this.readyState == 4){
				document.getElementById("fail").style.display = "block";
			}
		};
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhttp.setRequestHeader(header, token);
		
		xhttp.send("message="+encodeURIComponent(document.forms[1].message.value));
	}
</script>
</body>
</html>