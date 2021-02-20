package com.power.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class PropertiesLoaderUtil {
	
	private static FileInputStream input;
		
	public static Properties loadProperties() throws IOException{
		Properties prop = new Properties();
		try{
			input = new FileInputStream(PropertiesLoaderUtil.class.getClassLoader().getResource("serverInfo.properties").getFile());
			//Load the proeprties file from the standard resource driectory. 
			prop.load(input);
		}
		catch(IOException e){
			System.out.print("Error Reading properties file " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			input.close();
		}
		return prop;
	}
}
