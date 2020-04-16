package com.power.messages;

public enum Message {
	INITAL_LOAD_COMPLETE("Users and Records loaded sucsesfully"),
	INITIAL_LOAD_FAIL("Users and Records Fialed to load correctly. Contact aadministrator"),
	INITIAL_LOAD_LOADING("Loading...."),
	LOGIN_FAILED("Invalid Login Credentials"),
	SERVER_ERROR("An error has occurred, contact your administrator")
	;
	
	private String message; 
	
	private Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
