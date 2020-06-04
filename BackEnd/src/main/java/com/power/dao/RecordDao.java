package com.power.dao;

import java.util.List;
import java.util.Map;

import com.power.models.Record;

public interface RecordDao {
	
	public void saveClientRecords(List<Record> records);
	
	public 	List<Map<String,Object>> getClientRecords(Long accNum);
	
}
