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
<title>Users table</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/admin/menu.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/hamburgers.css">
</head>

<body>
	<!--<div style="height: 100%;width:100%;background: url(https://pixabay.com/get/e837b90e2dfc003ed1534705fb0938c9bd22ffd41cb4114597f4c47fa3/books-1281581_1920.jpg);background-size: cover;box-sizing: unset;position: fixed;z-index: -100;top: 0;filter: grayscale(50%) blur(6px);"></div>
	-->

	<div
		style="background-color: #3e8ef7; width: 130%; height: 56px; position: fixed; top: 0;">
		<button class="hamburger hamburger--3dy" type="button" id="hamburger">
			<span class="hamburger-box"> <span class="hamburger-inner"></span>
			</span>
		</button>
		<div style="color: white; font-size: 2em; display: inline; pointer-events:none; user-select: none;">HMS</div>
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

			<li><a href='#'><span>About</span></a></li>
			<li><a href='#'><span>Contact</span></a></li>
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


	<div id="dataTable"
		class="container table_margin_while_loading panel panel-default">
		<table
			class="table table-hover table-striped shadow-lg p-3 mb-5 bg-white rounded table table-bordered">

			<thead class="thead-dark" style="user-select: none;">
				<tr>
					<th scope="col">Actions</th>
					<th scope="col">Username</th>
					<th scope="col">Authorities</th>
					<th scope="col">Enabled</th>
				</tr>

			</thead>

			<tbody id="table_body">
			</tbody>
		</table>

		<nav aria-label="Page navigation example" id="pagination-element">
		
			<ul class="pagination" style="user-select: none;" id="paginationNumbers">
				
			</ul>
		</nav>

		<button class="btn btn-primary btn-lg my-2 my-sm-0" type="button"
			data-toggle="modal" data-target="#addUserModal" data-whatever="@mdo"
			id="addUserModalButton">
			<i class="fas fa-user-plus"></i><span id="addIcon"></span> Add User
		</button>

	</div>



	<script
		src="${pageContext.request.contextPath}/resources/admin/main.js">
		
	</script>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>

</html>