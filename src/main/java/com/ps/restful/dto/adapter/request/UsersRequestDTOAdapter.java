package com.ps.restful.dto.adapter.request;

import com.ps.dto.UsersDTO;
import com.ps.entities.global.Users;

public class UsersRequestDTOAdapter {

	public Users buildRequest(Users user,UsersDTO usersDTO) {
		
		if(usersDTO == null) return null;
		if(user == null) user = new Users();
		
		user.setName(usersDTO.getName());
		user.setEmail(usersDTO.getEmail());
		user.setMobile(usersDTO.getMobile());
		user.setLogin(usersDTO.getLogin());
		user.setPassword(usersDTO.getPassword());
		
		return user;
	}
	
}
