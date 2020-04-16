package com.power.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.power.models.Client;
import com.power.services.MainService;

//this will become a rest controller at somepoint. 
@RestController
//@CrossOrigin 
@RequestMapping("/power/data")
public class PowerController {
	
	@Autowired
	private MainService mainService;
	
	@PostMapping(value="/initalize")
	public boolean loadUsersData(@RequestParam("files") List<MultipartFile> files){ //Cant use @RequestBody. 
		return mainService.loadUserAndData(files);
	}
	
	@PostMapping(value="/getAllUsers")
	public  List<Client> retrieveAllUsers(){
		return mainService.getAllUsers();
	}

	@PostMapping(value="/getRecords")
	public 	List<Map<String,Object>> retrieveClientRecords(@RequestBody Map<String,String> jsonString){
		Long accNum = Long.valueOf(jsonString.get("accountNumber"));
		return mainService.getUserRecords(accNum);
	}	
	
	@PostMapping(value="/userSearch")
	public List<Client> retrieveUserSearch(@RequestBody Map<String,String> jsonString){
		Map<String,String> inputMap = new HashMap<String,String>();
		inputMap.put("inputValue", jsonString.get("inputVal").toString());
		inputMap.put("searchCritera", jsonString.get("searchCritera").toString());
		inputMap.put("searchField", jsonString.get("searchOpt").toString());
		return mainService.getUserSearch(inputMap);
	}
	
}
