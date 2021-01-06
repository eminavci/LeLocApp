package com.webproject;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.webproject.spring.common.Keys;
import com.webproject.spring.common.LELOCAPP;
import com.webproject.spring.conf.LeLocAppLocaleConfiguration;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class, LeLocAppSecurityConfiguration.class })
@EnableTransactionManagement
@Import({LeLocAppLocaleConfiguration.class})
@EnableAutoConfiguration
@EnableCaching
public class LeLocAppApplication implements Keys{
	
	private static final Logger logger = LoggerFactory.getLogger(LeLocAppApplication.class);
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				ServletContext sContext = sce.getServletContext();
				logger.info("LeLocApp-ServletContext initilized with following params: ");
				String s = "";
				s += "\n CONTEXT PATH : " + sContext.getContextPath();
				s += "\n SERVLET INFO : " + sContext.getServerInfo();
				s += "\n SERVLET CONTEXT NAME : " + sContext.getServletContextName();
				logger.info(s);
			}
			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("LeLocApp-ServletContext Destroyed.");
			}
		};
	}

	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(new Object[]{LeLocAppApplication.class});

    	try {
			LELOCAPP.init();
		} catch (IOException e) {
			logger.error("System configurationproperties is not initilized. App Stopped! : " + e.toString());
			throw new RuntimeException(e);
		}
    	
    	app.run(args);
    }
    
    @PostConstruct
    void init(){
    	logger.info("STARt CONSTRUCTT CAN BE HERE***********************");
    }
}
