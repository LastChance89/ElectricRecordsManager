package com.power.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface SharedDao {
	public void setupTemplate(DataSource ds);
	public JdbcTemplate getTemplate();
	
	
}
