package com.power.front.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.power.persistentData.PersistentData;

@RestController
@CrossOrigin
@RequestMapping("/grid")
public class GridController {
	
	@Autowired
	PersistentData persistentData;
	
	@PostMapping("/gridMeta")
	public List<Map<String,String>> getGridMetadata(@RequestBody Map<String,String> input){
		return persistentData.getGridMeta(Integer.valueOf(input.get("id")));
		
	}	
	
}
