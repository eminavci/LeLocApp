package com.webproject.spring.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.webproject.spring.service.MessageByLocaleService;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService{

	@Autowired
	 private MessageSource messageSource;
	
	@Override
	public String getMessage(String name) {
		 Locale locale = LocaleContextHolder.getLocale();
	     return  messageSource.getMessage(name,null,name, locale);
	}

	@Override
	public String getMessage(String name, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
	     return messageSource.getMessage(name,params, name, locale);
	}

	@Override
	public String getMessage(String name, String defaultText) {
		 Locale locale = LocaleContextHolder.getLocale();
	     return  messageSource.getMessage(name,null,defaultText, locale);
	}

}
