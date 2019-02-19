<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Employee Home</title>

<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/menu.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/employee/css/employee/employeeHome.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/hamburgers.css">
</head>

<body>
	<!--<div style="height: 100%;width:100%;background: url(https://pixabay.com/get/e837b90e2dfc003ed1534705fb0938c9bd22ffd41cb4114597f4c47fa3/books-1281581_1920.jpg);background-size: cover;box-sizing: unset;position: fixed;z-index: -100;top: 0;filter: grayscale(50%) blur(6px);"></div>
	-->

	<div style="position:absolute; left: 10px; top: 57px;">
		/ Employee / <a href="${pageContext.request.contextPath}/employee/employeeHome">Home</a>
	</div>
	<div
		style="background-color: #3e8ef7; width: 130%; height: 56px; position: fixed; top: 0; z-index:2;">
		<button class="hamburger hamburger--3dy" type="button" id="hamburger">
			<span class="hamburger-box"> <span class="hamburger-inner"></span>
			</span>
		</button>
		<div
			style="color: white; font-size: 2em; display: inline; pointer-events: none; user-select: none;">HMS</div>
		<input type="text" placeholder="Search..." id="searchField"
			class="searchTerm">
		<div id="closeSearch">
			<i class="fas fa-times" style="opacity: 0.6;"></i>
		</div>
		<div id="openSearch">
			<i class="fas fa-search" style="opacity: 1;"></i>
		</div>
	</div>
	<div id='cssmenu'>
		<ul style="user-select: none;">
			<li style="pointer-events: none;"><a href='#'><i
					class="fas fa-user-tie"> <span
						style="text-transform: none; font-family: 'Roboto Condensed';"><c:out
								value="${username}" /></span></i></a></li>

			<c:choose>
				<c:when test="${authorities.size()>1}">
					<li class='active has-sub'><a href='#'><span>Home</span></a>
						<ul>
							<c:forEach var="i" begin="0" end="${authorities.size()-1}">
								<c:if
									test="${authorities.get(i).getAuthority().equals(\"ROLE_ADMIN\")}">
									<li><a
										href="${pageContext.request.contextPath}/admin/editUsers"><span>Admin</span></a></li>
								</c:if>
								<c:if
									test="${authorities.get(i).getAuthority().equals(\"ROLE_FOREMAN\")}">
									<li><a
										href="${pageContext.request.contextPath}/foreman/home"><span>Foreman</span></a></li>
								</c:if>
								<c:if
									test="${authorities.get(i).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
									<li><a
										href="${pageContext.request.contextPath}/employee/employeeHome"><span>Employee</span></a></li>
								</c:if>
								<c:if
									test="${authorities.get(i).getAuthority().equals(\"ROLE_STUDENT\")}">
									<li><a
										href="${pageContext.request.contextPath}/student/studentHome"><span>Student</span></a></li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:when>
				<c:otherwise>
					<c:if
						test="${authorities.get(0).getAuthority().equals(\"ROLE_ADMIN\")}">
						<li><a
							href="${pageContext.request.contextPath}/admin/editUsers"><span>Home</span></a></li>
					</c:if>
					<c:if
						test="${authorities.get(0).getAuthority().equals(\"ROLE_FOREMAN\")}">
						<li><a href="${pageContext.request.contextPath}/foreman/home"><span>Home</span></a></li>
					</c:if>
					<c:if
						test="${authorities.get(0).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
						<li><a
							href="${pageContext.request.contextPath}/employee/employeeHome"><span>Home</span></a></li>
					</c:if>
					<c:if
						test="${authorities.get(0).getAuthority().equals(\"ROLE_STUDENT\")}">
						<li><a
							href="${pageContext.request.contextPath}/student/studentHome"><span>Home</span></a></li>
					</c:if>

				</c:otherwise>
			</c:choose>

			<li><a href='${pageContext.request.contextPath}/employee/housingApplications'><span>Applications</span></a></li>
			<li><a href='${pageContext.request.contextPath}/about'><span>About</span></a></li>
			<li><a href='${pageContext.request.contextPath}/contact'><span>Contact</span></a></li>
			<li><a href='#' id="logoutBtn"><span>Logout</span></a></li>

			<li
				style="position: fixed; top: -1000; left: -1000; display: none; visibility: hidden; width: 0px; height: 0px;"><form:form
					action="${pageContext.request.contextPath}/logout" method="POST">
					<button type="submit" id="logoutSubmit">Logout</button>
				</form:form></li>
		</ul>
	</div>

	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add user</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="email" class="col-form-label">Email</label> <input
								type="text" class="form-control" id="employee_email">
						</div>
						<div class="form-group">
							<label for="password" class="col-form-label">Password<span
								id="reload-pass"> <i class="fas fa-redo-alt"></i></span></label> <input
								type="text" class="form-control" id="employee_password">
						</div>
						<br />
						<div class="form-group">
							User type <br /> <input type="radio" name="user_type"
								id="type_employee" /> <label for="type_employee"
								class="col-form-label">Employee</label> <br /> <input
								type="radio" name="user_type" id="type_student" class="has-val" />
							<label for="type_student" class="col-form-label">Student</label>

						</div>
						<br />
						<div class="form-group">
							Authorities <br /> <input type="checkbox"
								name="employee_authority_admin" id="employee_authority_admin"
								class="has-val" /> <label for="employee_authority_admin"
								class="col-form-label">Admin</label> <br /> <input
								type="checkbox" name="employee_authority_foreman"
								id="employee_authority_foreman" class="has-val" /> <label
								for="employee_authority_foreman" class="col-form-label">Foreman</label>
							<br /> <input type="checkbox" name="employee_authority_employee"
								id="employee_authority_employee" class="has-val" /> <label
								for="employee_authority_employee" class="col-form-label">Employee</label>
						</div>
						<div class="form-group">
							<input type="checkbox" name="enabled" id="employee_enabled">
							<label for="employee_enabled" class="col-form-label">Enabled</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="submit-btn"
						onclick="event.preventDefault();">Add user</button>
				</div>
			</div>
		</div>
	</div>

	<div id="loading" class="loading_margin container">
		<div class="text-center">
			<button class="btn btn-primary" type="button" disabled>
				<span class="spinner-border spinner-border-sm" role="status"
					aria-hidden="true"></span> Loading...
			</button>
		</div>
	</div>
	<div id="result">
		<button type="button" class="close"
			onclick="closeResult();hideButton();" aria-label="Close">
			<span aria-hidden="true" id="close_button">&times;</span>
		</button>

	</div>

	<div class="jumbotron container"
		style="margin-top: 5rem; background: aliceblue;">

		<div class="alert alert-success" role="alert" style="padding-bottom:10px;display:none">This is a success
			alert—check it out!</div>


		<form>
			<div class="form-row">
				<div class="col-md-4 mb-3" style="pointer-events: none;">
					<label for="validationServerUsername">User Name</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend3">@</span>
						</div>
						<select id="validationServerUsername"
							aria-describedby="inputGroupPrepend3" class="form-control"
							></select>
						<div class="invalid-feedback">Please choose a username.</div>
					</div>
				</div>
				<div class="col-md-4 mb-3">
					<label for="validationServer02">Name</label> <input
						class="form-control" id="validationServer02" placeholder=""
						value="" type="text">
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">Name should contain only
						characters and be at least 3 characters long</div>
				</div>
				<div class="col-md-4 mb-3">
					<label for="validationServer03">Last Name</label> <input
						class="form-control" id="validationServer03" placeholder=""
						value="" type="text">
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">Last name should contain only
						characters and be at least 3 characters long</div>
				</div>

			</div>

			<div class="form-row" style="margin-top: 30px;">

				<div class="col-md-4 mb-3" style="display: contents;">

					<div>
						<label for="date">Birth Date</label>
						<div role="wrapper"
							class="gj-datepicker gj-datepicker-md gj-unselectable"
							style="width: 356px; background-color: white; border: 1px solid rgb(206, 212, 218); padding: 7px; border-radius: 0.25rem;">
							<input id="date" />
						</div>
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Enter a valid birthdate format
							yyyy-mm-dd</div>
					</div>

					<div class="col-md-4 mb-3">
						<label for="validationServer04">Year of enrollment</label> <input
							class="form-control" id="validationServer04" placeholder=""
							value="" style="width: 353px;" type="text">
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Enter a valid year of
							enrollment (format yyyy)</div>


					</div>

					<div class="custom-control custom-checkbox"
						style="margin-top: 40px; margin-left: 20px;">
						<input class="custom-control-input" id="customCheck1"
							type="checkbox"> <label class="custom-control-label"
							for="customCheck1">Check this if the student is
							postgraduate</label>
					</div>
				</div>

			</div>





			<div class="form-row" style="margin-top: 45px; margin-bottom: 27px;">

				<div class="col-md-4 mb-3" style="padding-left: 0px;">
					<label for="sel1">Choose department</label> <select
						class="form-control" id="sel1">
						<option>Informatics and Telematics</option>
						<option>Home economics and ecology</option>
						<option>Geography</option>
						<option>International master of sustainable tourism
							development</option>
						<option>Nutrition and dietics</option>
					</select>
				</div>

				<div class="col-md-4 mb-3">
					<label for="validationServer06">Phone</label> <input
						class="form-control" id="validationServer06" placeholder=""
						value="" type="text">
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">A valid phone should be 10
						digits long</div>
				</div>

				<div class="col-md-4 mb-3">
					<label for="validationServer07">Address</label> <input
						class="form-control" id="validationServer07" placeholder=""
						value="" type="text">
					<div class="valid-feedback">Looks good</div>
					<div class="invalid-feedback">Type only characters , numbers
						and slashes Address should be at least 3 characters long</div>
				</div>

			</div>



			<div class="form-group"
				style="display: ruby-text-container; margin-top: 1px;">


				<div style="float: right; display: none;">
					<i style="color: green; display: inline-table; float: none;">There
						are -1 students to check</i>
				</div>
			</div>

			<div>

				<button class="btn btn-primary" type="submit"
					style="margin-top: 10px; widh: 121px;" onclick="return false;">Save
					student</button>
				<button class="btn btn-primary" type="submit"
					style="margin-top: 10px; widh: 121px; float: right; margin-right: 21px;"
					onclick="return false;">Show next student</button>
			</div>



		</form>
	</div>



 	<script
		src="${pageContext.request.contextPath}/resources/hamburger.js">
		
	</script>

	<script
		src="${pageContext.request.contextPath}/resources/employee/js/hamburgerEmployee.js">
		
	</script>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>

</html>