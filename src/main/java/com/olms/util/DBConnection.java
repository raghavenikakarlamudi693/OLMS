package com.olms.util;
import java.sql.*;

public class DBConnection {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USER="system";
	private static final String PASSWORD = "Oracle123";
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection(url,USER,PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

}
