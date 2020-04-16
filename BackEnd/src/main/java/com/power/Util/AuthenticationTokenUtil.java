package com.power.Util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthenticationTokenUtil  implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String secret = "Secret_Key"; 
	
	public String getUserNameFromToken(String token) {
		return  getAllClaimsFromToken(token).getSubject();
	}
	
	//we are getting each claim experation date from teh token.  
	public Date getExperationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}
	
	private Boolean isTokenExpired(String token) {
		Date expiration = getExperationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//@TODO: Create my own custom user details so I can have useful information.
	//public String createToken(UserDetails userDetails) {
	public String createToken(String userName) {
		return generateToken(userName);
	}
	
	private String generateToken(String userName) {
		return Jwts.builder().setClaims(new HashMap<String,Object>())
				.setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis())) //start
				.setExpiration(new Date(System.currentTimeMillis() *3600000)) //expire
				.signWith(SignatureAlgorithm.HS512, secret) //Hash
				.compact();
	}
	
	public Boolean validate(String token, UserDetails userDetails) {
		return  getUserNameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public void setContext(UsernamePasswordAuthenticationToken token) {
		SecurityContext security = new SecurityContextImpl();
		security.setAuthentication(token);
		SecurityContextHolder.setContext(security);
	}
	
}
