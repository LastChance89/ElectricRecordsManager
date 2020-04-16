package com.power.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.DAO.UserDao;
import com.power.extractors.UserResultSetExtractor;
import com.power.models.User;

@Component
public class UserDaoImpl extends SharedDaoImpl implements UserDao{

	
	private final String userQuery = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean getUserCredentials(String userName, String password) {
		return jdbcTemplate.query(userQuery, new Object[] {userName,password}, new UserResultSetExtractor());
	}

}
