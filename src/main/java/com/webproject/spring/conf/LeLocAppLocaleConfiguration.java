package com.webproject.spring.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import com.webproject.spring.i18n.CustomLocaleResolver;

@Configuration
public class LeLocAppLocaleConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(LeLocAppLocaleConfiguration.class);
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public LocaleResolver localeResolver(){
		CustomLocaleResolver localeResolver = new CustomLocaleResolver();
		localeResolver.setDefaultLocale("en");
		localeResolver.setSupportedLocales("en", "fr");
		logger.info("localeResolver set locate settings");
        return localeResolver;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
	    messageSource.setCacheSeconds(3600); //refresh cache once per hour
	    return messageSource;
	}
}
