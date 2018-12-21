<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	<form action="/admin/addStudent" method="post">
		<input type="text" placeholder="id" name="id"><br> <input
			type="text" placeholder="email" name="email"><br> <input
			type="text" placeholder="name" name="name"><br> <input
			type="text" placeholder="surname" name="surname"><br> <input
			type="text" placeholder="password" name="password"><br>
		<input type="text" placeholder="birthdate" name="birthdate"><br>
		<input type="text" placeholder="yearOfEnrollment"
			name="yearOfEnrollment"><br> <input type="text"
			placeholder="isPostgraduate" name="isPostgraduate"><br>
		<input type="text" placeholder="department" name="department"><br>
		<input type="text" placeholder="phone" name="phone"><br>
		<input type="text" placeholder="address" name="address"><br>
		<input type="text" placeholder="currentSemester"
			name="currentSemester"><br> <input type="text"
			placeholder="employeeId" name="employeeId"><br> <input
			type="submit">

	</form>

	<script>
		$(document).ready(function(){
			var variable = "test";
			$.ajax({
				url : "/AdminController/addStudent",
				async : true,
				data : {
					id : "1"
				},
				dataType : 'html',
				success : function(dat) {
					console.log(dat);
				}
			});
	
		});
			</script>
</body>
</html>