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
<title>Foreman Home</title>
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
	
<!-- Χεράτο  css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/employee/css/employee/employeeHome.css">
<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
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
		<!-- Επιπλέον modal -->
	<div id="error" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Caution</h5>

				</div>
				<div class="modal-body" id='modal-body'>
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">ok</button>

				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="success" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">System report</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div style="position:fixed; left: 10px; top: 65px;">
		/ Foreman / <a href="${pageContext.request.contextPath}/foreman/home">Home</a>
	</div>


	<div class="jumbotron container">

		<h5 style="margin-left: 45%; padding-bottom: 5%; color: green">Time
			period</h5>


		<form>

			<div style="border: 1px solid gray; padding: 5%">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="date">Starting Date</label> <input id="date"
							width="312" />

					</div>
					<div class="form-group col-md-6">
						<label for="date1">Ending Date</label> <input id="date1"
							width="312" />

					</div>

				</div>

			</div>

			<div>
				<h5
					style="color: green; padding-left: 41%; padding-top: 3%; padding-bottom: 5%;">Free
					slots per department</h5>
			</div>

			<div style="border: 1px solid grey; padding: 5%">
				<div style="display: flex;">
					<div class="form-group col-md-4" style="display: grid;">
						<label for="inputState" style="width: 2;">Informatics and
							telematics</label> <select id="inputState" size="5" style="">

						</select>
					</div>
					<div class="form-group col-md-4" style="display: grid;">
						<label for="inputState" style="width: 2;">Home economics
							and ecology</label> <select id="inputState" size="5" style="">

						</select>
					</div>
					<div class="form-group col-md-4" style="display: grid;">
						<label for="inputState" style="width: 2;">Geography</label> <select
							id="inputState" size="5" style="">

						</select>
					</div>




				</div>
				<div style="display: flex;">
					<div class="form-group col-md-4" style="display: grid;">
						<label for="inputState" style="width: 2;">International
							master of sustainable tourism development </label> <select
							id="inputState" size="5" style="">

						</select>
					</div>
					<div class="form-group col-md-4" style="display: grid;">
						<label for="inputState" style="width: 2;">Nutrition and
							dietics<br>&nbsp;</label> <select id="inputState" size="5" style="">

						</select>
					</div>




				</div>

			</div>

			<div class="container" style="margin-top:15px;">
				<button type="submit" class="btn btn-primary" 
					onclick="return false" style="position: absolute;left: 50%;transform:translate(-50%,0);">Update system</button>
			</div>
		</form>


	</div>

	<script
		src="${pageContext.request.contextPath}/resources/foreman/home.js"></script>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>
</html>