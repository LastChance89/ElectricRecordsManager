package com.power.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.dao.DashBoardDao;
import com.power.extractors.DateRangeExtractor;
import com.power.models.DashBoard;

@Component
public class DashBoardDaoImpl implements DashBoardDao {
	
	private final Logger logger = LogManager.getLogger(DashBoardDao.class);
	
	@Autowired
	SessionFactory sessionFactory;


	@Autowired
	JdbcTemplate jdbctemplate;
	
	private final String getClientCount = "select count(*) from Client";
	private final String getRecordCount = "select count(*) from Record";
	//Cant use untion all. Change or move ot result set extractor. 
	private final String getRecordRange = "select min(record_date) as date from RECORDS UNION ALL select max(record_date)as date from RECORDS";
	

	@Override
	public DashBoard getDashboardData() {
		try {
			Session currentSession = sessionFactory.openSession();
			
			//TODO: Make a view rather than these 3 queries. Then Convert ot hibernate quiery. 
			long clientCount = (long)currentSession.createQuery(getClientCount).getSingleResult();
			long recordCount = (long)currentSession.createQuery(getRecordCount).getSingleResult();	
				
			List<String> recordRange = jdbctemplate.query(getRecordRange, new Object[] {}, new DateRangeExtractor());
			
			return new DashBoard(clientCount, recordCount, recordRange);
		}
		
		catch(Exception e) {
			logger.error("ERROR :", e);
			//Fix me
			return null;
		}

	}

	
	
	
}

