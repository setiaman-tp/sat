<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Staff Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> Staff Management Application </a>
</div>
<ul class="navbar-nav">
<li><a
href="<%=request.getContextPath()%>/StaffServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<form action="update" method="post">

<caption>
<h2>

Edit Staff

</h2>
</caption>
<c:if test="${staff != null}">
<input type="hidden" name="oriStaffNo"
value="<c:out
value='${staff.staffNo}' />" />
</c:if>
<fieldset class="form-group">
	<label>Staff No</label> <input type="text"
	value="<c:out value='${staff.staffNo}' />" class="form-control"
	name="staffNo" required="required" >
</fieldset>
<fieldset class="form-group">
	<label>Staff Name:</label> 
	<input type="text"
		value="<c:out value='${staff.staffname}' />" 
		class="form-control"
		name="staffname" >
</fieldset>
<fieldset class="form-group">
	<label> Mobile No</label> <input type="text"
	value="<c:out value='${staff.mobileNo}' />" class="form-control"
	name="mobileNo" >
</fieldset>

	<fieldset class="form-group">
		<label>Address</label> 
		<input  type="text"
		value="${staff.address}" 
		class="form-control"
		name="address" >
	</fieldset>

<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>