package com.power.front.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.power.persistentData.PersistentData;
import com.power.services.MainService;

@Service
public class SystemInitializerImpl{
	@Autowired
	private MainService power;
	
	@Autowired
	PersistentData storedApplicationConfiguration;

	
	@PostConstruct // Executes this method at startup, so we get persistaent data. 
	public void init() {
		storedApplicationConfiguration.setGridMeta(power.getGridMeta());
	}



	
	
	
	
}
