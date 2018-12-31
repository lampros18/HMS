<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">




<style type="text/css">
.pt-3-half {
	padding-top: 1.4rem;
}
</style>

<title>Σύστημα διαχείρισης αιτήσεων στέγασης - Διοικητικός
	υπάλληλος</title>
</head>
<body class="jumbotron">








	<div class="jumbotron" style="padding: 5%;">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand">Employee</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">



					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/createUser">Insert
							Student</a></li>

					<li class="nav-item"><form:form
							action="${pageContext.request.contextPath}/logout" method="POST">


						</form:form></li>

				</ul>
				<form:form class="form-inline my-2 my-lg-0"
					action="${pageContext.request.contextPath}/logout" method="POST"
					style="padding-right:0.2%;">
					<button type="submit" class="btn btn-primary">logout</button>


				</form:form>
			</div>

			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>



		</nav>

		<nav class="breadcrumb">
			<a class="breadcrumb-item active"
				href="${pageContext.request.contextPath}/">/Home</a>

		</nav>

		<div id="myModal" class="modal" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Modal title</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p>Modal body text goes here.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">Save
							changes</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<button type="button" id="hiddenButton" class="btn btn-info btn-lg"
			style="display: none;" data-toggle="modal" data-target="#myModal">Open
			Modal</button>




		<!-- Editable table -->
		<div id="mainTable" class="card">
			<h3 class="card-header text-center font-weight-bold py-4">Insert
				student</h3>

			<div style="float: right; padding-top: 2%; padding-right: 1.5%">
				<button type="button" id="createNewRow"
					class="btn btn-success btn-rounded btn-sm my-0 pull-right"
					style="float: right;">Add new row</button>
			</div>
			<div class="card-body">
				<div id="table" class="table-editable">


					<table
						class="table table-bordered table-responsive-md table-striped text-center"
						style="table-layout: fixed;">
						<tr>
							<th class="text-center" style="width: 3%;">#</th>
							<th class="text-center">Email</th>
							<th class="text-center">Name</th>
							<th class="text-center">Surname</th>
							<th class="text-center">Id</th>
							<th class="text-center">Birth date</th>
							<th class="text-center">Year of enrollement</th>
							<th class="text-center">Postgradute</th>
							<th class="text-center">Department</th>
							<th class="text-center">Phone</th>
							<th class="text-center">Address</th>

						</tr>
						<tr>
							<td class="pt-3-half" contenteditable="false"></td>
							<td class="pt-3-half" contenteditable="true">30</td>
							<td class="pt-3-half" contenteditable="true">Deepends</td>
							<td class="pt-3-half" contenteditable="true">Spain</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td><span class="table-remove"><button type="button"
										class="btn btn-danger btn-rounded btn-sm my-0">Delete</button></span>
							</td>
						</tr>
						<!-- This is our clonable table line -->

						<tr>
							<td class="pt-3-half" contenteditable="false"></td>
							<td class="pt-3-half" contenteditable="true">30</td>
							<td class="pt-3-half" contenteditable="true">Deepends</td>
							<td class="pt-3-half" contenteditable="true">Spain</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td class="pt-3-half" contenteditable="true">Madrid</td>
							<td><span class="table-remove"><button type="button"
										class="btn btn-danger btn-rounded btn-sm my-0">Delete</button></span>
							</td>
						</tr>
						<!-- This is our clonable table line -->



					</table>
				</div>
			</div>
		</div>
		<!-- Editable table -->




	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/employeeForm/js/editStudent.js"></script>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


</body>
</html>

