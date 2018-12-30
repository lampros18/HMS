<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Users</title>
<script
	src="${pageContext.request.contextPath}/resources/dataTables/jquery-3.3.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<body>
	<table id="users" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Email</th>
				<th>Password</th>
				<th>Student</th>
				<th>Admin</th>
				<th>Foreman</th>
				<th>Employee</th>
				<th>Enabled</th>
			</tr>
		</thead>
	</table>
	<script>
  	var editor; // use a global for the submit and return data rendering in the examples
  	
    $(document).ready(function() {
    	var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		
		
		

		
        $('#users').DataTable( {
            "ajax": {
                "url": "",
                "type": "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Content-Type','application/json;charset=UTF-8');
                    xhr.setRequestHeader(header, token);
                }
            },
            "columns": [
                { "data": "email" },
                { "data": "password" },
                { "data": "student" },
                { "data": "admin" },
                { "data": "foreman" },
                { "data": "employee" },
                { "data": "enabled" }
                
            ]
        } );
    } );
    </script>
</body>
</html>