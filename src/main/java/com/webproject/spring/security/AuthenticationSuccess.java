package com.webproject.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.webproject.spring.security.model.LeLocAppAccountDetail;

@Component
public class AuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 protected AuthenticationManager authenticationManager;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			
			LeLocAppAccountDetail acc = (LeLocAppAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.err.println("asaaaaaaaaaaaaaa : " + acc.toString());
			
		} catch (Exception e) {
			logger.error("AAAAAAAAAAAAAAAAAAa : " + e);
		}
		
		clearAuthenticationAttributes(request);
	}

}
