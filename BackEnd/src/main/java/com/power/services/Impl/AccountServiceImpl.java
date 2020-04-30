package com.power.services.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.power.DAO.UserDao;
import com.power.Util.AuthenticationTokenUtil;
import com.power.messages.Message;
import com.power.models.User;
import com.power.services.AccountService;

@Component
public class AccountServiceImpl implements AccountService {
	private final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

	/*
	 * @Autowired private CustomAuthenticator authenticator;
	 */
	
	@Autowired
	private AuthenticationTokenUtil authenticationTokenUtil;
	
	@Autowired
	private UserDao userDao;

	
	@Override
	public  ResponseEntity<?> userLogin(User user) {
		//boolean authenticated = authenticate(user);
		ResponseEntity<?> response = null;
		if (authenticate(user)) {
			try {
				List<SimpleGrantedAuthority> roles = userDao.getRoles(user.getUserName());
				user.setRoles(roles);
				String token =  authenticationTokenUtil.createToken(user);
				//Instantiate for serializability
				Map<String,String> responseToken = new HashMap<String,String>();
				responseToken.put("token",token);
				response= ResponseEntity.status(HttpStatus.OK).body(responseToken);
			}
			catch(Exception e) {
				logger.error("ERROR: ",e);
				response= ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.SERVER_ERROR.getMessage());
			}
		}
		else {
			logger.error("Invalid Username / password for username: " + user.getUserName());
			logger.error("Token has not been created.");
			response= ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.LOGIN_FAILED.getMessage());
		}
		return response;
		
	
	}

	// Authorizes the user who is logging in.
	//@Override
	private boolean authenticate(User user) throws AuthenticationException {
		return userDao.getUserCredentials(user);
	}
		
	/*
	 * Refactor or good enough?
	 * Seems a little too complicated, flow might not be a bit off. 
	 * 
	 * variables userNameExists and createSuccsess set as boolean objects 
	 * for ease of debugging. 
	 */
	@Override
	public ResponseEntity<String> createUserAccount(User user) {
		ResponseEntity<String> response = null;
		try {
			boolean userNameExists = userDao.checkUserNameExists(user.getUserName());
			if (!userNameExists) {
				
				boolean createSuccsess = userDao.createNewUser(user);
				if (createSuccsess) {
					response = ResponseEntity.ok(Message.USER_CREATION_SUCSESS.getMessage());
				}
				else {
					response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.USER_CREATION_ERROR.getMessage());
				}
			}
			else {
				logger.info("Username " + user.getUserName() + " already exists");
				response = ResponseEntity.ok().body(Message.USER_EXISTS.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR: ", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.SERVER_ERROR.getMessage());

		}
		return response;
	}
}
