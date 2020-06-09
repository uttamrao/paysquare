package com.ps.restful.resources.filter;

import java.util.ArrayList;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ps.entities.global.Users;
import com.ps.services.UsersService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	Logger logger = Logger.getLogger(JwtUserDetailsService.class);
	
	@Autowired
	UsersService usersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(logger.isDebugEnabled()) logger.debug("In loadUserByUsername finding user "+username+" in DB "); 
		Users user = usersService.getUserByLogin(username);
		if(logger.isDebugEnabled()) logger.debug("In loadUserByUsername user found in DB "+user);
		return 	new User(user.getLogin(), user.getName(), new ArrayList<>());		
	}
}
