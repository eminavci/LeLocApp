package com.webproject.spring.i18n;

import java.util.Arrays;
import java.util.Locale;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class CustomLocaleResolver extends AcceptHeaderLocaleResolver{
	public static final String REQUEST_LOCALE_HEADER_NAME 		= "Accept-Language";
	public static final String CURRENT_LOCALE_ATTRIBUTE_NAME 	= "currentLocale";
	private Locale 		defaultLocale 		= null;
	private String[] 	supportedLocales 	= null;
	public CustomLocaleResolver(){}
	
	@Override
	public Locale resolveLocale(HttpServletRequest request){
		// Return current locale
		Locale currentLocale = (Locale) request.getAttribute(CURRENT_LOCALE_ATTRIBUTE_NAME);
		if (currentLocale != null){
			return currentLocale;
		}
		
		// Set new locale
		String newLocale = request.getHeader(REQUEST_LOCALE_HEADER_NAME);
		
		if (StringUtils.hasLength(newLocale)){
			try{
				// Get locale (Ex: "fr", "fr-fr")
				Locale locale = Locale.forLanguageTag(newLocale);
				// Force to the base language locale ("fr-fr" -> "fr")
				Locale.forLanguageTag(locale.getLanguage());
				
				// Set locale
				setLocale(request, null, locale);
				
				// Return
				return locale;
			}catch (Exception e){
				e.printStackTrace();
				// Do nothing
			}
		}
		
		// Return default locale
		return defaultLocale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale){
		// Return default locale
		if ((locale == null) || (Arrays.asList(supportedLocales).contains(locale.getLanguage()) == false)){
			request.setAttribute(CURRENT_LOCALE_ATTRIBUTE_NAME, defaultLocale);
			return;
		}
		// Set current locale
		request.setAttribute(CURRENT_LOCALE_ATTRIBUTE_NAME, locale);
	}

	public void setDefaultLocale(String defaultLocale){
		this.defaultLocale = StringUtils.parseLocaleString(defaultLocale);
	}

	public void setSupportedLocales(String ... supportedLocales){
		this.supportedLocales = supportedLocales;
	}

	public String[] getSupportedLocales(){
		return supportedLocales;
	}
}
