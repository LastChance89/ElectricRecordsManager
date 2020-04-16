package com.power.services;

import java.util.List;
import java.util.Map;

import com.power.models.Record;
import com.power.models.Client;

public interface DataService {

	public void initalizeNewUser(List<String> lines);
	
	public Client readUserFromCSV(List<String> lines); 

	public List<Object[]> readPowerData(List<String> lines, long accountNumber);
	
	public Object [] prepairData(String [] currentReadInLine, long accountNumber);
	
	public Client getUser(Map<String,String> inputMap);
	public List<Record> getUserRecords(Long accNum);
	
}
