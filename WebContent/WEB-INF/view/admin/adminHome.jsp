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


<title>Καταχώρηση διοικητικού υπαλλήλου</title>
</head>
<body>

	<div class="jumbotron">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand">Administration</a>
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
							User</a></li>

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




		</nav>

		<nav class="breadcrumb">
			<a class="breadcrumb-item" href="#">Home</a> <a
				class="breadcrumb-item" href="#">Library</a> <a
				class="breadcrumb-item" href="#">Data</a> <span
				class="breadcrumb-item active">Bootstrap</span>
		</nav>



	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


</body>
</html>

