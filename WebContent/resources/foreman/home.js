$(document)
		.ready(
				function() {

					$('#date').datepicker({
						format : 'yyyy-mm-dd'
					});

					$('#date1').datepicker({
						format : 'yyyy-mm-dd'
					});

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[0].style['background-color'] = 'white';
					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[1].style['background-color'] = 'white';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[0].style.border = '1px solid #ced4da';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[1].style.border = '1px solid #ced4da';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[0].style['padding'] = '7px';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[1].style['padding'] = '7px';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[0].style['border-radius'] = '0.25rem';

					document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable')[1].style['border-radius'] = '0.25rem';

					document.getElementsByClassName('gj-icon')[0].style['padding-top'] = '2.5%';

					document.getElementsByClassName('gj-icon')[0].style['padding-right'] = '2.5%';

					document.getElementsByClassName('gj-icon')[1].style['padding-right'] = '2.5%';

					document.getElementsByClassName('gj-icon')[1].style['padding-top'] = '2.5%';

					let dropDowns = document
							.getElementsByClassName('form-group col-md-4');

					let input;
					let tmp;
					for (let i = 0; i < dropDowns.length; i++) {

						input = dropDowns[i].getElementsByTagName('select')[0];

						for (let k = 0; k < 101; k++) {

							tmp = document.createElement('option');
							tmp.textContent = k;

							input.appendChild(tmp);

						}
					}

					var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");

					var xhttp1 = new XMLHttpRequest();
					xhttp1.open('GET', 'getDateVariables', true);
					xhttp1.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp1.setRequestHeader(header, token);

					xhttp1.send();

					xhttp1.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result = xhttp1.responseText;

							result = JSON.parse(result);

							if (result.starting_date != null
									&& result.ending_date != null) {

								document
										.getElementsByClassName('gj-textbox-md')[0].value = result.starting_date;

								document
										.getElementsByClassName('gj-textbox-md')[1].value = result.ending_date;
							}

						}
					};

					var xhttp = new XMLHttpRequest();
					xhttp
							.open(
									'GET',
									'getApplicationsLimit?department=Informatics and Telematics',
									true);
					xhttp.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp.setRequestHeader(header, token);

					xhttp.send();
					xhttp.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result1 = xhttp.responseText;

							result1 = JSON.parse(result1);

							if (result1.status == 'success') {

								document.getElementsByTagName('select')[0].selectedIndex = result1.limit;
							}

						}
					};

					var xhttp2 = new XMLHttpRequest();
					xhttp2
							.open(
									'GET',
									'getApplicationsLimit?department=Home economics and ecology',
									true);
					xhttp2.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp2.setRequestHeader(header, token);

					xhttp2.send();
					xhttp2.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result = xhttp2.responseText;

							result = JSON.parse(result);

							if (result.status == 'success') {

								document.getElementsByTagName('select')[1].selectedIndex = result.limit;
							}

						}
					};

					var xhttp3 = new XMLHttpRequest();
					xhttp3.open('GET',
							'getApplicationsLimit?department=Geography', true);
					xhttp3.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp3.setRequestHeader(header, token);

					xhttp3.send();
					xhttp3.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result = xhttp3.responseText;

							result = JSON.parse(result);

							if (result.status == 'success') {

								document.getElementsByTagName('select')[2].selectedIndex = result.limit;
							}

						}

					};

					var xhttp4 = new XMLHttpRequest();
					xhttp4
							.open(
									'GET',
									'getApplicationsLimit?department=International master of sustainable tourism development',
									true);
					xhttp4.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp4.setRequestHeader(header, token);

					xhttp4.send();
					xhttp4.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result = xhttp4.responseText;

							result = JSON.parse(result);

							if (result.status == 'success') {

								document.getElementsByTagName('select')[3].selectedIndex = result.limit;
							}

						}

					};

					var xhttp5 = new XMLHttpRequest();
					xhttp5
							.open(
									'GET',
									'getApplicationsLimit?department=Nutrition and dietics',
									true);
					xhttp5.setRequestHeader('Content-Type',
							'application/json;charset=UTF-8');
					xhttp5.setRequestHeader(header, token);

					xhttp5.send();
					xhttp5.onreadystatechange = function() {

						if (this.readyState == 4 && this.status == 200) {

							result = xhttp5.responseText;

							result = JSON.parse(result);

							if (result.status == 'success') {

								document.getElementsByTagName('select')[4].selectedIndex = result.limit;
							}

						}

					};

				});

document.getElementById('error').getElementsByTagName('button')[0]
		.addEventListener('click', function() {

			setTimeout(function() {

				$("#modal-body").empty();

				$("#modal-body").append('<p></p>');

			}, 200);

		});

document.getElementsByClassName('btn btn-primary')[0]
		.addEventListener(
				'click',
				function() {

					var err = [];

					var values = {};

					var errDpt = [];

					var startDate = document
							.getElementsByClassName('gj-datepicker gj-datepicker-md gj-unselectable');

					if (startDate[0].childNodes[0].value.trim().match(
							/^\d{4}-\d{2}-\d{2}$/) == null) {

						err
								.push('Starting date doesn\'t seems to be valid. Valid date format yyyy-mm-dd')
					} else {
						values['startDate'] = startDate[0].childNodes[0].value
								.trim();
					}

					if (startDate[1].childNodes[0].value
							.match(/^\d{4}-\d{2}-\d{2}$/) == null) {

						err
								.push('Ending date doesn\'t seems to be valid. Valid date format yyyy-mm-dd')
					} else {

						values['endDate'] = startDate[1].childNodes[0].value
								.trim();
					}

					var freeSlots = document.getElementsByTagName('select');

					var freeSlotsArray = [];

					for (let i = 0; i < freeSlots.length; i++) {

						if (freeSlots[i].selectedIndex < 0) {

							errDpt
									.push(freeSlots[i].parentNode.childNodes[1].textContent);
						}

						freeSlotsArray.push(freeSlots[i].selectedIndex);
					}

					let strDate = document.getElementById('date');
					let enDate = document.getElementById('date1');

					dd1 = strDate.value.split('-');

					dd2 = enDate.value.split('-');

					dd1 = new Date(dd1[0], dd1[1], dd1[2]);

					dd2 = new Date(dd2[0], dd2[1], dd2[2]);

					if (dd2 < dd1) {

						err
								.push(`End date ${enDate.value} can\'t be less than start date ${strDate.value} `)
					}

					if (errDpt.length == 0 && err.length == 0) {

						let numOfsuccesses = 0;

						let token = $("meta[name='_csrf']").attr("content");
						let header = $("meta[name='_csrf_header']").attr(
								"content");

						let startingDate = document.getElementById('date').value;

						let endinDate = document.getElementById('date1').value;

						let xhttp = new XMLHttpRequest();
						xhttp
								.open(
										'POST',
										`setDateVariables?starting_date=${startingDate}&ending_date=${endinDate}`,
										true);
						xhttp.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp.setRequestHeader(header, token);

						xhttp.send();
						xhttp.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}

							}

						};

						let department;

						let limit;

						department = 'Informatics and Telematics';

						limit = document.getElementsByTagName('select')[0].selectedIndex;

						let xhttp1 = new XMLHttpRequest();
						xhttp1
								.open(
										'POST',
										`setApplicationsLimit?department=${department}&limit=${limit}`,
										true);
						xhttp1.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp1.setRequestHeader(header, token);

						xhttp1.send();
						xhttp1.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp1.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}
							}

						};

						department = 'Home economics and ecology';

						limit = document.getElementsByTagName('select')[1].selectedIndex;

						let xhttp2 = new XMLHttpRequest();
						xhttp2
								.open(
										'POST',
										`setApplicationsLimit?department=${department}&limit=${limit}`,
										true);
						xhttp2.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp2.setRequestHeader(header, token);

						xhttp2.send();
						xhttp2.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp2.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}
							}

						};

						department = 'Geography';

						limit = document.getElementsByTagName('select')[2].selectedIndex;

						let xhttp3 = new XMLHttpRequest();
						xhttp3
								.open(
										'POST',
										`setApplicationsLimit?department=${department}&limit=${limit}`,
										true);
						xhttp3.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp3.setRequestHeader(header, token);

						xhttp3.send();
						xhttp3.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp3.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}
							}

						};

						department = 'International master of sustainable tourism development';

						limit = document.getElementsByTagName('select')[3].selectedIndex;

						let xhttp4 = new XMLHttpRequest();
						xhttp4
								.open(
										'POST',
										`setApplicationsLimit?department=${department}&limit=${limit}`,
										true);
						xhttp4.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp4.setRequestHeader(header, token);

						xhttp4.send();
						xhttp4.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp4.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}
							}

						};

						department = 'Nutrition and dietics';

						limit = document.getElementsByTagName('select')[4].selectedIndex;

						let xhttp5 = new XMLHttpRequest();
						xhttp5
								.open(
										'POST',
										`setApplicationsLimit?department=${department}&limit=${limit}`,
										true);
						xhttp5.setRequestHeader('Content-Type',
								'application/json;charset=UTF-8');
						xhttp5.setRequestHeader(header, token);

						xhttp5.send();
						xhttp5.onreadystatechange = function() {

							if (this.readyState == 4 && this.status == 200) {

								result = xhttp5.responseText;

								result = JSON.parse(result);

								if (result.status == "success") {

									numOfsuccesses++;
								}
							}

						};
						
						let cnt=0;
						let timeRunner=setInterval(function(){
 							 
 							 
 							 
 							 cnt++;
 							 
 							if(numOfsuccesses==6){
								 
								 let modal=document.getElementById('success');
								 
								 modal.getElementsByTagName('p')[0].textContent="Updates successfuly took place";
								 
								 $('#success').modal('show');
								 clearInterval(timeRunner);
								 return;
							 }
 							 
 							 
 							 if(cnt>50){
 								 
 								 let modal=document.getElementById('success');
								 
								 modal.getElementsByTagName('p')[0].textContent="Error occured on updates";
								 
								 $('#success').modal('show');
 								 
 								 
								 clearInterval(timeRunner);
 								 return;
 							 }
 							 
 						 },100);

					} else {

						let tmp;

						document.getElementById('error').getElementsByTagName(
								'p')[0].textContent = 'Following errors occured';

						let rightDiv = document.getElementById('error')
								.getElementsByTagName('p')[0].parentNode;

						for (let i = 0; i < err.length; i++) {

							tmp = document.createElement('p');

							tmp.textContent = err[i];

							rightDiv.appendChild(tmp);

						}

						for (let i = 0; i < errDpt.length; i++) {

							tmp = document.createElement('p');

							tmp.textContent = 'You haven\'t choosed number of free slots for '
									+ errDpt[i];

							rightDiv.appendChild(tmp);

						}

						$('#error').modal('show');

					}

				});
