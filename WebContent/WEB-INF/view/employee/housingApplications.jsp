<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="UTF-8">
<title>Housing Applications</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/employee/css/employee/housingApplications.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/employee/js/housingApplications.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/menu.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/hamburgers.css">
	<script src="${pageContext.request.contextPath}/resources/hamburger.js"></script>

</head>
<body>
	
	<div style="position:fixed; left: 10px; top: 65px;">
		/<a href="${pageContext.request.contextPath}/employee/employeeHome"> Employee </a> / <a href="${pageContext.request.contextPath}/employee/housingApplications">Housing Applications</a>
	</div>
	<div
		style="background-color: #3e8ef7; width: 130%; height: 56px; position: fixed; top: 0;">
		<button class="hamburger hamburger--3dy" type="button" id="hamburger">
			<span class="hamburger-box"> <span class="hamburger-inner"></span>
			</span>
		</button>
		<div style="color: white; font-size: 2em; display: inline; pointer-events:none; user-select: none;">HMS</div>
		<input type="text" placeholder="Search..." id="searchField"
			class="searchTerm">
		<div id="closeSearch">
			<i class="fas fa-times" style="opacity: 0.6;"></i>
		</div>
		<div id="openSearch">
			<i class="fas fa-search" style="opacity: 1;"></i>
		</div>
	</div>
	<div id='cssmenu'>
		<ul style="user-select: none;">
			<li style="pointer-events:none;"><a href='#'><i class="fas fa-user-tie"> <span style="text-transform: none; font-family: 'Roboto Condensed';"><c:out value="${username}" /></span></i></a></li>
			
			<c:choose>
				<c:when test="${authorities.size()>1}">
					<li class='active has-sub'><a href='#'><span>Home</span></a>
						<ul>
							<c:forEach var="i" begin="0" end="${authorities.size()-1}">
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_ADMIN\")}">
										<li><a href="${pageContext.request.contextPath}/admin/editUsers"><span>Admin</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_FOREMAN\")}">
										<li><a href="${pageContext.request.contextPath}/foreman/home"><span>Foreman</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
										<li><a href="${pageContext.request.contextPath}/employee/employeeHome"><span>Employee</span></a></li>
									</c:if>
									<c:if test="${authorities.get(i).getAuthority().equals(\"ROLE_STUDENT\")}">
										<li><a href="${pageContext.request.contextPath}/student/studentHome"><span>Student</span></a></li>
									</c:if>
							</c:forEach>
						</ul></li>
				</c:when>
				<c:otherwise>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_ADMIN\")}">
						<li><a href="${pageContext.request.contextPath}/admin/editUsers"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_FOREMAN\")}">
						<li><a href="${pageContext.request.contextPath}/foreman/home"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_EMPLOYEE\")}">
						<li><a href="${pageContext.request.contextPath}/employee/employeeHome"><span>Home</span></a></li>
					</c:if>
					<c:if test="${authorities.get(0).getAuthority().equals(\"ROLE_STUDENT\")}">
						<li><a href="${pageContext.request.contextPath}/student/studentHome"><span>Home</span></a></li>
					</c:if>
					
				</c:otherwise>
			</c:choose>

			<li><a href='${pageContext.request.contextPath}/employee/housingApplications' style="font-weight: 900;"><span>Applications</span></a></li>
			<li><a href='${pageContext.request.contextPath}/about'><span>About</span></a></li>
			<li><a href='${pageContext.request.contextPath}/contact'><span>Contact</span></a></li>
			<li><a href='#' id="logoutBtn"><span>Logout</span></a></li>

			<li
				style="position: fixed; top: -1000; left: -1000; display: none; visibility: hidden; width: 0px; height: 0px;"><form:form
					action="${pageContext.request.contextPath}/logout" method="POST">
					<button type="submit" id="logoutSubmit">Logout</button>
				</form:form></li>
		</ul>
	</div>
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
			<tbody id="table_body">
			</tbody>
		</table>
		<nav aria-label="Page navigation example" id="pagination-element">
		
			<ul class="pagination" style="user-select: none;" id="paginationNumbers">
				
			</ul>
		</nav>
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