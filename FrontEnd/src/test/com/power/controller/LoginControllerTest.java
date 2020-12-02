package com.power.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.power.front.controller.LoginController;
import com.sec.model.User;

@ContextConfiguration
//This is the key to getting this to run, otherwise null pointers 
//and all sorts of other errors that dont show up in log. 
@SpringBootTest(webEnvironment = RANDOM_PORT)
class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    
	private MockMvc mockMvc;
	private User user;
	private String json;
    
    @BeforeEach
    public void setup() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new User("user","password","hint");
        ObjectMapper obj = new ObjectMapper(); 
        json = obj.writeValueAsString(user);
    }
	@AfterEach
	void tearDown() throws Exception {
	}

    @Test

    public void contextLoads() throws Exception {
    	
    	MvcResult result = mockMvc.perform(post("/power/authorization/userLogin")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json))
    	.andExpect(status().isUnauthorized())
    	.andReturn();
    	
    	assertEquals(result.getResponse().getContentAsString(), "Invalid Login Credentials");
    	
    }

}
