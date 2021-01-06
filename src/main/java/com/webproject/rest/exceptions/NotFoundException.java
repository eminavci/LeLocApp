package com.webproject.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Chris on 6/28/14.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiRestException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotFoundException() {
    	super();
    	super.setHttpStatus(HttpStatus.NOT_FOUND);
    }
    public NotFoundException(Throwable cause) {
        super(cause);
        super.setHttpStatus(HttpStatus.NOT_FOUND);
    }
    public NotFoundException(String message){
    	super(message);
    	super.setHttpStatus(HttpStatus.NOT_FOUND);
    }
    public NotFoundException(String message, Throwable cause){
    	super(message, cause);
    	super.setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
