package com.revature.project0.model;

import java.io.Serializable;

public class User implements Serializable {


	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private boolean isadmin;
	private boolean isactive;
	private int balance;
	
	public User() {
		super();
	}
	
	public User(String firstname, String lastname, String username, String password, boolean isadmin, boolean isactive, int balance) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.isadmin = isadmin;
		this.isactive = isactive;
		this.balance = balance;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getIsAdmin() {
		return isadmin;
	}
	
	public void setIsAdmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	public boolean getIsActive() {
		return isactive;
	}
	public void setIsActive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + ", isadmin=" + isadmin + ", isactive=" + isactive + ", balance=" + balance + "]";
	}
}
