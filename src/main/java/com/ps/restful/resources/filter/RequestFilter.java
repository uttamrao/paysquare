package com.ps.restful.resources.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.dto.ErrorDTO;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;

/*@Component
@Order(1)*/
public class RequestFilter /* implements Filter */ {
	/*
	 * private static final Logger logger =
	 * LogManager.getLogger(RequestFilter.class);
	 * 
	 * @Override public void doFilter(ServletRequest request, ServletResponse
	 * response, FilterChain chain) throws IOException, ServletException {
	 * if(logger.isDebugEnabled())logger.debug("In request filter");
	 * 
	 * 
	 * HttpServletRequest httpRequest = (HttpServletRequest)request;
	 * HttpServletResponse httpResponse = (HttpServletResponse)response;
	 * 
	 * 
	 * 
	 * //request.get
	 * 
	 * ErrorDTO errorDTO = new
	 * ErrorDTO(ErrorCode.UNAUTHORIZED.getCode(),"Unauthorized 123"); Response
	 * errorResponse = ResponseBuilder.builder().error(errorDTO).build(); byte []
	 * formattedErorResponse = formatResponse(errorResponse);
	 * httpResponse.setStatus(HttpStatus.OK.value());
	 * httpResponse.getOutputStream().write(formattedErorResponse);
	 * 
	 * chain.doFilter(request, response); }
	 * 
	 * private byte[] formatResponse(Response response) throws IOException { String
	 * serealized = new ObjectMapper().writeValueAsString(response); return
	 * serealized.getBytes(); }
	 */}
