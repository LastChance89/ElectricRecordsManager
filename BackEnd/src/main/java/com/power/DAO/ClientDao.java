package com.power.dao;

import java.util.List;
import java.util.Map;

import com.power.models.Client;

public interface ClientDao {
	
	public List<Client> getSearchedUsersData (Map<String,String> inputMap);

	public void updateUser(Client user);
	
	public void saveUser(Client user);
	public  List<Client> getAllUsers();
	
}
