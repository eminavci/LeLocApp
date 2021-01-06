package com.webproject.rest.exceptions;

import org.springframework.http.HttpStatus;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.services.exceptions.LeLocAppCoreException;

public class ApiRestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errCode;
	private Object params[];
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
	protected ApiRestException() {
    	super();
    	this.errCode = ErrKeys.GENERAL_INTERNAL_ERROR;	
    	this.params = new Object[0];
	}
	public ApiRestException(Throwable cause) {
        super(cause);

        if(cause instanceof LeLocAppCoreException){
    		this.errCode = ((LeLocAppCoreException) cause).getErrorCode();
    		this.params = ((LeLocAppCoreException) cause).getParams();
    	} else {
    		this.errCode = ErrKeys.GENERAL_INTERNAL_ERROR;
    		this.params = new Object[0];
    	}
    }
	protected ApiRestException(String message){
    	super(message);
    	this.errCode = ErrKeys.GENERAL_INTERNAL_ERROR;
    	this.params = new Object[0];
    }
    
	protected ApiRestException(String message, Throwable cause){
    	super(message, cause);
    	if(cause instanceof LeLocAppCoreException){
    		this.errCode = ((LeLocAppCoreException) cause).getErrorCode();
    		this.params = ((LeLocAppCoreException) cause).getParams();
    	} else {
    		this.errCode = ErrKeys.GENERAL_INTERNAL_ERROR;
    		this.params = new Object[0];
    	}
    }
    
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
