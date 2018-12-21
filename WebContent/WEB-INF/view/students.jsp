<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Students List</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<!--===============================================================================================-->
<style type="text/css">
.cell {
	background-color: transparent;
	border-color: grey !important;
	border-width: 1px !important;
	border-style: groove !important;
	text-align: center;
}
</style>

</head>
<body>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">ID</th>
								<th class="column2">Name</th>
								<th class="column3">Surname</th>
								<th class="column4">Birthdate</th>
								<th class="column5">Department</th>
								<th class="column6">Phone</th>
								<th class="column7">Address</th>
								<th class="column6">Year of Enrollment</th>
								<th class="column6">Undo</th>
								<th class="column6">Save</th>
								<th class="column6">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempStudent" items="${students}">
								<tr>
									<td class="column1"><input type="text" class="cell"
										value="${tempStudent.getId()}" /></td>
									<td class="column2"><input type="text" class="cell"
										value="${tempStudent.getName()}" /></td>
									<td class="column3"><input type="text" class="cell"
										value="${tempStudent.getSurname()}" /></td>
									<td class="column4"><input type="text" class="cell"
										value="${tempStudent.getBirthdate()}" /></td>
									<td class="column5"><input type="text" class="cell"
										value="${tempStudent.getDepartment()}" /></td>
									<td class="column6"><input type="text" class="cell"
										value="${tempStudent.getPhone()}" /></td>
									<td class="column7"><input type="text" class="cell"
										value="${tempStudent.getAddress()}" /></td>
									<td class="column8"><input type="text" class="cell"
										value="${tempStudent.getYearOfEnrollment()}" /></td>

									<td class="column9"><a href="#"
										class="btn btn-info btn-lg"> <span
											class="glyphicon glyphicon-trash"></span></td>
								</tr>


							</c:forEach>


						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>




	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>