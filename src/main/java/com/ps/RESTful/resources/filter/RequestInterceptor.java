package com.ps.RESTful.resources.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ps.config.tenant.TenantContextHolder;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(RequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		if (logger.isDebugEnabled())
			logger.debug("In RequestInterceptor, we are intercepting request-> " + request.getRequestURI());
		if (logger.isDebugEnabled())
			logger.debug("____________________________________________");

		String tenantId = request.getHeader("X-TenantID");
		if (logger.isDebugEnabled())
			logger.debug("RequestURI::" + request.getRequestURI() + " || Current X-TenantID-> " + tenantId);

		if (tenantId != null) {
			TenantContextHolder.setTenantId(tenantId);
		}
				return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("In RequestInterceptor, post handle method clearing tenant from context for -> "
					+ request.getRequestURI());
		TenantContextHolder.clear();
	}

}
