package com.webproject.core.services.exceptions;

public class RealEstateNotFound extends LeLocAppCoreException {

	private static final long serialVersionUID = 1L;

	public RealEstateNotFound() {
		super();
	}
	
	public RealEstateNotFound(String message){
		super(message);
	}
	
	public RealEstateNotFound(int errorCode, Object...pars){
		super(errorCode, pars);
	}
	
	public RealEstateNotFound(int errorCode, Throwable cause){
		super(errorCode, cause);
	}
	
	public RealEstateNotFound(String message, Throwable cause){
		super(message, cause);
	}
	public RealEstateNotFound(String message, int errorCode){
		super(message, errorCode);
	}
	
}
