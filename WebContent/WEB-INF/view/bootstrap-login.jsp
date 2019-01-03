<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>

.container{
	margin:0 auto;
}

#loginbox{
	margin: 0 auto;
	width:40%;
	min-width:280px;
}

body{
	background: #8e9eab; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #eef2f3, #8e9eab);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #eef2f3, #8e9eab);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}


</style>



</head>

<body class="jumbotron">

	<div class="container">

		<div id="loginbox">

			<div class="panel panel-info body-style">

				<div class="panel-heading">
					<div class="panel-title">Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body body-style">

					<!-- Login Form -->
					<form:form action="${pageContext.request.contextPath}/authUser"
						method="POST" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group body-style">
							<div class="col-xs-15">
								<div>

									<!-- Check for login error -->

									<c:if test="${param.error != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											Invalid username or password.</div>

									</c:if>
									<!-- Έλεγχος για Logout -->
									<c:if test="${param.logout != null}">

										<i id="info2"
											class="alert alert-success col-xs-offset-1 col-xs-10">You
											have disconnected</i>

									</c:if>

									<!--		            
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out.
									</div>
								    -->

								</div>
							</div>
						</div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input type="text"
								name="username" placeholder="username" class="form-control">
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input type="password"
								name="password" placeholder="password" class="form-control">
						</div>

						<!-- Login/Submit Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary ">Login</button>


							</div>
						</div>

					</form:form>

				</div>

			</div>

		</div>

	</div>

</body>
</html>