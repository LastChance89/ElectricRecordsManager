package com.power.services;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Messages;
import com.power.DAO.UserDao;
import com.power.Util.AuthenticationTokenUtil;
import com.power.messages.Message;
import com.power.models.User;
import com.power.services.Impl.AccountServiceImpl;

@RunWith(PowerMockRunner.class)
public class AccountServiceTest {

	
	@Mock 
	private AuthenticationTokenUtil authenticationTokenUtil;
	
	@Mock 
	private UserDao userDao;
	
	@InjectMocks
	private AccountServiceImpl accountService;
	

	
	private User user;
	private String json;
	
	@Before
	public void setUp() throws Exception {
		//accountService = new AccountServiceImpl();
		user = new User("user", "password", "hint");
		ObjectMapper obj = new ObjectMapper();
		json = obj.writeValueAsString(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void userLoginSucsessTest() throws Exception {
		  PowerMockito.when(accountService, "authenticate",user).thenReturn(true);
		  ResponseEntity<?> response = accountService.userLogin(user);
		  assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void userLoginFaukTest() throws Exception {
		  PowerMockito.when(accountService, "authenticate",user).thenReturn(false);
		  ResponseEntity<?> response = accountService.userLogin(user);
		  assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
		  assertEquals(response.getBody(),Message.LOGIN_FAILED.getMessage());
	}

	@Test
	public void userCreationSucsessTest() throws Exception {
		  Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(false);
		  Mockito.when(userDao.createNewUser(user)).thenReturn(true);
		  
		  ResponseEntity<?> response = accountService.createUserAccount(user);
		  
		  assertEquals(response.getStatusCode(), HttpStatus.OK);
		  assertEquals(response.getBody(),Message.USER_CREATION_SUCSESS.getMessage());
	}
	
	@Test
	public void userCreationFailTest() throws Exception {
		  Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(false);
		  Mockito.when(userDao.createNewUser(user)).thenReturn(false);
		  
		  ResponseEntity<?> response = accountService.createUserAccount(user);
		  
		  assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		  assertEquals(response.getBody(),Message.USER_CREATION_ERROR.getMessage());
	}
	
	@Test
	public void userExistsTest() {
		  Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(true);

		  ResponseEntity<?> response = accountService.createUserAccount(user);
		  
		  assertEquals(response.getStatusCode(), HttpStatus.OK);
		  assertEquals(response.getBody(),Message.USER_EXISTS.getMessage());
	}
	
	
}
