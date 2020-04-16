package com.power.front.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.power.front.spring.userDetails.CustomUserDetailsService;

import com.power.Util.AuthenticationTokenUtil;


@Component
public class RequestFilter extends OncePerRequestFilter {

	private final Logger logger = LogManager.getLogger(RequestFilter.class);
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationTokenUtil authUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String userName = null;
		
		UserDetails userDetails = null;
	
		//We know the user is logged in and good to go. 
		//Verify this is working with the logged in user, not just what ever user is pinging the server. 
		if(SecurityContextHolder.getContext().getAuthentication() != null || request.getHeader("Authorization") == null) {
			filterChain.doFilter(request,response);	
		}
		else {
			
		String tokenHeader = request.getHeader("Authorization");
		
		try {
			//Make the user name token in order to 
			userName = authUtil.getUserNameFromToken(tokenHeader);
		}
		catch(Exception e) {
			//@TODO: Add log4j here. 
			logger.error("ERROR: ",e);
			
		}
		
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) 
		{ 
			userDetails =userDetailsService.loadUserByUsername(userName); 
		}
		 
		if(authUtil.validate(tokenHeader, userDetails)) {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
					userDetails,null,userDetails.getAuthorities());
			upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(upToken);
		}
	
		filterChain.doFilter(request,response);	
		}
	}


}
