package com.power.services;

import org.springframework.http.ResponseEntity;

import com.power.models.User;

public interface AccountService {
	public ResponseEntity<String> userLogin(User user);
	public ResponseEntity<String> createUserAccount(User user);
	public ResponseEntity<String> logOutUser();
}
