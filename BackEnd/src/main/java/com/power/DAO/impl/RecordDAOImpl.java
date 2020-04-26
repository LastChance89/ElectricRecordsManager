package com.power.DAO.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.DAO.RecordDao;
import com.power.extractors.RecordResultsExtractor;
import com.power.models.Record;

@Component

public class RecordDAOImpl extends SharedDaoImpl implements RecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	private final String insert_user_Records = "INSERT INTO POWER_INFO VALUES (?,?,?,?,?,?,?,?,?)";
	
	private final String retreive_user_records = "SELECT * FROM POWER_INFO WHERE ACCOUNT_NUMBER =(?) LIMIT 10";
	
	private final String getRecordsMonthSum = "SELECT DATE_FORMAT(RECORD_DATE,'%Y%m') as DATE, SUM(COST) AS COST, SUM(POWER_USAGE) AS POWER_USAGE " + 
			"FROM POWER_INFO " + 
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
