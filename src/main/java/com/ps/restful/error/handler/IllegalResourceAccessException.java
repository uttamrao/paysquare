package com.ps.restful.error.handler;

public class IllegalResourceAccessException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public IllegalResourceAccessException(ErrorCode errorCode, String description) {
		super(errorCode, description);
	}
}
