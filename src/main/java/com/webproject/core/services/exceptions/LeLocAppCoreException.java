package com.webproject.core.services.exceptions;

public abstract class LeLocAppCoreException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public int errorCode = 100; // General system error;
	public Object[] params = new Object[0];

	public LeLocAppCoreException(){
		super();
	}
	public LeLocAppCoreException(Throwable cause) {
        super(cause);
    }

	public LeLocAppCoreException(String message, Throwable cause) {
        super(message, cause);
    }

	public LeLocAppCoreException(String message) {
        super(message);
    }

	public LeLocAppCoreException(int code){
    	super();
    	this.errorCode = code;
    }
	
	public LeLocAppCoreException(int code, Object...pars){
    	super();
    	this.errorCode = code;
    	this.params = pars;
    }
	
	public LeLocAppCoreException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public LeLocAppCoreException(int errorCode, Throwable cause){
    	super(cause);
    	this.errorCode = errorCode;
    }
    
	public LeLocAppCoreException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
}
