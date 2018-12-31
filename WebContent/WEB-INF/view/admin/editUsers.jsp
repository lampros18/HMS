<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>Users table</title>

<style>

.loading_margin{
	margin-top: 2%;
}

.table_margin {
	margin-top: 3%;
}
body{
background: #8e9eab;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #eef2f3, #8e9eab);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #eef2f3, #8e9eab); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

</style>

</head>

<body>
	<div id="loading" class="loading_margin">
		<div class="text-center">
			<button class="btn btn-primary" type="button" disabled>
				<span class="spinner-border spinner-border-sm" role="status"
					aria-hidden="true"></span> Loading...
			</button>
		</div>
	</div>

	<div class="container table_margin">
		<table class="table table-hover table-striped shadow-lg p-3 mb-5 bg-white rounded">

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
	</div>



	<script>
		$(document)
				.ready(
						function() {
							var xhttp = new XMLHttpRequest();

							xhttp.onreadystatechange = function() {
								if (this.readyState == 4 && this.status == 200) {

									loadTableAndData(xhttp.responseText);
									removeLoading();

								}
							};

							var token = $("meta[name='_csrf']").attr("content");
							var header = $("meta[name='_csrf_header']").attr(
									"content");

							xhttp.open('POST',
									'<c:url value="/admin/editUsers" />', true);
							xhttp.setRequestHeader('Content-Type',
									'application/json;charset=UTF-8');
							xhttp.setRequestHeader(header, token);
							xhttp.send({});
						});

		
		function removeLoading(){
			document.getElementById("loading").outerHTML = "";;
		}
		
		function loadTableAndData(response) {
			let json = JSON.parse(response);
			let tableBody = document.getElementById("table_body");
			for (let i = 0; i < json.users.length; i++) {
				createTableRow(i, tableBody);
				createActionsRow(i, tableBody);
				createEmailColumn(json.users[i].email, i);
				createPasswordColumn(json.users[i].password, i);
				if (json.users[i].student == 1)
					createStudentAuthoritiesColumn(json.users[i].student, i);
				else
					createEmployeeAuthoritiesColumn(json.users[i].admin,
							json.users[i].foreman, json.users[i].employee, i);
				createEnabledColumn(json.users[i].enabled, i);
			}

			function createTableRow(id, tableBody) {
				let tableRow = document.createElement("TR");
				tableRow.setAttribute("id", "tr" + id);
				tableBody.appendChild(tableRow);
			}
			
			function createActionsRow(id, tableBody) {
				let tableRow = document.getElementById("tr"+id);

				
				let td = document.createElement("TD");
				
				let div = document.createElement("DIV");
				div.setAttribute("class", "container")
				
				let iDeleteUser = document.createElement("I");
				iDeleteUser.setAttribute("class","fas fa-user-minus");
				
				let iUpdateUser = document.createElement("I");
				iUpdateUser.setAttribute("class","fas fa-user-edit");
				/*div.setAttribute("class","btn-group");
				div.setAttribute("role","group");
				div.setAttribute("aria-label","actions buttons");*/
				
				/*let updateButton = document.createElement("BUTTON");
				updateButton.setAttribute("type", "button");
				updateButton.setAttribute("class", "btn btn-warning");
				updateButton.setAttribute("type", "button");
				
				let deleteButton = document.createElement("BUTTON");
				deleteButton.setAttribute("type", "button");
				deleteButton.setAttribute("class", "btn btn-danger");
				deleteButton.setAttribute("type", "button");
				
				let updateButtonTxtNode = document.createTextNode("Update");
				let deleteButtonTxtNode = document.createTextNode("Delete");
				
				updateButton.appendChild(updateButtonTxtNode);
				deleteButton.appendChild(deleteButtonTxtNode);
				div.appendChild(updateButton);
				div.appendChild(deleteButton);*/
				div.appendChild(iDeleteUser);
				div.appendChild(document.createElement("BR"));
				div.appendChild(document.createElement("BR"));
				div.appendChild(iUpdateUser);
				td.appendChild(div);
				tableRow.appendChild(td);
				tableBody.appendChild(tableRow);
			}


			function createEmailColumn(email, id) {
				let tableRow = document.getElementById(("tr" + id));

				let td = document.createElement("TD");
				let div = document.createElement("DIV");
				div.setAttribute("contenteditable", "");
				let textNode = document.createTextNode(email);

				div.appendChild(textNode);
				td.appendChild(div);
				tableRow.appendChild(td);

			}

			function createPasswordColumn(password, id) {
				let tableRow = document.getElementById(("tr" + id));

				let td = document.createElement("TD");
				let div = document.createElement("DIV");
				div.setAttribute("contenteditable", "");
				let textNode = document.createTextNode(password);

				div.appendChild(textNode);
				td.appendChild(div);
				tableRow.appendChild(td);

			}

			function createEmployeeAuthoritiesColumn(admin, foreman, employee,
					id) {
				let tableRow = document.getElementById(("tr" + id));

				let td = document.createElement("TD");

				//Creating the custom swtch for the admin authority
				let admin_div = document.createElement("DIV");
				admin_div.setAttribute("class", "custom-control custom-switch");

				let admin_input = document.createElement("INPUT");
				admin_input.setAttribute("type", "checkbox");
				admin_input.setAttribute("class", "custom-control-input");
				admin_input.setAttribute("id", "admin_switch" + id);
				if (admin == 1)
					admin_input.setAttribute("checked", "");

				let admin_label = document.createElement("LABEL");
				admin_label.setAttribute("class", "custom-control-label");
				admin_label.setAttribute("for", "admin_switch" + id);

				let textNode = document.createTextNode("Admin");

				admin_label.appendChild(textNode);

				admin_div.appendChild(admin_input);
				admin_div.appendChild(admin_label);

				//Creating the custom switch for the foreman authority
				let foreman_div = document.createElement("DIV");
				foreman_div.setAttribute("class",
						"custom-control custom-switch");

				let foreman_input = document.createElement("INPUT");
				foreman_input.setAttribute("type", "checkbox");
				foreman_input.setAttribute("class", "custom-control-input");
				foreman_input.setAttribute("id", "foreman_switch" + id);
				if (foreman == 1)
					foreman_input.setAttribute("checked", "");

				let foreman_label = document.createElement("LABEL");
				foreman_label.setAttribute("class", "custom-control-label");
				foreman_label.setAttribute("for", "foreman_switch" + id);

				let foremanTextNode = document.createTextNode("Foreman");

				foreman_label.appendChild(foremanTextNode);

				foreman_div.appendChild(foreman_input);
				foreman_div.appendChild(foreman_label);

				//Creating the custom switch for the employee authority
				let employee_div = document.createElement("DIV");
				employee_div.setAttribute("class",
						"custom-control custom-switch");

				let employee_input = document.createElement("INPUT");
				employee_input.setAttribute("type", "checkbox");
				employee_input.setAttribute("class", "custom-control-input");
				employee_input.setAttribute("id", "employee_switch" + id);
				if (employee == 1)
					employee_input.setAttribute("checked", "");

				let employee_label = document.createElement("LABEL");
				employee_label.setAttribute("class", "custom-control-label");
				employee_label.setAttribute("for", "employee_switch" + id);

				let employeeTextNode = document.createTextNode("Employee");

				employee_label.appendChild(employeeTextNode);

				employee_div.appendChild(employee_input);
				employee_div.appendChild(employee_label);

				td.appendChild(admin_div);
				td.appendChild(foreman_div);
				td.appendChild(employee_div);
				tableRow.appendChild(td);

			}

			function createStudentAuthoritiesColumn(student, id) {
				let tableRow = document.getElementById(("tr" + id));

				let td = document.createElement("TD");

				//Creating the custom swtch for the student authority
				let div = document.createElement("DIV");
				div.setAttribute("class", "custom-control custom-switch");

				let input = document.createElement("INPUT");
				input.setAttribute("type", "checkbox");
				input.setAttribute("class", "custom-control-input");
				input.setAttribute("id", "student_switch" + id);
				input.setAttribute("checked", "");

				let label = document.createElement("LABEL");
				label.setAttribute("class", "custom-control-label");
				label.setAttribute("for", "student_switch" + id);

				let textNode = document.createTextNode("Student");

				label.appendChild(textNode);

				div.appendChild(input);
				div.appendChild(label);

				td.appendChild(div);
				tableRow.appendChild(td);

			}

			function createEnabledColumn(enabled, id) {
				let tableRow = document.getElementById(("tr" + id));

				let td = document.createElement("TD");

				//Creating the custom swtch for the student authority
				let div = document.createElement("DIV");
				div.setAttribute("class", "custom-control custom-switch");

				let input = document.createElement("INPUT");
				input.setAttribute("type", "checkbox");
				input.setAttribute("class", "custom-control-input");
				input.setAttribute("id", "enabled_switch" + id);
				if (enabled == 1)
					input.setAttribute("checked", "");

				let label = document.createElement("LABEL");
				label.setAttribute("class", "custom-control-label");
				label.setAttribute("for", "enabled_switch" + id);

				let textNode = document.createTextNode("Enabled");

				label.appendChild(textNode);

				div.appendChild(input);
				div.appendChild(label);

				td.appendChild(div);
				tableRow.appendChild(td);
			}

		}
	</script>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>

</html>
