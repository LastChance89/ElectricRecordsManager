package com.power.DAO.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.power.DAO.UserDao;
import com.power.extractors.UserResultSetExtractor;
import com.power.extractors.UserRoleExtractor;
import com.power.models.User;

@Component
public class UserDaoImpl extends SharedDaoImpl implements UserDao{

	private final Logger logger = LogManager.getLogger(UserDaoImpl.class);
			
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	private final String userQuery = "SELECT USERNAME FROM USER WHERE USERNAME = ?";
	private final String roleQuery = "SELECT ROLE FROM ROLES WHERE USERNAME = ?";

	
	@Override
	public Boolean getUserCredentials(User user) {
		return jdbcTemplate.query(userQuery + " AND PASSWORD = ?", new Object[] {user.getUserName(),user.getPassword()}, new UserResultSetExtractor());
	}


	@Override
	public Boolean checkUserNameExists(String userName) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(userQuery, new Object[] {userName}, new UserResultSetExtractor());
	}

	@Override
	public Boolean createNewUser(User user) {
	
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			return true;
		}
		
		catch(Exception e) {
			logger.error("ERROR: " , e);
			return false;
		}
	}


	@Override
	public List<SimpleGrantedAuthority> getRoles(String userName) {
		return jdbcTemplate.query(roleQuery, new Object[] {userName}, new UserRoleExtractor());
	}


}
