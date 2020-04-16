package com.power.front.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.power.messages.Message;

import com.power.Util.AuthenticationTokenUtil;



@RestController
@CrossOrigin
@RequestMapping("/power/checkLogin")
public class SessionCheckerController {

	@Autowired
	private AuthenticationTokenUtil authenticationTokenUtil;
	
	@PostMapping("/checkLoggedIn")
	public ResponseEntity<?> checkLogin(Authentication authentication){
	//public boolean checkLogin(Authentication authentication){
		if(authentication.isAuthenticated()) {
			Map<String,String> response = new HashMap<String,String>();
			response.put("token",authenticationTokenUtil.createToken(authentication.getPrincipal().toString()));
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.SERVER_ERROR.getMessage());  
	}
	// change this to ResponseEntity so we can return error page if context holder null / invalid.  
	@PostMapping("/setContext")
	public boolean setContext() {
		return SecurityContextHolder.getContext() != null ?
				SecurityContextHolder.getContext().getAuthentication().isAuthenticated():false;	
	}
	
}
