		function init() {
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("table_body").innerHTML = "";
					loadTableAndData(xhttp.responseText);
					removeLoading();
					$('[data-toggle="popover"]').popover();
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
		var searchOpen = false;
		var hamburgerOpen = false;
		$(document).ready(function() {
			
			init();
			
			
			$('#cssmenu li.has-sub>a').on('click', function(){
				$(this).removeAttr('href');
				var element = $(this).parent('li');
				if (element.hasClass('open')) {
					element.removeClass('open');
					element.find('li').removeClass('open');
					element.find('ul').slideUp();
				}
				else {
					element.addClass('open');
					element.children('ul').slideDown();
					element.siblings('li').children('ul').slideUp();
					element.siblings('li').removeClass('open');
					element.siblings('li').find('li').removeClass('open');
					element.siblings('li').find('ul').slideUp();
				}
			});
			
			/*document
			.getElementById("searchBtn")
			.addEventListener(
					"click",
					function(){
						searchOpen = !searchOpen;
						/*if(searchOpen){
							$("#searchField").removeClass("close");
							$("#searchField").addClass("open");
						}
						else{
							$("#searchField").removeClass("open");
							$("#searchField").addClass("close");
						}*/
					/*});*/
			
			document
			.getElementById("closeSearch")
			.addEventListener(
					"click",
					function(){
						//searchOpen = !searchOpen;
						searchOpen = false;
						document.getElementById("searchField").style.top = "-56px";
						document.getElementById("closeSearch").style.opacity = "0";
						document.getElementById("closeSearch").style.pointerEvents = "none";
						document.getElementById("openSearch").style.pointerEvents = "all";
						document.getElementById("openSearch").style.opacity = "1";
					});
			
			document
			.getElementById("openSearch")
			.addEventListener(
					"click",
					function(){
						//searchOpen = !searchOpen;
						searchOpen = true;
						document.getElementById("searchField").style.top = "0px";
						document.getElementById("closeSearch").style.opacity = "1";
						document.getElementById("closeSearch").style.pointerEvents = "all";
						document.getElementById("openSearch").style.pointerEvents = "none";
						document.getElementById("openSearch").style.opacity = "0";
					});
			document.getElementById("closeSearch").click();
			document
			.getElementById("hamburger")
			.addEventListener(
					"click",
					function(){
						hamburgerOpen = !hamburgerOpen;
						if(hamburgerOpen){
							$("#hamburger").addClass("is-active");
							document.getElementById("cssmenu").style.marginLeft = "0px";
						}
						else{
							$("#hamburger").removeClass("is-active");
							document.getElementById("cssmenu").style.marginLeft = "-250px";
						}
					});
			
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
											
					}else{
						
						trs[i].removeAttribute('style');
					}
				}
			});
			
			$('body').on('click', function (e) {
			    //did not click a popover toggle or popover
			    if ($(e.target).data('toggle') !== 'popover'
			        && $(e.target).parents('.popover.in').length === 0
			        && $(e.target).data('input') !== 'input') {
			    		SavePopoverData();
			    		//console.log(document.getElementById("password"+$('[data-toggle="popover"]')[0].parentNode.parentNode.parentNode.id).value);
			        	$('[data-toggle="popover"]').popover('hide');
			    }
			    if($(e.target).data('toggle') == 'popover'){
			    	//SavePopoverData();
			    	$('[data-toggle="popover"]').popover('hide');
			    	$(e.target).popover('show');
			    	document.getElementById("password"+$(e.target).parent().parent().parent().attr('id')).value = popoverData[$(e.target).parent().parent().parent().attr('id')];
			    }
			});
		});
		var popoverData = [];
		function SavePopoverData(){
			for(let i = 0; i<$('[data-toggle="popover"]').length; i++){
				if(document.getElementById("password"+$('[data-toggle="popover"]')[i].parentNode.parentNode.parentNode.id) != null){
					popoverData[$('[data-toggle="popover"]')[i].parentNode.parentNode.parentNode.id] = document.getElementById("password"+$('[data-toggle="popover"]')[i].parentNode.parentNode.parentNode.id).value;
				}
			}
		}
		
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
				iDeleteUser.setAttribute("class", "fas fa-user-times favicon_margin");
				iDeleteUser.setAttribute("style",  "cursor:pointer;color:#f12525;");
				iDeleteUser.setAttribute("title", "Delete user");
				iDeleteUser.setAttribute("onclick", "deleteUser("+id+");");

				let iPasswordA = document.createElement("A");
				iPasswordA.setAttribute("href", "#");
				iPasswordA.setAttribute("data-toggle", "popover");
				iPasswordA.setAttribute("data-placement", "bottom");
				iPasswordA.setAttribute("data-html", "true");
				iPasswordA.setAttribute("data-content", "<input data-input='input' id='password"+id+"' type='text'/>");
				iPasswordA.setAttribute("title", "Enter password:");
				
				
				let iUpdatePassword = document.createElement("I");
				iUpdatePassword.setAttribute("class", "fas fa-key favicon_margin");
				iUpdatePassword.setAttribute("style",  "color:#efbc04;pointer-events:none;");
				
				let save = document.createElement("I");
				save.setAttribute("class", "fas fa-check-circle");
				save.setAttribute("style", "cursor:pointer;color:green;");
				save.setAttribute("title", "Apply changes");
				save.setAttribute("onclick", "editUser(" + id + ");");
				
				iPasswordA.appendChild(iUpdatePassword);
				div.appendChild(iDeleteUser);
				div.appendChild(iPasswordA);
				div.appendChild(save);
				td.appendChild(div);
				tableRow.appendChild(td);
				tableBody.appendChild(tableRow);
			}

			function createEmailColumn(email, id) {
				let tableRow = document.getElementById(id);

				let td = document.createElement("TD");
				let div = document.createElement("DIV");
				div.setAttribute("contenteditable", "");
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
				let admin_div = document.createElement("DIV");
				admin_div.setAttribute("class", "custom-switch");
				
				let admin_input = document.createElement("INPUT");
				admin_input.setAttribute("type", "checkbox");
				admin_input.setAttribute("class", "custom-control-input");
				admin_input.setAttribute("id", "admin_switch" + id);
				admin_input.setAttribute("onclick", "adminSwitch("+id+")");
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
						"custom-switch switch_input_span");

				let foreman_input = document.createElement("INPUT");
				foreman_input.setAttribute("type", "checkbox");
				foreman_input.setAttribute("class", "custom-control-input");
				foreman_input.setAttribute("id", "foreman_switch" + id);
				foreman_input.setAttribute("onclick", "foremanSwitch("+id+")");
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
						" custom-switch switch_input_span");

				let employee_input = document.createElement("INPUT");
				employee_input.setAttribute("type", "checkbox");
				employee_input.setAttribute("class", "custom-control-input");
				employee_input.setAttribute("id", "employee_switch" + id);
				employee_input.setAttribute("onclick", "employeeSwitch("+id+")");
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
				input.setAttribute("disabled", "");

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
		
		function editUser(uid){
			
			let isStudent = false;
			
			if(document.getElementById("student_switch"+uid) != null)
				isStudent = true;
			
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var json = JSON.parse(xhttp.responseText);
					setResultMessage(xhttp.responseText);
				}
			};

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			xhttp.open('POST', 'editUserRow', true);
			xhttp.setRequestHeader('Content-Type',
					'application/json;charset=UTF-8');
			
	
			xhttp.setRequestHeader(header, token);
			if(isStudent){
				xhttp.send(JSON.stringify(
						{
							id:uid,
							username:$("#"+uid).find("td:eq(1)").text(),
							password:document.getElementById("password"+uid).value,
							enabled:document.getElementById("enabled_switch"+uid).checked ? "1" : "0"
						}
						));
			}else {
				xhttp.send(JSON.stringify(
						{
							id:uid,
							username:$("#"+uid).find("td:eq(1)").text(),
							adminAuthority:document.getElementById("admin_switch"+uid).checked ? 1 : 0,
							foremanAuthority:document.getElementById("foreman_switch"+uid).checked ? 1 : 0,
							employeeAuthority:document.getElementById("employee_switch"+uid).checked ? 1 : 0,
							password:document.getElementById("password"+uid).value,
							enabled:document.getElementById("enabled_switch"+uid).checked ? "1" : "0"
						}
						));
			}
	
		}
		
		function adminSwitch(id){
			if(!document.getElementById("admin_switch"+id).checked){
				if(!document.getElementById("foreman_switch"+id).checked && !document.getElementById("employee_switch"+id).checked){
					document.getElementById("admin_switch"+id).checked = true;
				}
			}
		}
		
		function foremanSwitch(id){
			if(!document.getElementById("foreman_switch"+id).checked){
				if(!document.getElementById("admin_switch"+id).checked && !document.getElementById("employee_switch"+id).checked){
					document.getElementById("foreman_switch"+id).checked = true;
				}
			}
		}
		
		function employeeSwitch(id){
			if(!document.getElementById("employee_switch"+id).checked){
				if(!document.getElementById("foreman_switch"+id).checked && !document.getElementById("admin_switch"+id).checked){
					document.getElementById("employee_switch"+id).checked = true;
				}
			}
		}
		
				
