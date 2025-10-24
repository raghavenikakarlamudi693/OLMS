package com.olms.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.olms.dao.LeaveDAO;
import com.olms.model.Leave;
import com.olms.model.Staff;

@WebServlet("/LeaveController")
public class LeaveController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LeaveDAO leaveDAO;

    @Override
    public void init() throws ServletException {
        leaveDAO = new LeaveDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "view";

        try {
            switch (action) {
                case "apply":
                    request.getRequestDispatcher("applyLeave.jsp").forward(request, response);
                    break;
                case "view":
                	
                case "hrview":
                    viewAllLeaves(request, response);
                    break;

                default:
                    viewLeaves(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("insert".equals(action)) {
                applyLeave(request, response);
            } else {
                viewLeaves(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void applyLeave(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int staffId = Integer.parseInt(request.getParameter("staffId"));
        String leaveType = request.getParameter("leaveType");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String reason = request.getParameter("reason");

        Leave leave = new Leave();
        leave.setStaffId(staffId);
        leave.setLeaveType(leaveType);
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setReason(reason);
        leave.setStatus("Pending");

        boolean success = leaveDAO.applyLeave(leave);

        if(success) {
            request.setAttribute("msg", "Leave applied successfully.");
        } else {
            request.setAttribute("msg", "Failed to apply leave. Try again.");
        }

        viewLeaves(request, response);
    }

    private void viewLeaves(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        HttpSession session = request.getSession(false);
        Staff currentStaff = null;
        if (session != null) {
            currentStaff = (Staff) session.getAttribute("staff");
        }

        List<Leave> leaves;
        if (currentStaff != null) {
            // Show only current staff leaves
            leaves = leaveDAO.getLeavesByStaffId(currentStaff.getStaffId());
        } else {
            // Fallback: show all leaves (for admin or testing)
            leaves = leaveDAO.getAllLeaves();
        }

        request.setAttribute("leaves", leaves);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
    
    private void viewAllLeaves(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Leave> leaves = leaveDAO.getAllLeaves(); // All leaves from DB
        request.setAttribute("leaves", leaves);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}
