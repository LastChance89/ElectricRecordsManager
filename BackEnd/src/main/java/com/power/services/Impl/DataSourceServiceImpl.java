package com.power.services.Impl;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.power.services.DataSourceService;

@Service
public class DataSourceServiceImpl implements DataSourceService{
	
	public DataSource createDataSource(Properties prop){
		String connectionURL ="jdbc:"+ getDataSourceType(prop.getProperty("databaseType"))+"://"+prop.getProperty("host") +":"+prop.getProperty("port")
		+ "/"+prop.getProperty("dbData")
		//This line of code seems to significantly enhance teh speed of large number of inserts. at this time, 175 thousand takes under a second. 
		//Optional useServerPrepStmts=false& but if I understand this correctly, this is sfater to leave alone. 
		+"?rewriteBatchedStatements=true";
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(connectionURL);
		dataSource.setUsername(prop.getProperty("user"));
		dataSource.setPassword("password");
		return dataSource;
	}
	
	//Switch statement for datasource for future implemntations. 
	public String getDataSourceType(String conType){
		switch(conType){
			case "mysql":
				return "mysql";
			default:
				return null;
		}
	}
	

}
