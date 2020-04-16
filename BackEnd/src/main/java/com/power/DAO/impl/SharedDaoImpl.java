package com.power.DAO.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;


public class SharedDaoImpl{
	/*
	@Value("${connection.url}")
	private String url;
	@Value("${connection.driver}")
	private String driver;
	@Value("${connection.userName}")
	private String userName;
	@Value("${connection.password}")
	private String password;
	*/
	
    @Autowired
    private DataSource dataSource;
    
	
	@Bean 
	public JdbcTemplate getTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		JndiDataSourceLookup jndiLookup = new JndiDataSourceLookup();
		
		//DataSource ds = jndiLookup.getDataSource("java:/mysql/power");
		
		template.setDataSource(dataSource);
		return template;
	}
}
