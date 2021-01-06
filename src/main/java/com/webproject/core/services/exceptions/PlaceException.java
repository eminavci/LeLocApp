package com.webproject.core.services.exceptions;

public class PlaceException extends LeLocAppCoreException{
	private static final long serialVersionUID = 1L;

	public PlaceException() {
		super();
	}
	
	public PlaceException(String message){
		super(message);
	}
	
	public PlaceException(String message, int errorCode){
		super(message, errorCode);
	}
	
	public PlaceException(int errorCode, Object...pars){
		super(errorCode, pars);
	}
	
	public PlaceException(int errorCode, Throwable cause){
		super(errorCode, cause);
	}
	
	public PlaceException(String message, Throwable cause){
		super(message, cause);
	}
}
