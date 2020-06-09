package com.ps.restful.error.handler;

public class InvalidRequestException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public InvalidRequestException(ErrorCode errorCode, String description) {
		super(errorCode, description);
	}
}
