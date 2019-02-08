$(document).ready(function() {
	init();
});

function init() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("tableBody").innerHTML = "";
			loadTableAndData(xhttp.responseText);
			removeLoading();
		}
	};

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	xhttp.open('POST', 'getHousingApplications', true);
	xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send({});
}

function loadTableAndData(response) {
	let json = JSON.parse(response);
	let tableBody = document.getElementById("tableBody");
	for (let i = 0; i < json.housingApplications.length; i++) {
		createTableRow(json.housingApplications[i].id, tableBody);
		createUsernameColumn(json.housingApplications[i].username,
				json.housingApplications[i].id);
		createCreationDateColumn(json.housingApplications[i].createdAt,
				json.housingApplications[i].id);
		createActionsColumn(json.housingApplications[i].id, tableBody);
	}
}

function createTableRow(id, tableBody) {
	let tableRow = document.createElement("TR");
	tableRow.setAttribute("id", id);
	tableBody.appendChild(tableRow);
}

function createUsernameColumn(username, id) {
	let tableRow = document.getElementById(id);

	let td = document.createElement("TD");
	let div = document.createElement("DIV");
	let a = document.createElement("A");
	a.setAttribute("href", "applications/" + id);
	a.setAttribute("target", "_blank");
	let textNode = document.createTextNode(username);

	a.appendChild(textNode);
	div.appendChild(a);
	td.appendChild(div);
	tableRow.appendChild(td);

}

function createCreationDateColumn(date, id) {
	let tableRow = document.getElementById(id);

	let td = document.createElement("TD");
	let div = document.createElement("DIV");
	let textNode = document.createTextNode(date);

	div.appendChild(textNode);
	td.appendChild(div);
	tableRow.appendChild(td);

}

function createActionsColumn(id, tableBody) {
	let tableRow = document.getElementById(id);

	let td = document.createElement("TD");

	let div = document.createElement("DIV");
	div.setAttribute("class", "container")

	let verify = document.createElement("I");
	verify.setAttribute("class", "fas fa-check-circle");
	verify.setAttribute("style", "cursor:pointer;color:green;");
	verify.setAttribute("onclick", "verify(" + id + ");");

	let reject = document.createElement("I");
	reject.setAttribute("class", "fas fa-times-circle favicon_margin");
	reject.setAttribute("style", "cursor:pointer;color:red;");
	reject.setAttribute("onclick", "reject(" + id + ");");

	div.appendChild(verify);
	div.appendChild(reject);
	td.appendChild(div);
	tableRow.appendChild(td);
	tableBody.appendChild(tableRow);
}

function removeLoading() {
	if (document.getElementById("loading") != null)
		document.getElementById("loading").outerHTML = "";
}

function verify(id) {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(JSON.parse(xhttp.responseText).status == "success")
				{
					let tr = document.getElementById(id);
					tr.outerHTML = "";
					document.getElementById("result_message").innerHTML = "Your request was successful.";
				}else{
					document.getElementById("result_message").innerHTML = "Oops! Something went wrong!";
				}
			$('#modal').modal('show');
		}
	};

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	xhttp.open('POST', 'verify/' + id, true);
	xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send();
}

function reject(id) {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(JSON.parse(xhttp.responseText).status == "success")
			{
				let tr = document.getElementById(id);
				tr.outerHTML = "";
				document.getElementById("result_message").innerHTML = "Your request was successful.";
			}else{
				document.getElementById("result_message").innerHTML = "Oops! Something went wrong!";
			}
		$('#modal').modal('show');
		}
	};

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	xhttp.open('POST', 'reject/' + id, true);
	xhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send();
}