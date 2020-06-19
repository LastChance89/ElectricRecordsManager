package com.power.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.power.services.MainService;

/**
 * 
 * @author ksmitw
 * Custom Authenticator class to check database for user credentials. 
 * Moved into part of authenticaiton Service. 
 */
@Component
public class CustomAuthenticator 
//implements AuthenticationManager  
{

	/*
	
	private Logger logger = LogManager.getLogger(CustomAuthenticator.class);
	
	@Autowired
	private MainService mainService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		boolean authorized = mainService.getUserCredentials(authentication.getPrincipal().toString(), authentication.getCredentials().toString());

		if(authorized) {
			return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials().toString());
		}
		
		logger.error("Invalid Username / password for username: " + authentication.getPrincipal());
		logger.error("Token has not been created.");
		
		return null; 
	}
*/


	
}
