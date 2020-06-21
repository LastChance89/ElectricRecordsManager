package com.power.dao;

import java.util.List;
import java.util.Map;

import com.power.models.Client;

public interface ClientDao {
	
	public List<Client> getSearchedUsersData (Map<String,String> inputMap);
	public void saveClient(Client client);
	public List<Client> getAllClients();
	public Client getClient(long accNum);
}
