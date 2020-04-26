package com.power.messages;

public enum Message {
	INITAL_LOAD_COMPLETE("Users and Records loaded sucsesfully"),
	INITIAL_LOAD_FAIL("Users and Records Fialed to load correctly. Contact aadministrator"),
	INITIAL_LOAD_LOADING("Loading...."),
	LOGIN_FAILED("Invalid Login Credentials"),
	SERVER_ERROR("An error has occurred. Contact your administrator."),
	USER_CREATION_ERROR("Error creating user. Contact your administrator."),
	USER_CREATION_SUCSESS("User Account successfully created"),
	USER_EXISTS("User name already exists.")
	;
	
	private String message; 
	
	private Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
