<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!doctype html>
<html lang="en">

<head>

<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
@import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300);
.container {
	margin: 0 auto;
}

#loginbox {
	margin: 0 auto;
	width: 40%;
	min-width: 280px;
}

html{
	height:100%;
}

body {
  font-family:'Roboto Condensed';
  background-image: url(https://www.pixelstalk.net/wp-content/uploads/2016/10/Free-Wallpapers-Anime-Landscape.jpg);
  height: 100%; 
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>



</head>

<body class="jumbotron">

	<div class="container">

		<div id="loginbox">

			<div class="panel panel-info body-style" style="box-shadow: 12px 12px 130px #000;">

				<div class="panel-heading" style="color: #fff;background-color: #0B69E3;border-color: #0B69E3">
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
									
									<c:if test="${param.logout != null}">

										<i id="info2"
											class="alert alert-success col-xs-offset-1 col-xs-10">You
											have been disconnected</i>

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
							<div style="text-align:center">
								<button style="text-align:center;padding: 8px 16px 8px 16px;background-color:#0B69E3" type="submit" class="btn btn-primary " onclick="return false;">Login</button>


							</div>
						</div>

					</form:form>

				</div>

			</div>

		</div>

	</div>
	
	<form:form action="${pageContext.request.contextPath}/authUser"  id="hiddenForm" style="display:none;">
		method="POST">
		<!-- Λάθος στοιχεία -->
		<c:if test="${param.error != null}">
			<i>Λάθος Username ή password</i>
		</c:if>

		<!-- Έλεγχος για Logout -->
		<c:if test="${param.logout!=null}">

			<i> Έχεις αποσυνδεθεί επιτυχώς</i>

		</c:if>

		<div class="field">
			<label>User Name</label> <input type="text" name="username" />
		</div>
		<div class="field">
			<label>Password</label> <input type="password" name="password" />
		</div>
		<div class="field">
			<input type="submit" value="Login" />
		</div>
	</form:form>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/login/login.js"></script>

</body>


</html>