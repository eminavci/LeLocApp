package com.webproject.core.services.exceptions;

public class UserException extends LeLocAppCoreException{
	
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
	}
	
	public UserException(String message){
		super(message);
	}
	
	public UserException(int errorCode, Object...pars){
		super(errorCode, pars);
	}
	
	public UserException(int errorCode, Throwable cause){
		super(errorCode, cause);
	}
	
	public UserException(String message, Throwable cause){
		super(message, cause);
	}
	public UserException(String message, int errorCode){
		super(message, errorCode);
	}

}
