package com.appsdeveloperblog.app.ws.exceptions;

public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7751721099180481163L;
	private String customException;
	
	public UserServiceException(String exception) {
		super(exception);
		this.customException = exception;
		
	}

}
