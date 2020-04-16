package com.power.front.spring.userDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.power.services.MainService;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private MainService mainService;
	 
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> userRoles = mainService.getRoles(userName);
		return new User(userName, "", userRoles);
	}	
}
