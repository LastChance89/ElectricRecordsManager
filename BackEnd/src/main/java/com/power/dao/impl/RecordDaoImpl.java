package com.power.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.dao.RecordDao;
import com.power.extractors.RecordResultsExtractor;
import com.power.models.Record;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component

public class RecordDaoImpl implements RecordDao {

	private static final Logger logger = LogManager.getLogger(RecordDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	//TODO Setup this as a string builder so we can make this dynamic. 
	private final String getRecordsMonthSum = "SELECT DATE_FORMAT(RECORD_DATE,'%m/%d/%y') as date, SUM(COST) AS cost, SUM(POWER_USAGE) AS power_usage " + 
			"FROM RECORDS " + 
			"WHERE ACCOUNT_NUMBER = ? " + 
			"GROUP BY DATE";
	
	
	@Override
	public void saveClientRecords(List<Record> records) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			
			Transaction transaction = currentSession.beginTransaction();
			
			int counter = 0;
			
			for(Record record: records) {
				counter ++;
				currentSession.save(record);
				if(counter%5000 ==0) {
					currentSession.flush();
					currentSession.clear();
				}
				
			}
			transaction.commit();
		}
		
		catch(Exception e) {
			logger.error("ERROR :", e);
		}		
	}
	
	@Override
	public 	List<Map<String,Object>> getClientRecords(Long accNum){  
		return  jdbcTemplate.query(getRecordsMonthSum, new Object[] {accNum}, new RecordResultsExtractor());

	}
}
