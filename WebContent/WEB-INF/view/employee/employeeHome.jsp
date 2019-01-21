<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<!-- Χεράτο  css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/employee/css/employee/employeeHome.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />

<title>Employee home page</title>

</head>

<body>
	<div class="container">

		<nav class="navbar fixed-top dark-theme" style="float: left;">
			<a class="navbar-brand">Employee Homepage</a>
			<!--<form class="form-inline" style="float: left; padding-right: 0%;">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-light my-2 my-sm-0" type="button">Search</button>
			</form> -->
		</nav>
	</div>


	<div class=container style="padding-top: 5%;" id="logout-form"
		class="logout_margin_after_load"></div>

	<div id="confirm" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Caution</h5>
					
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="return false">Delete</button>
					<button type="button" class="btn btn-primary"
						data-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="saveError" class="modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Error Occured</h5>
					
				</div>
				<div class="modal-body">
					<p>Some fields are not filled correctly please fix
					the errors.</p>
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Path navigation -->
	<ol class="breadcrumb">
		<li class="breadcrumb-item active"><a
			href="${pageContext.request.contextPath}/"> Home </a></li>
	</ol>



	<div class=container id="logout-form"
		class="logout_margin_while_loading">
		<form:form class="form-inline"
			action="${pageContext.request.contextPath}/logout" method="POST">
			<button type="submit" class="btn btn-danger btn-sm">
				<i class="fas fa-sign-out-alt"></i> Logout
			</button>
		</form:form>
	</div>


	<div id="dataTable" class="container table_margin_while_loading">
		<table
			class="table table-hover table-bordered table-striped shadow-lg p-3 mb-5 bg-white rounded">

			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Email</th>
					<th scope="col">Name</th>
					<th scope="col">Surname</th>
					<th scope="col">Birthdate</th>
					<th scope="col">Year of enrollment</th>
					<th scope="col">Postgradute</th>
					<th scope="col">Department</th>
					<th scope="col">Phone</th>
					<th scope="col">Address</th>
				</tr>

			</thead>

			<tbody id="table_body">
			</tbody>
		</table>
		<div class="container" id="bottom-container">
			<button class="btn btn-primary btn-lg my-2 my-sm-0" type="button"
				data-toggle="modal" data-whatever="@mdo" id="addUserModalButton"
				onclick="return false;">
				<i class="fas fa-user-plus"></i><span id="addIcon"></span> Add User
			</button>
			<nav aria-label="Page navigation example"></nav>
		</div>
	</div>


	<script type="text/javascript"
		src="${ pageContext.request.contextPath}/resources/employee/js/editStudent.js"></script>

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