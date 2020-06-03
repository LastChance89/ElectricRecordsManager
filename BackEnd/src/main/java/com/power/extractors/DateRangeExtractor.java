package com.power.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class DateRangeExtractor implements ResultSetExtractor<List<String>> {

	@Override
	public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
		List<String> dateRange = new ArrayList<String>();		
		while (rs.next()) {
			dateRange.add(rs.getString("date"));
		}
		return dateRange;
	}
}
