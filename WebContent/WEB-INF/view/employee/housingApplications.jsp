<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/resources/employee/js/housingApplications.js"></script>
<title>Verify Housing Applications</title>
<style>
.favicon_margin {
	margin-left: 20px;
}

.table {
	margin-top: 50px;
	background-color: white;
}

.loading_margin {
	margin-top: 5%;
}

.table_margin_while_loading {
	margin-top: 1%;
}

body {
	background: #8e9eab;
	background: -webkit-linear-gradient(to right, #eef2f3, #8e9eab);
	background: linear-gradient(to right, #eef2f3, #8e9eab);
}
</style>


</head>
<body>

	<div id="loading" class="loading_margin container">
		<div class="text-center">
			<button class="btn btn-primary" type="button" disabled>
				<span class="spinner-border spinner-border-sm" role="status"
					aria-hidden="true"></span> Loading...
			</button>
		</div>
	</div>

	<div class="container">
		<table class="table table-striped table-bordered">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Username</th>
					<th scope="col">Creation Date</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody id="tableBody">
			</tbody>
		</table>
	</div>
	<div id="modal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Result</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p id="result_message"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>