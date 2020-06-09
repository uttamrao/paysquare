package com.ps.restful.dto.adapter.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ps.dto.EmployeeAddressDTO;
import com.ps.dto.UsersDTO;
import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.global.Users;
import com.ps.restful.dto.response.UsersResponseDTO;

public class UsersResponseDTOAdapter {

	public UsersResponseDTO buildResponse(Users users) {
		
		if(users == null) return null;
		
		UsersResponseDTO userDTO = new UsersResponseDTO();
		userDTO.setEmail(users.getEmail());
		userDTO.setMobile(users.getMobile());
		userDTO.setLogin(users.getLogin());
		userDTO.setPassword(users.getPassword());
		
		return userDTO;
	}
	
	public List<UsersResponseDTO> buildResponseList(List<Users> usersList) {		
		
		if(CollectionUtils.isEmpty(usersList)) new ArrayList<UsersResponseDTO>();
		
		List<UsersResponseDTO> usersDTOList = new ArrayList<UsersResponseDTO>();

		for (Users user : usersList) {
			UsersResponseDTO userDTO = buildResponse(user);
			if(userDTO != null) usersDTOList.add(userDTO);
		}
		
		return usersDTOList;
	}
	
}
