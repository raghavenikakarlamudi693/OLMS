<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HR Dashboard</title>
</head>
<body>
    <h2>HR Dashboard</h2>
    <p>Welcome, HR! Here are all the leave applications:</p>

    <a href="LeaveController?action=hrview">View All Leaves</a><br><br>

    <!-- 🔙 Back to Staff Login -->
    <form action="login.jsp" method="get">
        <input type="submit" value="Back to Staff Login">
    </form>
</body>
</html>

