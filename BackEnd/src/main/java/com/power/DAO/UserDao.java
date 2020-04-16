package com.power.DAO;

import com.power.models.User;

public interface UserDao {
	public Boolean getUserCredentials(String userName, String password);
}
