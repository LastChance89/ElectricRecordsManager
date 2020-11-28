package com.power.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.power.services.AccountService;
import com.sec.model.User;



@RestController
@CrossOrigin
@RequestMapping("/power/authorization")
public class LoginController {

	@Autowired 
	AccountService accountService;

	@PostMapping(value="/userLogin")
	public ResponseEntity<String> getUserLogin(@RequestBody User user) {
		return accountService.userLogin(user);
	}
	
	@PostMapping(value ="/createAccount")
	public ResponseEntity<String> createAccount(@RequestBody User user){
		return accountService.createUserAccount(user);
	}

	@PostMapping(value="/logOut")
	public ResponseEntity<String> logOutUser(){
		return accountService.logOutUser();
	}
	
	//TODO: Implement me
	@PostMapping(value="/getHint")
	public ResponseEntity<String> getUserHint(@RequestBody String userName){
		return accountService.getPasswordHint(userName);
	}
		
}
