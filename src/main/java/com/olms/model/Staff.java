package com.olms.model;

public class Staff {
	
	private int staffId;
	private String name;
	private String email;
	private String password;

	public Staff(int staffId, String name, String email, String password) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Staff() {
		// TODO Auto-generated constructor stub
	}
		
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
