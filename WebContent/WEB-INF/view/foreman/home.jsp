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

<title>Foreman home</title>

</head>

<body>

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


	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Set
				system variables</li>
		</ol>
	</nav>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="${pageContext.request.contextPath}/">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Set
				system variables</li>
		</ol>
	</nav>

	<div class="container">

		<nav class="navbar fixed-top dark-theme" style="float: left;">
			<a class="navbar-brand">Foreman Homepage</a>

			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/">Home <span
						class="sr-only">(current)</span>
				</a></li>


			</ul>

			<form:form id="command" class="form-inline"
				action="${pageContext.request.contextPath}/logout" method="POST">
				<button type="submit" class="btn btn-danger btn-sm">
					<i class="fas fa-sign-out-alt"></i> Logout
				</button>
				<div></div>
			</form:form>


		</nav>
	</div>


	<div class="container">

		<nav class="navbar fixed-top dark-theme" style="float: left;">
			<a class="navbar-brand">Foreman Homepage</a>

			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/">Home <span
						class="sr-only">(current)</span>
				</a></li>


			</ul>

			<form:form id="command" class="form-inline"
				action="${pageContext.request.contextPath}/logout" method="POST">
				<button type="submit" class="btn btn-danger btn-sm">
					<i class="fas fa-sign-out-alt"></i> Logout
				</button>
				<div></div>
			</form:form>


		</nav>
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


			<button type="submit" class="btn btn-primary" style="margin-top: 5%"
				onclick="return false">Update system</button>
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