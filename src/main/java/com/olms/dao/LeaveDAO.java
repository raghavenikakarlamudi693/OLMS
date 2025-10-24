package com.olms.dao;

import com.olms.model.Leave;
import com.olms.util.DBConnection;

import java.sql.*;
import java.util.*;

public class LeaveDAO {
	// apply leave
	public boolean applyLeave(Leave leave)
	{
		boolean result=false;
		String sql="INSERT INTO leaves(staff_id, leave_type,start_date,end_date,reason,status) "+ "VALUES(?,?,?,?,?, 'pending')";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, leave.getStaffId());
			ps.setString(2, leave.getLeaveType());
			ps.setDate(3, leave.getStartDate());
			ps.setDate(4, leave.getEndDate());
			ps.setString(5, leave.getReason());
			
			int rows = ps.executeUpdate();
			result = rows>0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//View Leaves for a specific staff 
	
	public List<Leave> getLeavesByStaffId(int staffId)
	{
		List<Leave> leaves = new ArrayList<>();
		String sql = "SELECT * FROM leaves WHERE staff_id=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, staffId);
			ResultSet rs = ps.executeQuery();
					while(rs.next())
					{
						Leave leave = new Leave();
						
						leave.setLeaveId(rs.getInt("leave_id"));
						leave.setStaffId(rs.getInt("staff_id"));
						leave.setLeaveType(rs.getString("leave_type"));
						leave.setStartDate(rs.getDate("start_date"));
						leave.setEndDate(rs.getDate("end_date"));
						leave.setReason(rs.getString("reason"));
						leave.setStatus(rs.getString("status"));
						leaves.add(leave);
					}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return leaves;
	}
	//view all leaves (for admin)
	public List<Leave> getAllLeaves()
	{
		List<Leave> leaves = new ArrayList<>();
		String sql = "SELECT l.*, s.name AS staff_name " +
                "FROM leaves l " +
                "JOIN staff_members s ON l.staff_id = s.staff_id";
		
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql))
		{
			while(rs.next())
			{
				Leave leave = new Leave();
				
				
				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setStaffId(rs.getInt("staff_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setStartDate(rs.getDate("start_date"));
				leave.setEndDate(rs.getDate("end_date"));
				leave.setReason(rs.getString("reason"));
				leave.setStatus(rs.getString("status"));
				leave.setStaffName(rs.getString("staff_name"));
				leaves.add(leave);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return leaves;
		
	}
	//update leave status (approve/reject) -for admin
	
	public boolean updateLeaveStatus(int leaveId,String status)
	{
		boolean result = false;
		String sql = "UPDATE leaves SET status=? WHERE leave_id=?";
		try(Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, status);
			ps.setInt(2, leaveId);
			
			int rows = ps.executeUpdate();
			result = rows>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		
	}

}
