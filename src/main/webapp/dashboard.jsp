<%@ page import="java.util.*, com.olms.model.Leave, com.olms.model.Staff" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // Session check
    Staff currentStaff = (Staff) session.getAttribute("staff");
    if(currentStaff == null){
        response.sendRedirect("login.jsp");
        return;
    }

    List<Leave> leaves = (List<Leave>) request.getAttribute("leaves");
    String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Dashboard</title>
</head>
<body>
    <h2>Leave Dashboard</h2>

    <%-- Display message if available --%>
    <%
        if(msg != null){
    %>
        <p style="color: green;"><%= msg %></p>
    <%
        }
    %>

    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>Leave ID</th>
                <th>Leave Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Staff Name</th>
                
            </tr>
        </thead>
        <tbody>
            <%
                if (leaves != null && !leaves.isEmpty()) {
                    for (Leave leave : leaves) {
            %>
            <tr>
                <td><%= leave.getLeaveId() %></td>
                <td><%= leave.getLeaveType() %></td>
                <td><%= leave.getStartDate() %></td>
                <td><%= leave.getEndDate() %></td>
                <td><%= leave.getReason() %></td>
                <td style="color: <%= "Pending".equals(leave.getStatus()) ? "orange" : "green" %>">
    <%= leave.getStatus() %>
</td>
                
                <td><%= leave.getStaffName() %></td>
                                
                
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center;">No leaves found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <br>
    <a href="LeaveController?action=apply">Apply New Leave</a>
    <br>
    <a href="LogoutController">Logout</a>
    <br>
	<a href="LeaveController?action=hrview">View All Leaves (HR)</a>
    
</body>
</html>
