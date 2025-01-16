<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff </title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .form-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        margin-right: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 400px;
    }
    .form-container h2 {
        margin-bottom: 20px;
        text-align: center;
    }
    .form-container label {
        display: block;
        margin-bottom: 5px;
    }
    .form-container input[type="text"] {
  		width: 100%; 
		padding: 8px; 
		margin-bottom: 10px; 
		border: 1px solid #ccc; 
		border-radius: 4px; 
		box-sizing: border-box;   
    }
    .form-container input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
	.form-container textarea { 
		width: 100%; 
		padding: 8px; 
		margin-bottom: 10px; 
		border: 1px solid #ccc; 
		border-radius: 4px; 
		box-sizing: border-box; 
	}
    
    .form-container input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .form-container input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
<div class="form-container">
    <h2>Staff Management</h2>
    <form action="StaffServlet/insert" method="Post"> 
        <label for="staffNo">Staff No:</label>
        <input type="text" id="staffNo" name="staffNo" required>
        
        <label for="name">Staff Name:</label>
        <input type="text" id="staffname" name="staffname" required>
        
        <label for="address">Address:</label>
        <textarea  rows="4" id="address" name="address" required></textarea>
        
        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" required>
        
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
