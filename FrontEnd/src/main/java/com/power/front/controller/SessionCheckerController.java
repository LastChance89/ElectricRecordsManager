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

import com.power.Util.AuthenticationTokenUtil;
import com.power.messages.Message;
import com.power.models.User;

@RestController
@CrossOrigin
@RequestMapping("/power/checkLogin")
public class SessionCheckerController {

	@Autowired
	private AuthenticationTokenUtil authenticationTokenUtil;

	@PostMapping("/checkLoggedIn")
	public ResponseEntity<?> checkLogin(Authentication authentication) {
		// public boolean checkLogin(Authentication authentication){
		if (authentication.isAuthenticated()) {
			Map<String, String> response = new HashMap<String, String>();
			List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			response.put("token", authenticationTokenUtil.createToken(user.getUserName(), roles));
			response.put("user", user.getUserName());
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Message.SERVER_ERROR.getMessage());
	}

	// change this to ResponseEntity so we can return error page if context holder
	// null / invalid.
	@PostMapping("/setContext")
	public boolean setContext() {
		return SecurityContextHolder.getContext() != null
				? SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				: false;
	}

	/*
	 * Might need to adjust flow so this is not silly.
	 */
	@PostMapping(value = "/keepAcitve")
	public ResponseEntity<?> validateAndRefresh(@RequestBody String token) {

		ResponseEntity<?> response = null;
		authenticationTokenUtil.isTokenExpired(token);
		// Context exists and is authenticated
		if (!authenticationTokenUtil.isTokenExpired(token)) {
			if (SecurityContextHolder.getContext() != null
					&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
					&& !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")
					) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (authenticationTokenUtil.getUserNameFromToken(token).equals(user.getUserName())) {
					String updateToken = authenticationTokenUtil.createToken(user);
					Map<String, String> responseToken = new HashMap<String, String>();
					responseToken.put("token", updateToken);
					response = ResponseEntity.status(HttpStatus.OK).body(responseToken);
				} else {
					response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.TOKEN_EXPIRED);
				}
			} else {
				response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.UNAORTHOIZED);
			}
		} else {
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.UNAORTHOIZED);
		}
		return response;
	}

}
