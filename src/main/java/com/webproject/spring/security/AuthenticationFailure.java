package com.webproject.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailure extends SimpleUrlAuthenticationFailureHandler{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad Crendentials");
		logger.error("HATAMM ALDIIIIIIIIIIIIII : ", exception);
		
//		PrintWriter writer = response.getWriter();
//        writer.write(exception.getMessage());
//        writer.flush();
	}

}
