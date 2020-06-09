package com.ps.restful.error.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ps.dto.ErrorDTO;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.restful.util.StringUtils;
import com.ps.services.constants.ErrorMessageConstants;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);
	
	@Autowired
	private Environment env;
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception exception) {
		return handleExceptionInternal(exception, null, null, null, null);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Response> handleBusinessException(BusinessException businessException) {
		if(logger.isDebugEnabled()) logger.debug("Handling business exception : " + businessException.getMessage());
		
		return new ResponseEntity<Response>(ResponseBuilder.builder()
				.error(new ErrorDTO(businessException.getErrorCode().getCode(), businessException.getErrorCode().name(), ErrorMessageConstants.ERROR_SOMETHING_WENT_WRONG))
				.build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<Response> handleInvalidRequestException(InvalidRequestException invRequestException) {
		if(logger.isDebugEnabled()) logger.debug("Handling invalid request exception : " + invRequestException.getMessage());
		
		return new ResponseEntity<Response>(ResponseBuilder.builder()
				.error(new ErrorDTO(invRequestException.getErrorCode().getCode(), invRequestException.getErrorCode().name(), invRequestException.getDescription()))
				.build(),
				HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
		if(logger.isDebugEnabled()) logger.debug("Handling resource not found exception : " + resourceNotFoundException.getMessage());
		
		ErrorDTO errorDTO = null;
		
		if (StringUtils.isValidString(resourceNotFoundException.getDescription())) {
			errorDTO = new ErrorDTO(resourceNotFoundException.getErrorCode().getCode(), resourceNotFoundException.getErrorCode().name(),
					resourceNotFoundException.getDescription());
		} else {
			errorDTO = new ErrorDTO(resourceNotFoundException.getErrorCode().getCode(), resourceNotFoundException.getErrorCode().name(),
					ErrorMessageConstants.ERROR_BAD_REQUEST);
		}
		
		return new ResponseEntity<Response>(ResponseBuilder.builder().error(errorDTO).build(), HttpStatus.NOT_FOUND);
	}
	
	
	//############### Overridden methods go here ###############
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		if(logger.isDebugEnabled()) {
			logger.debug("Handling method argument not valid exception : " + methodArgumentNotValidException.getMessage());
		}
		
		List<ErrorDTO> errorDTOs = new ArrayList<ErrorDTO>();
		
		List<ObjectError> objectErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
		
		if(logger.isDebugEnabled()) logger.debug("Number of objectErrors: "+ objectErrors.size());
		
		for (ObjectError objectError : objectErrors) {
			errorDTOs.add(
					new ErrorDTO(
							ErrorCode.INVALID_PARAMETER.getCode(), 
							ErrorCode.INVALID_PARAMETER.name(), 
							objectError.getDefaultMessage()));	
		}
		
		return new ResponseEntity<Object>(ResponseBuilder.builder().errors(errorDTOs).build(),HttpStatus.BAD_REQUEST);
	}
	
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception exception, 
			@Nullable Object body, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		ErrorDTO errorDTO = null;
		String message = exception.getMessage();
		
		if(exception instanceof HttpMessageNotReadableException || 
				exception instanceof MissingServletRequestParameterException || 
				exception instanceof UnsupportedOperationException ||
				exception instanceof MethodArgumentTypeMismatchException) {
			if(logger.isDebugEnabled()) {
				logger.debug("Handling " + exception.getClass().getSimpleName() + ": " + message);
			}
			errorDTO = new ErrorDTO(
					ErrorCode.BAD_REQUEST.getCode(),
					message,
					ErrorMessageConstants.ERROR_BAD_REQUEST);
			return new ResponseEntity<>(ResponseBuilder.builder().error(errorDTO).build(), HttpStatus.BAD_REQUEST);
		}
		logger.error("Handling exception internal: " + exception.getClass() + " " + message, exception);
		errorDTO = new ErrorDTO(
				ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
				message,
				ErrorMessageConstants.ERROR_SOMETHING_WENT_WRONG);
		return new ResponseEntity<Object>(ResponseBuilder.builder().error(errorDTO).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
