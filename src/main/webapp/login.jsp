<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.olms.model.Staff" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Login - Online Leave Management</title>
</head>
<body>
    <h2>Staff Login</h2>

    <!-- Display messages -->
    <%
        String msg = request.getParameter("msg");
        if ("loggedout".equals(msg)) {
    %>
        <p style="color: green;">You have successfully logged out.</p>
    <%
        } else if ("registered".equals(msg)) {
    %>
        <p style="color: green;">Registration successful. Please login.</p>
    <%
        } else if ("error".equals(msg)) {
    %>
        <p style="color: red;">Invalid email or password. Try again.</p>
    <%
        }
    %>

    <!-- Login Form -->
    <form action="login" method="post">
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>

        <input type="submit" value="Login">
    </form>

    <br>
    <p>Don't have an account? <a href="register.jsp">Please Register Here</a></p>
    <p>HR? <a href="hrDashboard.jsp">Go to HR Dashboard</a></p>
    
</body>
</html>
