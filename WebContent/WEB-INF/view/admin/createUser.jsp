<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script
	src="${pageContext.request.contextPath}/resources/employeeForm/jQuery/jquery-3.3.1.min.js"></script>
<title>Insert Employee</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/employeeForm/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/employeeForm/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<h5>Admin home page</h5>

	<div>
		<!-- Υποστήριξη logout -->
		<form:form action="${pageContext.request.contextPath}/logout"
			style="float:left; padding-right:0.3%;" method="POST">
			<input type="submit" value="logout" />
			<br />
		</form:form>

		<!-- Ο HomeController διακρίνει τους χρήστες ανάλογα με το ρόλο
			τους και μας γυρνάει στην αρχική σελίδα του διαχειριστή -->
		<!-- Αξιοθρήνητη προσπάθεια για αισθητική -->
		<form:form action="${pageContext.request.contextPath}/"
			style="float:left float:left; padding-right:0.3%;" method="GET">
			<input type="submit" value="Κεντρική σελίδα" />
			<br />
		</form:form>


	</div>
	<div class="container-contact100">
		<div class="wrap-contact100">
			<form:form class="contact100-form validate-form"
				action="${pageContext.request.contextPath}/admin/createEmployee"
				accept-charset="UTF-8">
				<span class="contact100-form-title"> Insert User </span>


				<div class="wrap-input100 validate-input"
					data-validate="Valid email is required: example@hua.gr">
					<span class="label-input100">Email</span> <input class="input100"
						type="text" name="email" id="employee_email"
						placeholder="Enter an email addess"> <span
						class="focus-input100"></span>
				</div>


					<div class="wrap-input100 validate-input"
					data-validate="Valid email is required: example@hua.gr">
					<span class="label-input100">Password</span> <input class="input100"
						type="text" name="password" id="employee_password"
						placeholder="Enter an email addess"> <span
						class="focus-input100"></span>
				</div>


					<div class="wrap-input100 input100-select">
						<div>
							<span class="label-input100">User type</span> <br /> <input
								type="radio" name="user_type"
								id="type_employee" class="has-val" /> <label
								for="type_employee" class="label-input100">Employee</label>
							<br/> <input type="radio" name="user_type"
								id="type_student" class="has-val" /> <label
								for="type_student" class="label-input100">Student</label>
							<span class="focus-input100"></span>
					</div>
				</div>



				<div class="wrap-input100 input100-select">
					<div>
						<span class="label-input100">Authorities</span> <br /> <input
							type="checkbox" name="employee_authority_admin"
							id="employee_authority_admin" class="has-val" /> <label
							for="employee_authority_admin" class="label-input100">Admin</label>
						<br /> <input type="checkbox" name="employee_authority_foreman"
							id="employee_authority_foreman" class="has-val" /> <label
							for="employee_authority_foreman" class="label-input100">Foreman</label>
						<br /> <input type="checkbox" name="employee_authority_employee"
							id="employee_authority_employee" class="has-val" /> <label
							for="employee_authority_employee" class="label-input100">Employee</label>
						<span class="focus-input100"></span>
					</div>
				</div>
				<div class="wrap-input100 input100-select">
					<span class="label-input100">Enabled</span> <input type="checkbox"
						name="enabled" id="employee_enabled"> <span
						class="focus-input100"></span>
				</div>

				<div class="container-contact100-form-btn">
					<div class="wrap-contact100-form-btn">
						<div class="contact100-form-bgbtn"></div>
						<button class="contact100-form-btn" id="employee_submit"
							type="button" onclick="event.preventDefault()">
							<span> Αποθήκευση διοικητικού υπαλλήλου <i
								class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
							</span>
						</button>
					</div>
				</div>

			</form:form>
		</div>
	</div>


	<div id="dropDownSelect1"></div>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/select2/select2.min.js"></script>
	<script>
		$(".selection-2").select2({
			minimumResultsForSearch : 20,
			dropdownParent : $('#dropDownSelect1')
		});
	</script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/employeeForm/js/main.js"></script>

	<script>
		$(document).ready(
				function() {
					$("#employee_password").val(
							Math.random().toString(36).substring(2).replace(
									"l", "(").replace("I", ")").replace("1",
									"m"));
				});
		document.getElementById("type_employee").addEventListener("click", function(){
				if(document.getElementById("employee_authority_admin").disabled == true)
					document.getElementById("employee_authority_admin").disabled = false;
				if(document.getElementById("employee_authority_foreman").disabled == true)
					document.getElementById("employee_authority_foreman").disabled = false;
				if(document.getElementById("employee_authority_employee").disabled == true)
					document.getElementById("employee_authority_employee").disabled = false;
		});
		
		document.getElementById("type_student").addEventListener("click", function(){
				document.getElementById("employee_authority_admin").disabled = true;
				document.getElementById("employee_authority_foreman").disabled = true;
				document.getElementById("employee_authority_employee").disabled = true;
				if(document.getElementById("employee_authority_admin").checked)
					document.getElementById("employee_authority_admin").checked = false;
				if(document.getElementById("employee_authority_foreman").checked)
					document.getElementById("employee_authority_foreman").checked = false;
				if(document.getElementById("employee_authority_employee").checked)
					document.getElementById("employee_authority_employee").checked = false;
		});
	
	</script>

	<script>
		document
				.getElementById("employee_submit")
				.addEventListener(
						"click",
						function() {
							var xhttp = new XMLHttpRequest();

							xhttp.onreadystatechange = function() {
								if (this.readyState == 4 && this.status == 200) {
									// Typical action to be performed when the document is ready:
									//document.getElementById("demo").innerHTML = xhttp.responseText;
									console.log(xhttp.responseText);
								}
							};

							var token = $("meta[name='_csrf']").attr("content");
							var header = $("meta[name='_csrf_header']").attr(
									"content");

							xhttp.open('POST',
									'<c:url value="/admin/createUser" />',
									true);
							xhttp.setRequestHeader('Content-Type',
									'application/json;charset=UTF-8');
							xhttp.setRequestHeader(header, token);
							xhttp
									.send(JSON
											.stringify({
												email : document
														.getElementById("employee_email").value,
												password : document
														.getElementById("employee_password").value,
												authority_admin : document
														.getElementById("employee_authority_admin").checked == true ? '1'
														: '0',
												authority_foreman : document
														.getElementById("employee_authority_foreman").checked == true ? '1'
														: '0',
												authority_employee : document
														.getElementById("employee_authority_employee").checked == true ? '1'
														: '0',
														authority_student : document
														.getElementById("type_student").checked == true ? '1'
														: '0',
												enabled : document
														.getElementById("employee_enabled").checked == true ? '1'
														: '0'
											}));
							return false;
						});
	</script>

</body>
</html>