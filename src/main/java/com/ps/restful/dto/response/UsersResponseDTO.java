package com.ps.restful.dto.response;

import com.ps.dto.UsersDTO;

public class UsersResponseDTO extends UsersDTO  {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
