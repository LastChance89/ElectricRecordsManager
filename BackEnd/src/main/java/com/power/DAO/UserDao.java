package com.power.DAO;

import com.power.models.User;

public interface UserDao {
	public Boolean getUserCredentials(User user);
	public Boolean checkUserNameExists(String userName);
	public Boolean createNewUser(User user);
}
