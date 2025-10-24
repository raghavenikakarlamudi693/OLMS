<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Registration</title>
</head>
<body>
<h2>Staff Registration</h2>

<%
    String msg = request.getParameter("msg");
    if("nomatch".equals(msg)){
%>
    <p style="color: red;">Passwords do not match. Try again.</p>
<%
    } else if("error".equals(msg)){
%>
    <p style="color: red;">Registration failed. Try again.</p>
<%
    }
%>


<form action="register" method="post">
    

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>
    
    <label>Confirm Password:</label>
	<input type="password" name="confirmPassword" required><br><br>

    <input type="submit" value="Register">
</form>

<p>Already have an account? <a href="login.jsp">Login Here</a></p>
</body>
</html>
