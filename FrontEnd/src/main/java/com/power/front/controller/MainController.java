package com.power.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.power.models.Client;
import com.power.models.ClientReport;
import com.power.models.DashBoard;
import com.power.services.MainService;
import com.power.util.ResponseEntityUtil;

//this will become a rest controller at somepoint. 
@RestController
//@CrossOrigin 
@RequestMapping("/power/data")
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@PostMapping(value="/initalize")
	public ResponseEntity<String> loadUsersData(@RequestParam("files") List<MultipartFile> files){ //Cant use @RequestBody. 
		return mainService.loadUserAndData(files);
	}
	
	@PostMapping(value="/getAllClients")
	public ResponseEntity<String> getAllUsers(){
		return mainService.getAllClients();
	}


	@PostMapping(value="/clientSearch")
	public ResponseEntity<String> userSearch(@RequestBody Map<String,String> jsonString){
		Map<String,String> inputMap = new HashMap<String,String>();
		inputMap.put("inputValue", jsonString.get("inputVal").toString());
		inputMap.put("searchCritera", jsonString.get("searchCritera").toString());
		inputMap.put("searchField", jsonString.get("searchOpt").toString());
		return mainService.userSearch(inputMap);
	}
	
	//Need to figure out how to get rid of the wildCard for teh 2 methods below.
	//Works for now but go's against wildcard rules. 
	@PostMapping(value="/getRecords")
	public ResponseEntity<?> getClientReport(@RequestBody Map<String,String> jsonString){
		Long accNum = Long.valueOf(jsonString.get("accNum"));
		return mainService.generateClientReport(accNum);
	}	
	
	
	@PostMapping(value="/dashboardData")
	public ResponseEntity<?> getDashboardData(){
		return 	mainService.generateDashBoardData();
	}
}
