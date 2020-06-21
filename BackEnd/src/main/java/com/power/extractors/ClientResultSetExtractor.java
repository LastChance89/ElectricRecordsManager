package com.power.extractors;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.power.models.Client;

public class ClientResultSetExtractor implements ResultSetExtractor<List<Client>>{

	@Override
	public List<Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Client> clientList = new ArrayList<Client>();
		while(rs.next()){
			Client client = new Client();
			client.setAccountNumber(rs.getLong("ACCOUNTNUMBER"));
			client.setName(rs.getString("NAME"));
			client.setAddress(rs.getString("ADDRESS"));
			client.setService(rs.getString("SERVICE"));
			clientList.add(client);
		}
		return clientList;
	}

}
