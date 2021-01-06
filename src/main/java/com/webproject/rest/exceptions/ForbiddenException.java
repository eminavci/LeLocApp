package com.webproject.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Chris on 10/19/14.
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN)
public class ForbiddenException extends ApiRestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ForbiddenException() {
    	super();
    	super.setHttpStatus(HttpStatus.FORBIDDEN);
    }
    public ForbiddenException(Throwable cause) {
        super(cause);
        super.setHttpStatus(HttpStatus.FORBIDDEN);
    }
    public ForbiddenException(String message){
    	super(message);
    	super.setHttpStatus(HttpStatus.FORBIDDEN);
    }
    public ForbiddenException(String message, Throwable cause){
    	super(message, cause);
    	super.setHttpStatus(HttpStatus.FORBIDDEN);
    }
}
