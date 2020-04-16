package com.power.front.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.power.models.User;
import com.power.services.AccountService;



@RestController
@CrossOrigin
@RequestMapping("/power/authorization")
public class LoginController {
	private final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired 
	AccountService accountService;

	@PostMapping(value="/userLogin")
	public ResponseEntity<?> getUserLogin(@RequestBody User user) {
		return accountService.userLogin(user);
	}
	
	
	public ResponseEntity<?> createAccount(@RequestBody Map<String,String> payload){
		return null;
		
	}
		

}
