package com.power.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.dao.ClientDao;
import com.power.models.Client;
import com.power.options.SearchCritera;
import com.power.options.SearchOpt;

@Component
public class ClientDaoImpl implements ClientDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveClient(Client client) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.beginTransaction();
		currentSession.save(client);
		currentSession.getTransaction().commit();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public  List<Client> getAllClients(){
		Session currentSession = sessionFactory.getCurrentSession();
		//NOTE: Client is case sensitive to that of the class name.  
		return currentSession.createQuery("from Client").getResultList(); 
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Client> getSearchedUsersData(Map<String, String> inputMap) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		StringBuilder clientQuery = new StringBuilder("FROM Client WHERE ");
		clientQuery.append(SearchOpt.valueOf(inputMap.get("searchField")).getOptVal());
		SearchCritera critera = SearchCritera.valueOf(inputMap.get("searchCritera"));
		switch (critera) {
		case EQUAL:
			clientQuery.append(critera.getCritera()).append("'").append(inputMap.get("inputValue")).append("'");
			break;
		case LIKE:
			clientQuery.append(" "+critera.getCritera()+ " '%" + inputMap.get("inputValue") + "%'");
			break;
		}
		return currentSession.createQuery(clientQuery.toString()).getResultList();		
	}

	@Override
	public Client getClient(long accNum) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Client) currentSession.createQuery("from Client where ACCOUNT_NUMBER = " + accNum).getSingleResult();
	}



}
