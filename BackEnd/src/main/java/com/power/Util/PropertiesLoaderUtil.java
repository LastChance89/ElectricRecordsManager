package com.power.Util;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class PropertiesLoaderUtil {
	
	private static Properties prop;
		
	public static Properties loadProperties(){
		prop = new Properties();
		try{
			
			//Load the proeprties file from the standard resource driectory. 
			prop.load(new FileInputStream(PropertiesLoaderUtil.class.getClassLoader().getResource("serverInfo.properties").getFile()));
		}
		catch(Exception e){
			System.out.print("Error Reading properties file " + e.getMessage());
			e.printStackTrace();
		}
		return prop;
	}
}
