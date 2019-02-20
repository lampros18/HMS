var uncheckedStudents;

$(document)
		.ready(
				function() {

					$('#date').datepicker({
						format : 'yyyy-mm-dd'
					});

					document.getElementsByClassName('gj-icon')[0]['style'] = 'padding:5px';
					document.getElementById('date').parentNode['style'] = "width: 356px;background-color: white;border: 1px solid rgb(206, 212, 218);padding: 7px;border-radius: 0.25rem;"

						
						
						document
						.getElementById("closeSearch")
						.addEventListener(
								"click",
								function() {
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
								function() {
									//searchOpen = !searchOpen;
									searchOpen = true;
									document.getElementById("searchField").style.top = "0px";
									document.getElementById("closeSearch").style.opacity = "1";
									document.getElementById("closeSearch").style.pointerEvents = "all";
									document.getElementById("openSearch").style.pointerEvents = "none";
									document.getElementById("openSearch").style.opacity = "0";
								});
					document.getElementById("closeSearch").click();
						
				});

init();

var data = [];
var remaining = 0;
function init() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementsByClassName('btn btn-primary')[1]
			.setAttribute('style', 'display:none');
			uncheckedStudents = JSON.parse(xhttp.responseText);

			let selectList = document.getElementById("validationServerUsername");
			let option;
			let textNode;
			let studentUsername;
			for( student of uncheckedStudents ){
				 option = document.createElement("OPTION");
				 studentUsername = student.username;
				 if(student.hasProfile == 0){
					 textNode = document.createTextNode("-- "+studentUsername);
					 remaining++;
				 }
				 else
					 textNode = document.createTextNode(studentUsername);
				option.setAttribute("value", studentUsername);
				selectList.addEventListener("change", function(){
					console.log(this.value);
					setValues(this.value);
				});
				option.appendChild(textNode);
				selectList.appendChild(option);
				
				data[studentUsername] = [];
				data[studentUsername]["name"] = student.name==undefined?"":student.name;
				data[studentUsername]["surname"] = student.surname==undefined?"":student.surname;
				data[studentUsername]["birthdate"] = student.birthdate==undefined?"":student.birthdate;
				data[studentUsername]["yearOfEnrollment"] = student.yearOfEnrollment==undefined?"":student.yearOfEnrollment;
				data[studentUsername]["isPostgraduate"] = student.isPostgraduate==undefined?"":student.isPostgraduate;
				data[studentUsername]["department"] = student.department==undefined?"":student.department;
				data[studentUsername]["phone"] = student.phone==undefined?"":student.phone;
				data[studentUsername]["address"] = student.address==undefined?"":student.address;
			}
			document.getElementById("remaining").innerText = remaining;
			setValues(document.getElementById("validationServerUsername").value);

			document.getElementsByClassName('form-group')[5].childNodes[1].style['display'] = null;

			if (uncheckedStudents.length == 0) {

				document.getElementsByTagName('i')[5].textContent = "There are not student's profiles to create";
				document.getElementsByClassName('input-group')[0]
						.getElementsByTagName('input')[0].value = "";

				document.getElementsByTagName('i')[5]['style'] = "color:red;";

				document.getElementsByClassName('btn btn-primary')[2]['style'] = 'display:none';
				document.getElementsByClassName('btn btn-primary')[3]['style'] = 'display:none';
			}

		}
	};
	
	

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	xhttp.open('POST', 'getAllStudents', true);
	xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send({});

	
	setValidationListeners();
}

function setValues(username){
	document.getElementById("validationServer02").value = data[username]["name"];
	document.getElementById("validationServer03").value = data[username]["surname"];
	document.getElementById("date").value = data[username]["birthdate"];
	document.getElementById("validationServer04").value = data[username]["yearOfEnrollment"];
	document.getElementById("customCheck1").checked = data[username]["isPostgraduate"]==1?true:false;
	document.getElementById("sel1").value = data[username]["department"];
	document.getElementById("validationServer06").value = data[username]["phone"];
	document.getElementById("validationServer07").value = data[username]["address"];
	
	for(let i = 0; i<document.getElementsByClassName("is-invalid").length; i++){
		document.getElementsByClassName("is-invalid")[i].setAttribute("class", "form-control");
	}
	for(let i = 0; i<document.getElementsByClassName("is-valid").length; i++){
		document.getElementsByClassName("is-valid")[i].setAttribute("class", "form-control");
	}
}
function setValidationListeners() {

	let inputs = document.getElementsByTagName('input');

			document.getElementById("validationServer02").addEventListener('blur', function() {

				if (this.value.trim().length > 0) {

					if (this.value.trim().match(/.{3,}/) == null) {

						this.setAttribute('class', 'form-control is-invalid');
					} else {

						this.setAttribute('class', 'form-control is-valid');
					}
				} else {

					this.setAttribute('class', 'form-control');
				}
			});


			document.getElementById("validationServer03").addEventListener('blur', function() {

				if (this.value.trim().length > 0) {

					if (this.value.trim().match(/.{3,}/) == null) {

						this.setAttribute('class', 'form-control is-invalid');
					} else {

						this.setAttribute('class', 'form-control is-valid');
					}
				} else {

					this.setAttribute('class', 'form-control');
				}
			});


			document.getElementById("validationServer04").addEventListener('blur', function() {

				if (this.value.trim().length > 0) {

					if (this.value.trim().match(/^\d{4}$/) == null) {

						this.setAttribute('class', 'form-control is-invalid');
					} else {

						this.setAttribute('class', 'form-control is-valid');
					}
				} else {

					this.setAttribute('class', 'form-control');
				}
			});


			document.getElementById("validationServer06").addEventListener('blur', function() {

				if (this.value.trim().length > 0) {

					if (this.value.trim().match(/^\d{10}$/) == null) {

						this.setAttribute('class', 'form-control is-invalid');
					} else {

						this.setAttribute('class', 'form-control is-valid');
					}
				} else {

					this.setAttribute('class', 'form-control');
				}
			});


			document.getElementById("validationServer07").addEventListener('blur', function() {

				if (this.value.trim().length > 0) {

					if (this.value.trim().match(/^.{3,}$/) == null) {

						this.setAttribute('class', 'form-control is-invalid');
					} else {

						this.setAttribute('class', 'form-control is-valid');
					}
				} else {

					this.setAttribute('class', 'form-control');
				}
			});

}

var savingIndex = -1;
document.getElementsByClassName('btn btn-primary')[2]
		.addEventListener(
				'click',
				function() {
					savingIndex = document.getElementById("validationServerUsername").selectedIndex;
					
					let curStudent = document
							.getElementById('validationServerUsername').value
							.trim();
					let studentName = document.getElementById("validationServer02").value
							.trim();
					let studentLastName = document.getElementById("validationServer03").value.trim();
					let studentBirthDate = document.getElementById("date").value.trim();
					let studentYearOfEnrollment = document.getElementById("validationServer04").value.trim();
					let studentIsPostGradute = document.getElementById("customCheck1").checked;
					let studentPhone = document.getElementById("validationServer06").value
							.trim();
					let studentAddress = document.getElementById("validationServer07").value
							.trim();
					let studentDepartment = document.getElementById("sel1").value;

					let packet = {};

					packet.email = curStudent;
					packet.name = studentName;
					packet.surname = studentLastName;
					packet.birthDate = studentBirthDate;
					packet.yearOfEnrollment = studentYearOfEnrollment;
					packet.postGraduate = studentIsPostGradute;
					packet.phone = studentPhone;
					packet.department = studentDepartment;
					packet.address = studentAddress;

					var xhttp = new XMLHttpRequest();

					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							
							
							let response=JSON.parse(xhttp.responseText);
							
							
							
							if(response.result==200){
								if(document.getElementById("validationServerUsername").children[savingIndex].innerText.includes("--"))
									remaining--;
								if(remaining < 0)
									remaining = 0;
								if(document.getElementById("validationServerUsername").children[savingIndex].innerText.includes("--"))
									document.getElementById("validationServerUsername").children[savingIndex].innerText = document.getElementById("validationServerUsername").children[savingIndex].innerText.substring(3,document.getElementById("validationServerUsername").children[savingIndex].innerText.length);
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["name"] = document.getElementById("validationServer02").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["surname"] = document.getElementById("validationServer03").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["birthdate"] = document.getElementById("date").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["yearOfEnrollment"] = document.getElementById("validationServer04").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["isPostgraduate"] = document.getElementById("customCheck1").checked?1:0;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["department"] = document.getElementById("sel1").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["phone"] = document.getElementById("validationServer06").value;
								data[document.getElementById("validationServerUsername").children[savingIndex].innerText]["address"] = document.getElementById("validationServer07").value;

								setTimeout(function(){
									document.getElementById("validationServerUsername").value = document.getElementById("validationServerUsername").children[savingIndex].innerText;
									savingIndex = -1;
								},100);
								
								document.getElementById("remaining").innerText = remaining;
								document.getElementsByClassName('alert')[0].textContent=`Successful entry of user ${result.username}`;
								document.getElementsByClassName('alert')[0].
								style['display']="inherit";
								document.getElementsByClassName('alert')[0].setAttribute('class',
										"alert alert-success");
								
								delete uncheckedStudents[response.username];
								
								if (uncheckedStudents.length == 0) {

									document.getElementsByTagName('i')[5].textContent = "There are not student's profiles to create";
									document.getElementsByClassName('input-group')[0]
											.getElementsByTagName('input')[0].value = "";

									document.getElementsByTagName('i')[5]['style'] = "color:red;";

									document.getElementsByClassName('btn btn-primary')[2]['style'] = 'display:none';
									document.getElementsByClassName('btn btn-primary')[3]['style'] = 'display:none';
								}else{
									
									
									
									document.getElementById('validationServerUsername').value=uncheckedStudents[0];
								}
								
								
							
							
							}else{
								
								document.getElementsByClassName('alert')[0].setAttribute('class',
								"alert alert-danger");
								document.getElementsByClassName('alert')[0].textContent='Error occured update didn\'t took place ';
								document.getElementsByClassName('alert')[0].
								style['display']="inherit";
								
								
							
							}
							
							let count=0;
							let interv=setInterval(function(){
								
								count++;
								
								if(count==5){
									
									clearInterval(interv);
									
									document.getElementsByClassName('alert')[0].
									style="display:none;"
									
									
								}
								
							},1000);
							
						}
					};

					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");

					xhttp.open('POST', 'createStudent', true);
					xhttp.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp.setRequestHeader(header, token);
					xhttp.send(JSON.stringify(packet));

					
					setValidationListeners();

				});

//document.getElementsByClassName('btn btn-primary')[3]
//		.addEventListener(
//				'click',
//				function() {
//
//					if (uncheckedStudents.length > 1) {
//
//						let curStudent = document.getElementsByTagName('input')[10].value
//								.trim();
//
//						document.getElementsByTagName('input')[10].value = uncheckedStudents[(uncheckedStudents
//								.indexOf(curStudent) + 1)
//								% uncheckedStudents.length];
//					}
//
//				});

$('#searchField').on(
		'change paste keyup',
		function() {
			
			

			let textContent = this.value;

			let regex = new RegExp('^' + textContent, 'g');

			if(textContent.trim().length==0 && uncheckedStudents.length>0 ){
				
			}
			

			for (let i = 0; i < uncheckedStudents.length; i++) {

			
				if(uncheckedStudents[i].match(regex)!=null){
					
					document.getElementById('validationServerUsername').value=uncheckedStudents[i];
				}else{
					
					document.getElementById('validationServerUsername').value="";
				}
				
				
			}

				
		});
