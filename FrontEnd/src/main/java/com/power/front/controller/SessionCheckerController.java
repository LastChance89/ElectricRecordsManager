package com.power.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.power.util.AuthenticationTokenUtil;
import com.power.util.ResponseEntityUtil;
import com.power.messages.Message;
import com.power.models.User;

@RestController
@CrossOrigin
@RequestMapping("/power/checkLogin")
public class SessionCheckerController {

	@Autowired
	private AuthenticationTokenUtil authenticationTokenUtil;

	/*
	 * Used for multiple tab capability. 
	 */
	@PostMapping("/checkLoggedIn")
	public ResponseEntity<String> checkLogin(Authentication authentication) {
		//If its anonymousUser, authentication is null and not checked, so we check first to not throw npe.
		if(!isAnonymous()){
			if (authentication.isAuthenticated())  {
				Map<String, String> response = new HashMap<String, String>();
				List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				response.put("token", authenticationTokenUtil.createToken(user.getUserName(), roles));
				response.put("user", user.getUserName());
				return ResponseEntityUtil.createValidResponse(response);
			}
		}
		//Check if its Anonymous. If it is, were on the login page for the first time, dont throw the error. 
		return isAnonymous() ? ResponseEntityUtil.createResponseMessage(HttpStatus.OK,Message.SET_CONTEXT): ResponseEntityUtil.InternalResponseError();
	}

	// change this to ResponseEntity so we can return error page if context holder
	// null / invalid.
	//This method is so that the spring security context stays persistant after login page. 
	@PostMapping("/setContext")
	public ResponseEntity<Boolean> setContext() {
		return SecurityContextHolder.getContext() != null
				?  ResponseEntityUtil.createResponseMessage(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
				:  ResponseEntityUtil.createResponseMessage(false);
	}

	/*
	 * Might need to adjust flow so this is not silly.
	 */
	@PostMapping(value = "/keepAcitve")
	public ResponseEntity<String> validateAndRefresh(@RequestBody String token) {

		ResponseEntity<String> response = null;
		authenticationTokenUtil.isTokenExpired(token);
		// Context exists and is authenticated
		if (!authenticationTokenUtil.isTokenExpired(token)) {
			if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !isAnonymous()) 
			{
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				//If token and the logged in user are the same, we create a new refresh token and send to the user. 
				if (authenticationTokenUtil.getUserNameFromToken(token).equals(user.getUserName())) {
					String updateToken = authenticationTokenUtil.createToken(user);
					Map<String, String> responseToken = new HashMap<String, String>();
					responseToken.put("token", updateToken);
					response = ResponseEntityUtil.createValidResponse(responseToken);
				} else {
					ResponseEntityUtil.createResponseMessage(HttpStatus.UNAUTHORIZED, Message.TOKEN_EXPIRED);
				}
			} else {
				response =ResponseEntityUtil.createResponseMessage(HttpStatus.UNAUTHORIZED, Message.UNAORTHOIZED);
			}
		} else {
			response =ResponseEntityUtil.createResponseMessage(HttpStatus.UNAUTHORIZED,Message.TOKEN_EXPIRED);
		}
		return response;
	}

	
	private boolean isAnonymous() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser") ? 
				true : false;
	}
	
}
