package com.power.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.message.Message;

public class ResponseEntityUtil {

	private static final Logger logger = LogManager.getLogger(ResponseEntityUtil.class);
			
	public static ResponseEntity<String> createValidResponse(Map<String,String> responseBody){
		return validResponse().body(formatMessageBody(responseBody));		 
	}

	public static ResponseEntity<String> createValidResponse(List<?> response){
		return validResponse().body(formatMessageBody(response));
	}

	public static ResponseEntity<String> createResponseMessage(HttpStatus httpStatus,String message){
		return ResponseEntity.status(httpStatus).body(formatMessageBody(message));
	}
	
	//used for displaying error messages, and at somepoint maybe debug message if we were to 
	//implment a developer mode. 
	public static ResponseEntity<String> createResponseMessage(String message){
		return validResponse().body(formatMessageBody(message));
	}
	
	//Remove all the ResponseEntity.ok() with a single method.  
	private static BodyBuilder validResponse() {
		return ResponseEntity.ok();
	}
	
	//Generic error that something has gone wrong on the server. 
	public static ResponseEntity<String> InternalResponseError(){
		return ResponseEntityUtil.createResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, Message.SERVER_ERROR.getMessage());
	}
	
	/*
	 * formatMessageBody methods are used to convert incoming objects into a JSON readable format 
	 * for the front end. 
	 */
	@SuppressWarnings("serial")
	private static String formatMessageBody(String message) {
		String mapToStringValue = null; 
		try {
			mapToStringValue =new ObjectMapper().writeValueAsString(new HashMap<String,String>(){{put("message",message);}});
		} catch (JsonProcessingException e) {
			logger.error("ERROR", e);
		}
		return mapToStringValue;
	}
	
	private static String formatMessageBody(Map<String,String> responseBody) {
		String mapToStringValue = null; 
		try {
			mapToStringValue =new ObjectMapper().writeValueAsString(responseBody);
		} catch (JsonProcessingException e) {
			logger.error("ERROR", e);
		}
		return mapToStringValue;
	}
	
	
	private static String formatMessageBody(List<?> responseBody) {
		String mapToStringValue = null; 
		try {
			mapToStringValue =new ObjectMapper().writeValueAsString(responseBody);
		} catch (JsonProcessingException e) {
			logger.error("ERROR", e);
		}
		return mapToStringValue;
	}
	
}
