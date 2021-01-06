package com.webproject.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Chris on 6/30/14.
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class ConflictException extends ApiRestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConflictException() {
    	super();
    	super.setHttpStatus(HttpStatus.CONFLICT);
    }
    public ConflictException(Throwable cause) {
        super(cause);
        super.setHttpStatus(HttpStatus.CONFLICT);
    }
    public ConflictException(String message){
    	super(message);
    	super.setHttpStatus(HttpStatus.CONFLICT);
    }
    public ConflictException(String message, Throwable cause){
    	super(message, cause);
    	super.setHttpStatus(HttpStatus.CONFLICT);
    }
}
