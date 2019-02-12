		function init() {
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("table_body").innerHTML = "";
					loadTableAndData(xhttp.responseText);
					removeLoading();

				}
			};

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			xhttp.open('POST', 'editUsers', true);
			xhttp.setRequestHeader('Content-Type',
					'application/json;charset=UTF-8');
			xhttp.setRequestHeader(header, token);
			xhttp.send({});

		}
		$(document).ready(function() {
			init();
			
			$('#searchField').on('change paste keyup',function(){
				
				let trs=document.getElementsByTagName('tr');
				
				let textContent=this.value;
				
				let regex=new RegExp('^'+textContent,'g');
				
				for(let i=1;i<trs.length;i++){
					
					
					if(textContent.trim()==''){
						
						trs[i].removeAttribute('style');
						continue;
					}
					
					if(trs[i].getElementsByTagName('td')[1]
							.getElementsByTagName('div')[0].textContent.toLocaleLowerCase().
							match(regex)==null){
						
						trs[i].style['display']='none';
						
//						console.log(textContent, trs[i].getElementsByTagName('td')[1]
//								.getElementsByTagName('div')[0].textContent);
//						
					}else{
						
						trs[i].removeAttribute('style');
					}
				}
			})
		});

		function removeLoading() {
			if(document.getElementById("loading") != null)
				document.getElementById("loading").outerHTML = "";
			document.getElementById("dataTable").setAttribute("class",
					"container table_margin_after_load");
		}

		function loadTableAndData(response) {
			let json = JSON.parse(response);
			let tableBody = document.getElementById("table_body");
			for (let i = 0; i < json.users.length; i++) {
				createTableRow(json.users[i].id, tableBody);
				createActionsRow(json.users[i].id, tableBody);
				createEmailColumn(json.users[i].email, json.users[i].id);
				if (json.users[i].student == 1)
					createStudentAuthoritiesColumn(json.users[i].student, json.users[i].id);
				else
					createEmployeeAuthoritiesColumn(json.users[i].admin,
							json.users[i].foreman, json.users[i].employee, json.users[i].id);
				createEnabledColumn(json.users[i].enabled, json.users[i].id);
			}

			function createTableRow(id, tableBody) {
				let tableRow = document.createElement("TR");
				tableRow.setAttribute("id", id);
				tableBody.appendChild(tableRow);
			}

			function createActionsRow(id, tableBody) {
				let tableRow = document.getElementById(id);

				let td = document.createElement("TD");

				let div = document.createElement("DIV");
				div.setAttribute("class", "container")

				let iDeleteUser = document.createElement("I");
				iDeleteUser.setAttribute("class", "fas fa-user-minus");
				iDeleteUser.setAttribute("style",  "cursor:pointer;");
				iDeleteUser.setAttribute("onclick", "deleteUser("+id+");");

				let iUpdatePassword = document.createElement("I");
				iUpdatePassword.setAttribute("class", "fas fa-key favicon_margin");
				iUpdatePassword.setAttribute("style",  "cursor:pointer;");
				
				div.appendChild(iDeleteUser);
				div.appendChild(iUpdatePassword);
				td.appendChild(div);
				tableRow.appendChild(td);
				tableBody.appendChild(tableRow);
			}

			function createEmailColumn(email, id) {
				let tableRow = document.getElementById(id);

				let td = document.createElement("TD");
				let div = document.createElement("DIV");
				div.setAttribute("contenteditable", "");
				div.setAttribute("onblur", "editEmail("+ id + ");"); 
				let textNode = document.createTextNode(email);

				div.appendChild(textNode);
				td.appendChild(div);
				tableRow.appendChild(td);

			}


			function createEmployeeAuthoritiesColumn(admin, foreman, employee,
					id) {
				let tableRow = document.getElementById(id);

				let td = document.createElement("TD");

				//Creating the custom swtch for the admin authority
				let admin_div = document.createElement("SPAN");
				admin_div.setAttribute("class", "custom-switch");
				
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
				let foreman_div = document.createElement("SPAN");
				foreman_div.setAttribute("class",
						"custom-switch switch_input_span");

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
				let employee_div = document.createElement("SPAN");
				employee_div.setAttribute("class",
						" custom-switch switch_input_span");

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
				let tableRow = document.getElementById(id);

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
				let tableRow = document.getElementById(id);

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

		function createPassword() {
			document.getElementById("employee_password").value = Math.random()
					.toString(36).substring(2).replace("l", "(").replace("I",
							")").replace("1", "m");
		}

		document.getElementById('reload-pass').addEventListener('click',
				function() {
					createPassword()
				});

		document
				.getElementById("type_employee")
				.addEventListener(
						"click",
						function() {
							if (document
									.getElementById("employee_authority_admin").disabled == true)
								document
										.getElementById("employee_authority_admin").disabled = false;
							if (document
									.getElementById("employee_authority_foreman").disabled == true)
								document
										.getElementById("employee_authority_foreman").disabled = false;
							if (document
									.getElementById("employee_authority_employee").disabled == true)
								document
										.getElementById("employee_authority_employee").disabled = false;
						});

		document
				.getElementById("type_student")
				.addEventListener(
						"click",
						function() {
							document.getElementById("employee_authority_admin").disabled = true;
							document
									.getElementById("employee_authority_foreman").disabled = true;
							document
									.getElementById("employee_authority_employee").disabled = true;
							if (document
									.getElementById("employee_authority_admin").checked)
								document
										.getElementById("employee_authority_admin").checked = false;
							if (document
									.getElementById("employee_authority_foreman").checked)
								document
										.getElementById("employee_authority_foreman").checked = false;
							if (document
									.getElementById("employee_authority_employee").checked)
								document
										.getElementById("employee_authority_employee").checked = false;
						});

		$('#addUserModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			var recipient = button.data('whatever') // Extract info from data-* attributes
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			createPassword();
			/*
			var modal = $(this)
			modal.find('.modal-title').text('New message to ' + recipient)
			modal.find('.modal-body input').val(recipient)*/
		})

		document
				.getElementById("submit-btn")
				.addEventListener(
						"click",
						function() {
							document.getElementById("employee_email")
									.setAttribute("class", "form-control");
							document.getElementById("employee_password")
									.setAttribute("class", "form-control");
							document.getElementById("type_employee")
									.setAttribute("class", "has-val");
							document.getElementById("type_student")
									.setAttribute("class", "has-val");
							document.getElementById(
									"employee_authority_foreman").setAttribute(
									"class", "has-val");
							document.getElementById("employee_authority_admin")
									.setAttribute("class", "has-val");
							document.getElementById(
									"employee_authority_employee")
									.setAttribute("class", "has-val");

							if (document.getElementById("employee_email").value
									.trim() == "") {
								document.getElementById("employee_email")
										.setAttribute("class",
												"form-control error");
								document.getElementById("exampleModalLabel")
										.scrollIntoView();
								return;
							}

							if (document.getElementById("employee_password").value
									.trim() == "") {
								document.getElementById("employee_password")
										.setAttribute("class",
												"form-control error");
								document.getElementById("exampleModalLabel")
										.scrollIntoView();
								return;
							}

							if (document.getElementById("type_employee").checked) {
								if (!document
										.getElementById("employee_authority_foreman").checked
										&& !document
												.getElementById("employee_authority_admin").checked
										&& !document
												.getElementById("employee_authority_employee").checked) {
									document.getElementById(
											"employee_authority_foreman")
											.setAttribute("class",
													"error-checkbox has-val");
									document.getElementById(
											"employee_authority_admin")
											.setAttribute("class",
													"error-checkbox has-val");
									document.getElementById(
											"employee_authority_employee")
											.setAttribute("class",
													"error-checkbox has-val");
									return;
								}
							}

							if (!document.getElementById("type_employee").checked) {
								if (!document.getElementById("type_student").checked) {
									document.getElementById("type_employee")
											.setAttribute("class",
													"has-val error-checkbox");
									document.getElementById("type_student")
											.setAttribute("class",
													"has-val error-checkbox");
									return;
								}
							}

							$('#addUserModal').modal('hide');
							var xhttp = new XMLHttpRequest();

							xhttp.onreadystatechange = function() {
								if (this.readyState == 4 && this.status == 200) {
									// Typical action to be performed when the document is ready:
									//docufment.getElementById("demo").innerHTML = xhttp.responseText;
									setResultMessage(xhttp.responseText);
									init();
								}
							};

							var token = $("meta[name='_csrf']").attr("content");
							var header = $("meta[name='_csrf_header']").attr(
									"content");

							xhttp.open('POST', 'createUser',
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
						});

		function setResultMessage(response) {
			let div = document.getElementById("result");
			div.style.visibility = "visible";
			document.getElementById("close_button").style.visibility = "visible";
			let json = JSON.parse(response);
			closeResult();
			let span = document.createElement("SPAN");
			span.setAttribute("id", "text-node");
			let txtNode = document.createTextNode(json.result);
			span.appendChild(txtNode);
			if (json.status == "500") {
				div.setAttribute("role", "alert");
				div
						.setAttribute("class",
								"result_container alert alert-danger alert-dismissible fade show");
			}
			if (json.status == "200") {
				div.setAttribute("role", "alert");
				div
						.setAttribute("class",
								"result_container alert alert-primary alert-dismissible fade show");
				resetForm();
			}
			div.appendChild(span);
			window.scroll(0, 0);
		}

		function resetForm() {
			document.getElementById("employee_email").value = "";
			createPassword();
			document.getElementById("type_employee").checked = false;
			document.getElementById("type_student").checked = false;
			document.getElementById("employee_authority_admin").checked = false;
			document.getElementById("employee_authority_foreman").checked = false;
			document.getElementById("employee_authority_employee").checked = false;
			document.getElementById("employee_enabled").checked = false;
		}

		function closeResult() {
			let div = document.getElementById("result");
			let span = document.getElementById("text-node");
			if (span != null)
				span.parentNode.removeChild(span);
			if (div != null) {
				div.removeAttribute("class");
				div.removeAttribute("role");
			}
		}
		
		function hideButton(){
			document.getElementById("close_button").style.visibility = "hidden";
		}
		
		function deleteUser(uid){
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var json = JSON.parse(xhttp.responseText);
					if(json.status == 200)
						document.getElementById(uid).parentNode.removeChild(document.getElementById(uid));
					setResultMessage(xhttp.responseText);
				}
			};

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			xhttp.open('POST', 'deleteUser', true);
			xhttp.setRequestHeader('Content-Type',
					'application/json;charset=UTF-8');
			xhttp.setRequestHeader(header, token);
			xhttp.send(JSON.stringify(
			{
				id:uid
			}		
			));
		}
		
		function searchUser(keyword){
			var results = 0;
		}
		
		function editEmail(uid){
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					console.log("edited");
				}
			};

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			xhttp.open('POST', 'deleteUser', true);
			xhttp.setRequestHeader('Content-Type',
					'application/json;charset=UTF-8');
			xhttp.setRequestHeader(header, token);
			xhttp.send(JSON.stringify(
			{
				id:uid
			}		
			));
		}