package com.webproject.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Chris on 6/30/14.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiRestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BadRequestException() {
    	super();
    	super.setHttpStatus(HttpStatus.BAD_REQUEST);
    }
    public BadRequestException(Throwable cause) {
        super(cause);
        super.setHttpStatus(HttpStatus.BAD_REQUEST);
    }
    public BadRequestException(String message){
    	super(message);
    	super.setHttpStatus(HttpStatus.BAD_REQUEST);
    }
    public BadRequestException(String message, Throwable cause){
    	super(message, cause);
    	super.setHttpStatus(HttpStatus.BAD_REQUEST);
    }
}
