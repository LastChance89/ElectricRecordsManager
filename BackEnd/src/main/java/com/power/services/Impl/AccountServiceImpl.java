package com.power.services.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.power.Util.AuthenticationTokenUtil;
import com.power.Util.CustomAuthenticator;
import com.power.messages.Message;
import com.power.models.User;
import com.power.services.AccountService;

@Component
public class AccountServiceImpl implements AccountService {
	private final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	private CustomAuthenticator authenticator;
	
	@Autowired
	private AuthenticationTokenUtil authenticationTokenUtil;
	
	@Override
	public  ResponseEntity<?> userLogin(User user) {
		// TODO Auto-generated method stub
		final String userName = user.getUserName();
		final String password = user.getPassword();
		
		Authentication authentication = authenticator.authenticate(
				new UsernamePasswordAuthenticationToken(userName, password));
		
		if ( authentication != null && userName.equals(authentication.getPrincipal())
				&& password.equals(authentication.getCredentials().toString())) {
			
			try {
				String token =  authenticationTokenUtil.createToken(authentication.getPrincipal().toString());
				//Instantiate for serializability
				Map<String,String> response = new HashMap<String,String>();
				response.put("token",token);
				return ResponseEntity.ok(response);
			}
			catch(Exception e) {
				logger.error("ERROR: ",e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.SERVER_ERROR.getMessage());
			}

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.LOGIN_FAILED.getMessage());
	}

	@Override
	public ResponseEntity<?> createUserAccount() {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.LOGIN_FAILED.getMessage());
	}
	

}
