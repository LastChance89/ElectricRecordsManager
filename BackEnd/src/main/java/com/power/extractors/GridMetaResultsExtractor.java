package com.power.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.power.models.GridMeta;

public class GridMetaResultsExtractor implements ResultSetExtractor<Map<Integer,List<Map<String,String>>>> {

	@Override
	public Map<Integer,List<Map<String,String>>> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
		Map<Integer,List<Map<String,String>>> gridDefinitions = new HashMap<Integer,List<Map<String,String>>>();
		
		while(rs.next()) {
			Map<String,String> gridMap = new HashMap<String,String>();
			gridMap.put("headerName", rs.getString("COLUMN_LABEL"));
			gridMap.put("field", rs.getString("COLUMN_NAME"));
			//TODO: Implement me at a later time. 
			//gridMap.put("width",String.valueOf(rs.getInt("COLUMN_WIDTH")));
			gridMap.put("cellRenderer", rs.getString("TYPE"));
			
			
			if(!gridDefinitions.containsKey(rs.getInt("GRID_ID"))) {
				gridDefinitions.put(rs.getInt("GRID_ID"),Stream.of(gridMap).collect(Collectors.toList()) );
			}
			else {
				gridDefinitions.get(rs.getInt("GRID_ID")).add(gridMap);
			}
		}
		return gridDefinitions;
	}

}
