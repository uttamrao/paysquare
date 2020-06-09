package com.ps.services.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ps.entities.global.Users;
import com.ps.restful.dto.request.UsersRequestDTO;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.restful.resources.filter.JwtTokenUtil;
import com.ps.restful.resources.filter.JwtUserDetailsService;
import com.ps.restful.util.StringUtils;
import com.ps.services.UsersService;
import com.ps.services.repository.global.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	Logger logger = Logger.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired 
	private JwtUserDetailsService jwtUserDetailsService;	
	
	@Autowired
    JwtTokenUtil jwtTokenUtil;
    
	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public void add(Users users) {
		
		if(logger.isDebugEnabled()) logger.debug("In add users "
				+ "service method adding user details into databse "+users);		
		if(users == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid request");
		
		users.setPassword(bcryptEncoder.encode(users.getPassword()));		
		
		usersRepository.save(users);		
	}

	@Override
	public List<Users> getAllUsers() {
		if(logger.isDebugEnabled()) logger.debug("In getAll Users "
				+ "service method retrieving all Users details from databse");
		
		return usersRepository.findAll();
		
	}

	@Override
	public Users getUserByLogin(String login) {
		
		if(logger.isDebugEnabled()) logger.debug("In getUserByLoginAndPassword "
				+ " for login-> "+login);		
		if(!StringUtils.isValidString(login)) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Login Id!");
				
		Optional<Users> optionalUser = usersRepository.findByLogin(login);		
		if(!optionalUser.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "User not found!");

		return optionalUser.get();
	}

	@Override
	public String generateToken(UsersRequestDTO requestDTO) {		
			
		if(logger.isDebugEnabled()) logger.debug("In autenticateLogin for login-> "+requestDTO.getLogin());		
		UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername(requestDTO.getLogin());

		return jwtTokenUtil.generateToken(userDetails);

	}

	@Override
	public void deleteById(int id) {
		
		if(logger.isDebugEnabled()) logger.debug("In deleteById user "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid User Id! ");
		usersRepository.deleteById(id);
	}

}
