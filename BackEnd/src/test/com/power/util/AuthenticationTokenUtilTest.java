package com.power.util;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.power.Util.AuthenticationTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthenticationTokenUtilTest {
	
	
	private AuthenticationTokenUtil authenticationTokenUtil;

	

	
	@Before
	public void setUp() throws Exception {
		authenticationTokenUtil = new AuthenticationTokenUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTokenAndVerifyTest() {
		/*
		//String token = authenticationTokenUtil.createToken("TestUser");
		
		String userName = authenticationTokenUtil.getUserNameFromToken(token);
		
		assertEquals(userName,"TestUser");
		
		//Only need to confirm the experation date has been created. 
		assertNotNull(authenticationTokenUtil.getExperationDateFromToken(token));
		
		assertFalse(authenticationTokenUtil.isTokenExpired(token));
		
		//need to modify the validation, current validation is silly. 
		//authenticationTokenUtil.validate(token, userDetails)
		 * */
		 
	}

	public void getExperationDateFromTokenTest() {
		
	}
	
	
	
	public void validateTest() {
		
	}
	
	public void getAllAlaimsFromTokenTest() {
		
	}

}
