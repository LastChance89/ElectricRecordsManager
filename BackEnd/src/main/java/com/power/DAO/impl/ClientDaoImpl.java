package com.power.DAO.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.DAO.ClientDao;
import com.power.models.Client;
import com.power.options.SearchCritera;
import com.power.options.SearchOpt;

@Component
public class ClientDaoImpl extends SharedDaoImpl implements ClientDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveUser(Client user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.beginTransaction();
		currentSession.save(user);
		currentSession.getTransaction().commit();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public  List<Client> getAllUsers(){
		Session currentSession = sessionFactory.getCurrentSession();
		//NOTE: Client is case sensitive to that of the class name. 
		return currentSession.createQuery("from Client").getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Client> getSearchedUsersData(Map<String, String> inputMap) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		//currentSession.createQuery("from Client").
		//StringBuilder userQuery = new StringBuilder("SELECT ACCOUNT_NUMBER, NAME, ADDRESS, SERVICE FROM CLIENT WHERE ");
		StringBuilder userQuery = new StringBuilder("FROM Client WHERE ");
		userQuery.append(SearchOpt.valueOf(inputMap.get("searchField")).getOptVal());
		SearchCritera critera = SearchCritera.valueOf(inputMap.get("searchCritera"));
		switch (critera) {
		case EQUAL:
			userQuery.append(critera.getCritera()).append("'").append(inputMap.get("inputValue")).append("'");
			break;
		case LIKE:
			userQuery.append(" "+critera.getCritera()+ " '%" + inputMap.get("inputValue") + "%'");
			break;
		}
		return currentSession.createQuery(userQuery.toString()).getResultList();		
	}

	// Add stuff to me later.
	public void updateUser(Client user) {

	}

}
