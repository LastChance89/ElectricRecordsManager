package com.power.front.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sec.security.SecurityConfiguration;

//Reference. 
public class WebServletConfiguration implements WebApplicationInitializer
{
	
	  @Override public void onStartup(ServletContext context) throws
	  ServletException {
	  
	  AnnotationConfigWebApplicationContext webAppContext = new
	  AnnotationConfigWebApplicationContext();
	  webAppContext.register(SecurityConfiguration.class);
	  webAppContext.setServletContext(context);
	  ServletRegistration.Dynamic servlet = context.addServlet("dispatcher", new DispatcherServlet(webAppContext));
	  
	 // servlet.setLoadOnStartup(1); servlet.addMapping("/power/*");
	  
	  }
	 

}
