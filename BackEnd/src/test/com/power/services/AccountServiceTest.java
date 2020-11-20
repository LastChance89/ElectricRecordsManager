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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.dao.UserDao;
import com.sec.util.AuthenticationTokenUtil;
import com.global.message.Message;
import com.power.services.Impl.AccountServiceImpl;
import com.sec.model.User;

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
	private UsernamePasswordAuthenticationToken upToken;

	@Before
	public void setUp() throws Exception {
		// accountService = new AccountServiceImpl();
		user = new User("user", "password", "hint");
		ObjectMapper obj = new ObjectMapper();
		json = obj.writeValueAsString(user);
		upToken = new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void userLoginSucsessTest() throws Exception {
		PowerMockito.when(accountService, "authenticate", user).thenReturn(true);
		ResponseEntity<?> response = accountService.userLogin(user);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void userLoginFailTest() throws Exception {
		PowerMockito.when(accountService, "authenticate", user).thenReturn(false);
		ResponseEntity<String> response = accountService.userLogin(user);
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], Message.LOGIN_FAILED.getMessage());
	}

	@Test
	public void userCreationSucsessTest() throws Exception {
		Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(false);
		Mockito.when(userDao.createNewUser(user)).thenReturn(true);
		ResponseEntity<String> response = accountService.createUserAccount(user);
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], Message.USER_CREATION_SUCSESS.getMessage());
	}

	@Test
	public void userCreationFailTest() throws Exception {
		Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(false);
		Mockito.when(userDao.createNewUser(user)).thenReturn(false);
		ResponseEntity<String> response = accountService.createUserAccount(user);
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], Message.USER_CREATION_ERROR.getMessage());
	}

	@Test
	public void userCreationFailWhenExistsTest() {
		Mockito.when(userDao.checkUserNameExists(Mockito.anyString())).thenReturn(true);
		ResponseEntity<String> response = accountService.createUserAccount(user);
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], Message.USER_EXISTS.getMessage());
	}

	@Test
	public void userLogoutSucsess() {
		SecurityContextHolder.getContext().setAuthentication(upToken);
		ResponseEntity<String> response = accountService.logOutUser();
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], Message.USER_LOGGED_SUCSESS.getMessage());
	}

	@Test
	public void testUserGetHintSucsess() {
		Mockito.when(userDao.getPasswordHint(user.getUserName())).thenReturn("hint");
		ResponseEntity<String> response = accountService.getPasswordHint(user.getUserName());
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(responseBody[0], "message");
		assertEquals(responseBody[1], "hint");
	}

	@Test
	public void testUserGetHintNoUserFound() {
		Mockito.when(userDao.getPasswordHint(user.getUserName())).thenReturn("");
		ResponseEntity<String> response = accountService.getPasswordHint(user.getUserName());
		String[] responseBody = setupResponseBodyValue(response);
		assertEquals(responseBody[0], "error");
		assertEquals(responseBody[1], Message.USER_NOT_FOUND.getMessage());
	}

	private String[] setupResponseBodyValue(ResponseEntity<String> response) {
		return response.getBody().replace("{", "").replace("}", "").replaceAll("\"", "").split(":");
	}
}
