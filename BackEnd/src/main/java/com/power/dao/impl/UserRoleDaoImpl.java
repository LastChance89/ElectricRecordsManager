package com.power.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.power.dao.UserRoleDao;
import com.sec.extractor.UserRoleExtractor;

@Component
public class UserRoleDaoImpl implements UserRoleDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String roleQuery = "SELECT ROLE FROM ROLES WHERE USERNAME = ?";
	
	@Override
	@Cacheable(value = "userRoles",key = "#userName")
	public List<SimpleGrantedAuthority> getRoles(String userName) {
		return jdbcTemplate.query(roleQuery, new Object[] {userName}, new UserRoleExtractor());
	}

}
