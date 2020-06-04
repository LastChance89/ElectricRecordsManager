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

@Component

public class RecordDaoImpl extends SharedDaoImpl implements RecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	//TODO Setup this as a string builder so we can make this dynamic. 
	private final String getRecordsMonthSum = "SELECT DATE_FORMAT(RECORD_DATE,'%d/%m/%y') as DATE, SUM(COST) AS COST, SUM(POWER_USAGE) AS POWER_USAGE " + 
			"FROM RECORDS " + 
			"WHERE ACCOUNT_NUMBER = ? " + 
			"GROUP BY DATE";
	
	
	@Override
	public void saveClientRecords(List<Record> records) {
		
		Session session = sessionFactory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		int counter = 0;
		
		for(Record record: records) {
			counter ++;
			session.save(record);
			if(counter%5000 ==0) {
				session.flush();
				session.clear();
			}
			
		}
		transaction.commit();
		
	}
	@Override
	public 	List<Map<String,Object>> getClientRecords(Long accNum){  
		return  jdbcTemplate.query(getRecordsMonthSum, new Object[] {accNum}, new RecordResultsExtractor());

	}
}
