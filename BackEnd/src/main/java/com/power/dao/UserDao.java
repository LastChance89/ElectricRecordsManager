package com.power.dao;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.power.models.User;

public interface UserDao {
	public Boolean getUserCredentials(User user);
	public Boolean checkUserNameExists(String userName);
	public Boolean createNewUser(User user);
	public List<SimpleGrantedAuthority> getRoles(String userName);
	public String getPasswordHint(String username);
}
