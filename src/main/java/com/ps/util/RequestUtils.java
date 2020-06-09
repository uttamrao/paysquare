package com.ps.util;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class RequestUtils {

	static Logger logger = Logger.getLogger(RequestUtils.class);
	
	@Autowired
	protected  HttpServletRequest request;
	
	@RequestScope
	public String getUserName() {
		
		if(logger.isDebugEnabled()) logger.debug("In getUserName method retrieving username from request");
			
		Object userNameObject = request.getAttribute(Constants.USER_NAME);	
		if(logger.isDebugEnabled()) logger.debug("In getUserName username-> "+userNameObject);
		if(userNameObject != null ) return (String) userNameObject;
		
		return null;		
	}
	
}
