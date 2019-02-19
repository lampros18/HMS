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

function init() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {

			uncheckedStudents = JSON.parse(xhttp.responseText);

			document.getElementsByTagName('i')[5].textContent = `There are ${uncheckedStudents.length} students to check`;
			document.getElementsByClassName('input-group')[0]
					.getElementsByTagName('input')[0].value = uncheckedStudents[0];


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

	document.getElementsByClassName('btn btn-primary')[1].setAttribute('style',
			'display:none');
	setValidationListeners();
}

function setValidationListeners() {

	let inputs = document.getElementsByTagName('input');

	for (let i = 11; i < inputs.length; i++) {

		switch (i) {

		case 11:

			inputs[i].addEventListener('blur', function() {

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

			break;

		case 12:

			inputs[i].addEventListener('blur', function() {

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

			break;

		case 14:

			inputs[i].addEventListener('blur', function() {

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

			break;

		case 16:

			inputs[i].addEventListener('blur', function() {

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

			break;

		case 17:

			inputs[i].addEventListener('blur', function() {

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

			break;
		}

	}

}

document.getElementsByClassName('btn btn-primary')[2]
		.addEventListener(
				'click',
				function() {

					let curStudent = document
							.getElementById('validationServerUsername').value
							.trim();
					let studentName = document.getElementsByTagName('input')[11].value
							.trim();
					let studentLastName = document
							.getElementsByTagName('input')[12].value.trim();
					let studentBirthDate = document
							.getElementsByTagName('input')[13].value.trim();
					let studentYearOfEnrollment = document
							.getElementsByTagName('input')[14].value.trim();
					let studentIsPostGradute = document
							.getElementById('customCheck1').checked;
					let studentPhone = document.getElementsByTagName('input')[16].value
							.trim();
					let studentAddress = document.getElementsByTagName('input')[17].value
							.trim();
					let studentDepartment = document
							.getElementsByTagName('select')[0].value;

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
								
								if(count==3){
									
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

					document.getElementsByClassName('btn btn-primary')[1]
							.setAttribute('style', 'display:none');
					setValidationListeners();

				});

document.getElementsByClassName('btn btn-primary')[3]
		.addEventListener(
				'click',
				function() {

					if (uncheckedStudents.length > 1) {

						let curStudent = document.getElementsByTagName('input')[10].value
								.trim();

						document.getElementsByTagName('input')[10].value = uncheckedStudents[(uncheckedStudents
								.indexOf(curStudent) + 1)
								% uncheckedStudents.length];
					}

				});

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
