package main.java.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.power.front.filter.RequestFilter;

public class SpringSecurityTest {
	

	
	@Autowired 
	 private RequestFilter requestFilter;

	private MockMvc mvc;
	  
	@BeforeEach
	public void setup() {
		
	}
	
	
	@Test
	public void testSecurityContextHolder() throws Exception{
		//ServletRequest request, ServletResponse response, FilterChain filterChain
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse rep = mock(HttpServletResponse.class);
		FilterChain fChain = mock(FilterChain.class);
		
		when(req.getHeader("authorization")).thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrc21pdHciLCJleHAiOjU2OTg4NjcxMTgzNTMyMDAsImlhdCI6MTU4MzAxODY0M30.6efTZBiW2-48-06kPdyeELsaloFk2gKwWdOrQcBLmwj64tfjQysjjclDeu9fYfH980i8t9_rGFLC5p11Djzg4A");
		
		mvc.perform(post("/power/checkLogin/setContext")
				.contentType(MediaType.APPLICATION_JSON)).
		andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
