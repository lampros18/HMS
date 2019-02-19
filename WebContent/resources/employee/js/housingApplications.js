

function saveState() {
	xsave = document.getElementById("table_body").innerHTML;
}
var xsave;
function resetState() {
	document.getElementById("table_body").innerHTML = xsave;
}

var resetFlag = false;
var searchMode = false;

$(document).ready(function() {
	init();
	
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

$('#searchField')
.on(
		'change paste keyup',
		function() {

			if (!resetFlag)
				resetFlag = true;
			else
				resetState();

			searchMode = true;

			let trs = document
					.getElementsByTagName('tr');

			let textContent = this.value;

			let regex = new RegExp(textContent, 'g');

			let count = 0;

			for (let i = 1; i < trs.length; i++) {

				if (textContent.trim() == '') {
					//Important
					count++;
					//						trs[i].removeAttribute('style');
					trs[i].setAttribute("filtered",
							"no");
					resetFlag = false;
					searchMode = false;
					continue;
				}

				if (trs[i]
						.getElementsByTagName('td')[0]
						.getElementsByTagName('div')[0].textContent
						.toLocaleLowerCase().includes(
								textContent) == false) {

					//						trs[i].style['display']='none';
					trs[i].setAttribute("filtered",
							"yes");

				} else {
					count++;
					//						trs[i].removeAttribute('style');
					trs[i].setAttribute("filtered",
							"no");
				}
			}
			currentPage = 0;
			maxPages = Math.ceil(count / maxPageRows);
			Pagination();
		});

	
});


var maxPageRows = 3;
var currentPage = 0;
var maxPages = 1;
function NextPage() {
	currentPage++;
	if (currentPage > maxPages - 1) {
		currentPage = maxPages - 1;
		return;
	} else
		Pagination();

}
function PrevPage() {
	currentPage--;
	if (currentPage < 0) {
		currentPage = 0;
		return;
	} else
		Pagination();

}
function SetPage(page) {
	currentPage = page;
	if(currentPage < 0)
		currentPage = 0;
	Pagination();
}
function Pagination() {
	let fsave = true;
	var removal_marks = new Array();
	let children = document.getElementById("table_body").children.length;
	if (searchMode) {
		for (let i = 0; i < children; i++) {
			
			if (document.getElementById("table_body").children[i]
					.getAttribute("filtered") == "yes") {
				//Save state to temporary array
				if (fsave) {
					saveState();
					fsave = false;
				}

				//Mark
				removal_marks.push(document.getElementById("table_body").children[i].getAttribute("id"));
						
			}
		}

		var tr;
		for (tr of removal_marks){
			if(document.getElementById(tr) != null)
				document.getElementById(tr).outerHTML = "";
		}
		
	}
	for (let i = 0; i < document.getElementById("table_body").children.length; i++) {
		if (i < maxPageRows * currentPage
				|| i >= maxPageRows * currentPage + maxPageRows) {
			document.getElementById("table_body").children[i].style.display = "none";
		} else {
			document.getElementById("table_body").children[i].style.display = "table-row";
		}
	}

	ShowPaginationNumbers();
}

function ShowPaginationNumbers() {
	let ul = document.getElementById("paginationNumbers");
	ul.innerHTML = "";
	let li = document.createElement("LI");
	let a = document.createElement("A");
	let number = document.createTextNode("Previous");
	li.setAttribute("class", "page-item");
	a.setAttribute("class", "page-link");
	a.setAttribute("href", "#");
	a.appendChild(number);
	li.appendChild(a);
	li.addEventListener('click', function() {
		PrevPage();
	});
	ul.appendChild(li);

	li = document.createElement("LI");
	a = document.createElement("A");
	a.innerHTML = "&laquo;"
	li.setAttribute("class", "page-item");
	a.setAttribute("class", "page-link");
	a.setAttribute("href", "#");
	li.appendChild(a);
	li.addEventListener('click', function() {
		SetPage(0);
	});
	ul.appendChild(li);

	let max = currentPage + 2;
	if (currentPage == 0)
		max++;
	for (let i = currentPage - 2; i <= max; i++) {
		if (i == -1) {
			continue;
		}
		if (i == -2) {
			continue;
		}
		if (i < maxPages) {
			li = document.createElement("LI");
			a = document.createElement("A");
			if (i != max)
				number = document.createTextNode(i + 1);
			else if (i + 1 == maxPages)
				number = document.createTextNode(i + 1);
			else
				number = document.createTextNode("...");
			if (i == currentPage - 2 && i + 1 > 1)
				number = document.createTextNode("...");
			li.setAttribute("class", "page-item");
			a.setAttribute("class", "page-link");
			a.setAttribute("href", "#");
			a.appendChild(number);
			li.appendChild(a);
			li.addEventListener('click', function() {
				SetPage(i);
			});
			if (i == currentPage)
				a.setAttribute("style", "background-color: #ececec");
			ul.appendChild(li);
		}
	}

	li = document.createElement("LI");
	a = document.createElement("A");
	a.innerHTML = "&raquo;"
	li.setAttribute("class", "page-item");
	a.setAttribute("class", "page-link");
	a.setAttribute("href", "#");
	li.appendChild(a);
	li.addEventListener('click', function() {
		SetPage(maxPages - 1);
	});
	ul.appendChild(li);

	li = document.createElement("LI");
	a = document.createElement("A");
	number = document.createTextNode("Next");
	li.setAttribute("class", "page-item");
	a.setAttribute("class", "page-link");
	a.setAttribute("href", "#");
	a.appendChild(number);
	li.appendChild(a);
	li.addEventListener('click', function() {
		NextPage();
	});
	ul.appendChild(li);
}


function init() {
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("table_body").innerHTML = "";
			loadTableAndData(xhttp.responseText);
			removeLoading();
			saveState();
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
	let count = 0;
	let json = JSON.parse(response);
	let table_body = document.getElementById("table_body");
	for (let i = 0; i < json.housingApplications.length; i++) {
		createTableRow(json.housingApplications[i].id, table_body);
		createUsernameColumn(json.housingApplications[i].username,
				json.housingApplications[i].id);
		createCreationDateColumn(json.housingApplications[i].createdAt,
				json.housingApplications[i].id);
		createActionsColumn(json.housingApplications[i].id, table_body);
		count++;
	}
	maxPages = Math.ceil(count / maxPageRows);
}

function createTableRow(id, table_body) {
	let tableRow = document.createElement("TR");
	tableRow.setAttribute("id", id);
	table_body.appendChild(tableRow);
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

function createActionsColumn(id, table_body) {
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
	table_body.appendChild(tableRow);
}

function removeLoading() {
	if (document.getElementById("loading") != null)
		document.getElementById("loading").outerHTML = "";
	Pagination();
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