package com.power.models;

import java.util.List;
import java.util.Map;

public class ClientReport {
	
	private Client client; 
	private List<Map<String,Object>> records;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Map<String, Object>> getRecords() {
		return records;
	}
	public void setRecords(List<Map<String, Object>> records) {
		this.records = records;
	} 
	
	
	

}
