package com.ps.services;

import java.util.List;

import com.ps.entities.global.Users;
import com.ps.restful.dto.request.UsersRequestDTO;

public interface UsersService {

	public void add(Users users);
	
	public Users getUserByLogin(String login);
	
	public List<Users> getAllUsers();
	
	public String generateToken(UsersRequestDTO requestDTO);
	
	public void deleteById(int id);

}
