package com.power.DAO.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.power.DAO.GridMetaDAO;
import com.power.extractors.GridMetaResultsExtractor;
import com.power.models.GridMeta;

@Component
public class GridMetaDaoImpl implements GridMetaDAO {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	private final String getGridMetadata = "SELECT * FROM GRID_META";
	
	@Override
	public Map<Integer,List<Map<String,String>>>getGridMeta() {
		return jdbctemplate.query(getGridMetadata,  new Object[] {}, new GridMetaResultsExtractor());
	}
}
