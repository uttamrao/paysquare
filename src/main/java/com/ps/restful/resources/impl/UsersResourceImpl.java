package com.ps.restful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.entities.global.Users;
import com.ps.restful.dto.adapter.request.UsersRequestDTOAdapter;
import com.ps.restful.dto.request.UsersRequestDTO;
import com.ps.restful.dto.response.JwtResponseDTO;
import com.ps.restful.resources.UsersResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.UsersService;

@RestController
@RequestMapping(path = UsersResource.RESOURCE_PATH)
public class UsersResourceImpl extends AbstractResourceImpl implements UsersResource {

	Logger logger = Logger.getLogger(UsersResourceImpl.class);
	
	/*
	 * @Autowired AuthenticationManager authenticationManager;
	 * 
	 * @Autowired JwtTokenUtil jwtTokenUtil;
	 */
    
	@Autowired
	UsersService usersService;

	/*
	 * @Autowired private JwtUserDetailsService userDetailsService;
	 */
	
	@Override
	public ResponseEntity<Response> add(UsersRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add user "
				+ "resource");
		
		UsersRequestDTOAdapter usersRequestDTOAdapter = new UsersRequestDTOAdapter();
		if(logger.isDebugEnabled()) logger.debug("Building user "+requestDTO.getName());
		Users users = usersRequestDTOAdapter.buildRequest(null,requestDTO);
		if(logger.isDebugEnabled()) logger.debug("Sending user entity to service method for saving into db  "+users.getName());
		usersService.add(users);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("User Created successfully").build());
	}

	@Override
	public ResponseEntity<Response> getAll() {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll users details "
				+ "resource");
				
		List usersList = (List) usersService.getAllUsers();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().results(usersList).build());
	}
	
	public ResponseEntity<Response> authenticateLogin(UsersRequestDTO requestDTO) throws AuthenticationException {

		String token = usersService.generateToken(requestDTO);
		
		/*
		 * UserDetails userDetails = userDetailsService
		 * .loadUserByUsername(requestDTO.getLogin());
		 * 
		 * String token = jwtTokenUtil.generateToken(userDetails);
		 * 
		 * return ResponseEntity.status(HttpStatus.CREATED).body(
		 * ResponseBuilder.builder().result(new JWTResponseDTO(token)).build());
		 */
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				 ResponseBuilder.builder().result(new JwtResponseDTO(requestDTO.getLogin(),token)).build());
		
	    }
	
}
