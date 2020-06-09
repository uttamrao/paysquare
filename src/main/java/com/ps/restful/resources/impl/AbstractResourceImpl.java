package com.ps.restful.resources.impl;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.ps.restful.resources.AbstractResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.services.constants.Constants;
import com.ps.services.constants.ErrorMessageConstants;

//every resource implementing this class will follow a standard format of get/delete method 
//common methods for accessing request can be made here which will be accessible in all the resources
//this approach promotes reusability of code as there will be common methods which will prevent resource pollution 
public abstract class AbstractResourceImpl implements AbstractResource {

	static Logger logger = Logger.getLogger(AbstractResourceImpl.class);
	
	@Autowired
	protected HttpServletRequest request;
	
	public ResponseEntity<Response> get(@PathVariable("resourceId") int resourceId){
		 throw new UnsupportedOperationException(ErrorMessageConstants.ERROR_METHOD_NOT_IMPLEMENTED);		
	}	
	
	public ResponseEntity<Response> delete(@PathVariable("resourceId") int resourceId){
		 throw new UnsupportedOperationException(ErrorMessageConstants.ERROR_METHOD_NOT_IMPLEMENTED);		
	}
	
	public String getUserName() {
		
		if(logger.isDebugEnabled()) logger.debug("In getUserName method retrieving username from request");
			
		Object userNameObject = request.getAttribute(Constants.USER_NAME);	
		if(logger.isDebugEnabled()) logger.debug("In getUserName username-> "+userNameObject);
		if(userNameObject != null ) return (String) userNameObject;
		
		return null;		
	}
}
