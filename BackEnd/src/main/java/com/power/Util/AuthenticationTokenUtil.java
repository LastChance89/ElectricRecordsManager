package com.power.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.power.models.User;

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
	
	public List<SimpleGrantedAuthority> getRoles(String token) {
		List<SimpleGrantedAuthority> roleList = new ArrayList<SimpleGrantedAuthority>();
		@SuppressWarnings("unchecked")
		ArrayList<Object> roles =  (ArrayList<Object>) getAllClaimsFromToken(token).get("roles");
		for(Object role: roles) {
			roleList.add(new SimpleGrantedAuthority(String.valueOf(role)));
		}
		return roleList;
	}
	
	public Boolean isTokenExpired(String token) {
		Date expiration = getExperationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String createToken(User user) {
		return Jwts.builder().setClaims(new HashMap<String,Object>(){{put("roles",user.getRoles());}})
				.setSubject(user.getUserName()).setIssuedAt(new Date(System.currentTimeMillis())) //start
				.setExpiration(new Date(System.currentTimeMillis() +900000)) //expire
				.signWith(SignatureAlgorithm.HS512, secret) //Hash
				.compact();
	}
	
	public String createToken(String userName, List<SimpleGrantedAuthority> roleList) {
		return Jwts.builder().setClaims(new HashMap<String,Object>(){{put("roles",roleList);}})
				.setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis())) //start
				.setExpiration(new Date(System.currentTimeMillis() +900000)) //expire
				.signWith(SignatureAlgorithm.HS512, secret) //Hash
				.compact();
	}
	
	
	//@TODO: Fix this. 
	public Boolean validate(String token, User user) {
		return  getUserNameFromToken(token).equals(user.getUserName()) && !isTokenExpired(token);
	}
	
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//Might not be needed. Verify and check. 
	public void setContext(UsernamePasswordAuthenticationToken token) {
		SecurityContext security = new SecurityContextImpl();
		security.setAuthentication(token);
		SecurityContextHolder.setContext(security);
	}
	
}
