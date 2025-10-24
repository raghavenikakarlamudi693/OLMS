<%@ page import="com.olms.model.Staff" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // Session check
    Staff currentStaff = (Staff) session.getAttribute("staff");
    if(currentStaff == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Apply Leave</title>
</head>
<body>
    <h2>Apply for Leave</h2>

    <%-- Display message if available --%>
    <%
        String msg = (String) request.getAttribute("msg");
        if(msg != null){
    %>
        <p style="color: green;"><%= msg %></p>
    <%
        }
    %>

    <form action="LeaveController" method="post">
        <input type="hidden" name="action" value="insert">
        <input type="hidden" name="staffId" value="<%= currentStaff.getStaffId() %>">

        <label>Leave Type:</label>
        <select name="leaveType" required>
            <option value="Sick">Sick</option>
            <option value="Casual">Casual</option>
            <option value="Earned">Earned</option>
        </select><br><br>

        <label>Start Date:</label>
        <input type="date" name="startDate" required><br><br>

        <label>End Date:</label>
        <input type="date" name="endDate" required><br><br>

        <label>Reason:</label><br>
        <textarea name="reason" rows="4" cols="30" required></textarea><br><br>

        <input type="submit" value="Apply Leave">
    </form>

    <br>
    <a href="LeaveController?action=view">Back to Dashboard</a>
    <br>
    <a href="LogoutController">Logout</a>
</body>
</html>
