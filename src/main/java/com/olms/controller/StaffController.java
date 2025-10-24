package com.olms.controller;

import com.olms.dao.StaffDAO;

import com.olms.model.Staff;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class StaffController
 */
@WebServlet({"/register","/login"})
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StaffDAO staffDAO;
    
	@Override
	public void init() throws ServletException
	{
		staffDAO = new StaffDAO();
	}
    
    public StaffController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		    String path = request.getServletPath();
		    switch (path) {
		        case "/register":
		            request.getRequestDispatcher("register.jsp").forward(request, response);
		            break;
		        case "/login":
		            request.getRequestDispatcher("login.jsp").forward(request, response);
		            break;
		        default:
		            response.sendRedirect("index.jsp");
		    
		}

	}

	//Handles post requests (form submissions)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath(); //determines which URL was called
		
		switch(action)
		{
		case "/register":
			registerStaff(request,response);
		break;
		case "/login":
			loginStaff(request,response);
			break;
			default:
				response.sendRedirect("index.jsp");
				break;
		}
	}
	// ====================== Registration ======================
		    private void registerStaff(HttpServletRequest request, HttpServletResponse response)
		            throws IOException {

		        String name = request.getParameter("name");
		        String email = request.getParameter("email");
		        String password = request.getParameter("password");
		        String confirmPassword = request.getParameter("confirmPassword");
		        
		        if (!password.equals(confirmPassword)) {
		            // passwords do not match, redirect back to register page with error
		            response.sendRedirect("register.jsp?msg=nomatch");
		            return;
		        }


		        Staff staff = new Staff();
		        staff.setName(name);
		        staff.setEmail(email);
		        staff.setPassword(password);

		        boolean success = staffDAO.registerStaff(staff);

		        if (success) {
		            response.sendRedirect("login.jsp?msg=registered");
		        } else {
		            response.sendRedirect("register.jsp?msg=error");
		        }
		    }
		    // ====================== Login ======================
		    	    private void loginStaff(HttpServletRequest request, HttpServletResponse response)
		    	            throws IOException {

		    	        String email = request.getParameter("email");
		    	        String password = request.getParameter("password");

		    	        Staff staff = staffDAO.login(email, password);

		    	        if (staff != null) {
		    	            HttpSession session = request.getSession();
		    	            session.setAttribute("staff", staff);
		    	            response.sendRedirect("dashboard.jsp");
		    	        } else {
		    	            response.sendRedirect("login.jsp?msg=invalid");
		    	        }
		    	        
		    	    }


}
