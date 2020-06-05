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


import com.power.models.Record;

public class RecordResultsExtractor implements ResultSetExtractor<	List<Map<String,Object>>> {

	@Override
	public 	List<Map<String,Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		ResultSetMetaData resultMeta = rs.getMetaData();
		
		List<Map<String,Object>> records = new ArrayList<Map<String,Object>>();
		
		while(rs.next()) {
			Map<String,Object> recordMap = new HashMap<String,Object>();
			for(int i = 1; i<=resultMeta.getColumnCount(); i++) {
				recordMap.put(resultMeta.getColumnName(i), rs.getObject(resultMeta.getColumnName(i)));
			}
			records.add(recordMap);
		} 
		return records;
	}

}
