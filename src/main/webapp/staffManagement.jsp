<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="row">
<div class="container">
<h3 class="text-center">List of Staff</h3>
<hr>
<div class="container text-left">
<!-- Add new staff button redirects to the staffadd.jsp page -->
<a href="<%=request.getContextPath()%>/addstaff.jsp" class="btn btn-success">Add New Staff</a>
</div>
<br>
<!-- Create a table to list out all current users information -->
<table class="table">
<thead>
<tr>
<th>Staff No</th>
<th>Staff Name</th>
<th>Address</th>
<th>Mobile No</th>
<th>Actions</th>
</tr>
</thead>
<!-- Pass in the list of users receive via the Servlet response to a loop -->
<tbody>
<c:forEach var="staff" items="${listStaff}">
<!-- For each user in the database, display their information accordingly -->
<tr>
<td>
<c:out value="${staff.staffNo}" />
</td>
<td>
<c:out value="${staff.staffname}" />
</td>
<td>
<c:out value="${staff.address}" />
</td>
<td>
<c:out value="${staff.mobileNo}" />
</td>
<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
<td>
<a href="edit?staffNo=<c:out value='${staff.staffNo}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?staffNo=<c:out value='${staff.staffNo}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>