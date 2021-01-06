package com.webproject.spring.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LELOCAPP implements Keys{

	private static Logger logger = LoggerFactory.getLogger(LELOCAPP.class);
	
	public static String secretKey;
	public static Environment env;
	public static String logPath;
	
	
	public static Properties configuration;
	
	public static void init() throws IOException{
		logger.info("LelocApp Configuration Properties start");
		if(configuration != null)
			return;
		
		configuration = new Properties();           
		try {
			InputStream stream = LELOCAPP.class.getResourceAsStream(File.separator + "lelocapp.properties");
			
			configuration.load(stream);
			logger.info("configuration file is loaded");
			
			secretKey = configuration.getProperty("lelocapp.secretkey", "");
			env = Environment.valueOf(configuration.getProperty("lelocapp.environment", Environment.PROD.toString()));
			logPath = configuration.getProperty("lelocapp.log_path_"+env.toString(), "/home/user");
		} catch (Exception e) {
			logger.info("LELOCAPP properties set hardcode!!!");
			secretKey = "AOxzOlQc5XQGc0JhCmcxKjGDuTSNJbVqBLbGJnsMsYqfVAJb92gqCe7Xb94jRUKy";
			env = Environment.DEV;
			logPath = "C:\\";
		}
		
		initSystemProperties();
		
		
		logger.info("LelocApp Configuration Properties succesfully finished");
	}

	private static void initSystemProperties() {
		System.setProperty(SECRET_KEY, secretKey);
		System.setProperty(ENVIRONMENT, env.toString());
		System.setProperty(LOG_PATH, logPath);
	}
	
}
