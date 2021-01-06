package com.webproject.spring.service;

public interface MessageByLocaleService {
	
	public String getMessage(String name);
	public String getMessage(String name, Object...params);
	public String getMessage(String name, String defaultText);
	
}
