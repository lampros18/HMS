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
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Users table</title>

<style>
.error-checkbox {
	box-shadow: 0 0 8px red;
	background-color: red;
}

.error {
	box-shadow: 0 0 8px red;
}

.loading_margin {
	margin-top: 5%;
}

.table_margin_while_loading {
	margin-top: 1%;
}

.logout_margin_while_loading {
	margin-left: 9.2%;
}

.result_container {
	margin-left: 30%;
	margin-top: 4%;
	margin-bottom: -7.5%;
	width: 50%;
}

.logout_margin_after_load {
	margin-left: 9%;
	margin-top: 6%;
	margin-bottom: -7%;
}

.table_margin_after_load {
	margin-top: 8%;
}

.dark-theme {
	background-color: #212529;
	color: #fff;
}

#addUserModalButton {
	float: right;
}

#bottom-container {
	margin-top: -3%;
	margin-bottom: 2%;
	margin-left: 1.5%;
}

#pagination-element {
	margin-left: -3%;
}

body {
	background: #8e9eab; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #eef2f3, #8e9eab);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #eef2f3, #8e9eab);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>

</head>

<body>
	<div class="container">

		<nav class="navbar fixed-top dark-theme" style="float: left;">
			<a class="navbar-brand">Admin Homepage</a>
			<form class="form-inline" style="float: left; padding-right: 0%;">
				<input id="searchText" class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button id="searchButton" class="btn btn-outline-light my-2 my-sm-0" type="button">Search</button>
			</form>
		</nav>
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

	<div id="logout-form" class="logout_margin_while_loading">
		<form:form class="form-inline"
			action="${pageContext.request.contextPath}/logout" method="POST">
			<button type="submit" class="btn btn-danger btn-sm">
				<i class="fas fa-sign-out-alt"></i> Logout
			</button>
		</form:form>
	</div>

	<div id="result">
		<button type="button" class="close" onclick="closeResult();"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>

	</div>

	<div id="dataTable" class="container table_margin_while_loading">
		<table
			class="table table-hover table-striped shadow-lg p-3 mb-5 bg-white rounded">

			<thead class="thead-dark">
				<tr>
					<th scope="col">Actions</th>
					<th scope="col">Email</th>
					<th scope="col">Password</th>
					<th scope="col">Authorities</th>
					<th scope="col">Enabled</th>
				</tr>

			</thead>

			<tbody id="table_body">
			</tbody>
		</table>
		<div class="container" id="bottom-container">
			<button class="btn btn-primary btn-lg my-2 my-sm-0" type="button"
				data-toggle="modal" data-target="#addUserModal" data-whatever="@mdo"
				id="addUserModalButton">
				<i class="fas fa-user-plus"></i><span id="addIcon"></span> Add User
			</button>
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="pagination-element">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>



	<script src="${pageContext.request.contextPath}/resources/admin/main.js">
	</script>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>

</html>