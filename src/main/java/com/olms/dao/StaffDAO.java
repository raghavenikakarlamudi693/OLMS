package com.olms.dao;
import com.olms.model.Staff;
import com.olms.util.DBConnection;
import java.sql.*;

public class StaffDAO {
	//Register a new staff member
	public boolean registerStaff(Staff staff)
	{
		boolean result=false;
		String sql = "INSERT INTO staff_members(staff_id,name,email,password) VALUES(staff_seq.NEXTVAL,?,?,?)";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		
		{
			
			ps.setString(1, staff.getName());
			ps.setString(2, staff.getEmail());
			ps.setString(3, staff.getPassword());
			
			
			int rows=ps.executeUpdate();
			result = rows>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//Staff login
	
	public Staff login(String email,String password)
	{
		Staff staff=null;
		String sql = "SELECT * FROM staff_members WHERE email=? AND password=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				{
				staff=new Staff();
				
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setName(rs.getString("name"));
				staff.setEmail(rs.getString("email"));
				staff.setPassword(rs.getString("password"));
				
				}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return staff;
			
				
		}
	
	//Get staff by ID
	public Staff getStaffById(int staffId)
	{
		Staff staff = null;
		String sql = "SELECT * FROM staff_members WHERE staff_id=?";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, staffId);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				staff=new Staff();
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setName(rs.getString("name"));
				staff.setEmail(rs.getString("email"));
				staff.setPassword(rs.getString("password"));
			}
		}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return staff;
		
	}
	
	}


