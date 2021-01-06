package com.webproject.core.services.exceptions;

public class CategoryException  extends LeLocAppCoreException{
	private static final long serialVersionUID = 1L;

	public CategoryException() {
		super();
	}
	
	public CategoryException(String message){
		super(message);
	}
	
	public CategoryException(String message, int errorCode){
		super(message, errorCode);
	}
	
	public CategoryException(int errorCode, Object...pars){
		super(errorCode, pars);
	}
	
	public CategoryException(int errorCode, Throwable cause){
		super(errorCode, cause);
	}
	
	public CategoryException(String message, Throwable cause){
		super(message, cause);
	}
}
