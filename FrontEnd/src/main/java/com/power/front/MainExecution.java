package com.power.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("com.power.services,"
		+ "com.power.dao,"
		+ "com.power.front,"
		+ "com.power.util,"
		+ "com.power.persistentData,"
		+ "com.sec.util,"
		+ "com.sec.dao,"
		+ "com.sec.service,"
		+ "com.sec.filter")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MainExecution  extends SpringBootServletInitializer
{
	//Create spring initalizer to start loading things after main execution is called. 
	public static void main(String[] args) {
		SpringApplication.run(MainExecution.class, args);
	}
	
}
