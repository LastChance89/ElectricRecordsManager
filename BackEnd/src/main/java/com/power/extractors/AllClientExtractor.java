package com.power.extractors;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.power.models.Client;

public class AllClientExtractor implements ResultSetExtractor<List<Client>> {

	@Override
	public List<Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Client> userList = new ArrayList<Client>();
		//ResultSetMetaData met = rs.getMetaData();
		while(rs.next()) {
			//Map<String,Object> userData = new HashMap<String,Object>();			
			Client client = new Client();
			client.setAccountNumber(rs.getLong("ACCOUNT_NUMBER"));
			client.setAddress(rs.getString("ADDRESS"));
			client.setName(rs.getString("NAME"));
			client.setService(rs.getString("SERVICE"));
			/*
			 * for(int i = 1; i<=met.getColumnCount(); i++ ) {
			 * userData.put(met.getColumnName(i),rs.getObject(i)); }
			 */
			userList.add(client);
		}
		return userList;
	}
}
