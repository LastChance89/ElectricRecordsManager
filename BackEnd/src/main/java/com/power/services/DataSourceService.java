package com.power.services;

import java.util.Properties;

import javax.sql.DataSource;

public interface DataSourceService {
	
	public DataSource createDataSource(Properties prop);
	
	public String getDataSourceType(String conType);

}
