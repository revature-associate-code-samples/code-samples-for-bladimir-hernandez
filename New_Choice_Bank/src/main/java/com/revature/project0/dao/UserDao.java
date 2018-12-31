package com.revature.project0.dao;


import java.util.List;

import com.revature.project0.model.User;

public interface UserDao {
	
	public boolean insertUser(User user);
	public boolean insertUserProcedure(User user);
	public User getUser(User user);
	public boolean checkUser();
	public List<User> getAllUsers();
	
}
