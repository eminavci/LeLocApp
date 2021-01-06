package com.webproject.spring.conf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.webproject.spring.security.AuthenticationFailure;
import com.webproject.spring.security.AuthenticationSuccess;
import com.webproject.spring.security.HttpAuthenticationEntryPoint;
import com.webproject.spring.security.HttpLogoutSuccessHandler;
import com.webproject.spring.service.impl.LeLocAppAccountDetailService;

@Configuration
@EnableWebSecurity
//@EnableScheduling
//@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) //First thing to mention is @Order annotation, which basically keeps all the defaults set by Spring Boot, only overriding them in this file.
public class LeLocAppSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationFailure authFailure;
	@Autowired
	private AuthenticationSuccess authSuccess;
	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private LeLocAppAccountDetailService userDetailService;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
	
//	@Autowired
//	public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception{
//		builder.inMemoryAuthentication().withUser("eminavci").password("1234").roles("ADMIN", "USER");
//		
//		builder.
//		userDetailsService(userDetailService);
//        //;//.passwordEncoder(new BCryptPasswordEncoder());
//	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		 http
		 	.csrf().disable()
		 	.headers()
            .frameOptions()
            .disable()
           		.and()
           .sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
           		.and()
		 	.authenticationProvider(authenticationProvider())
	        .exceptionHandling()
	        .accessDeniedHandler(new AccessDeniedHandlerImpl())
	        .authenticationEntryPoint(authenticationEntryPoint)
	        	.and()
		 	.authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/category/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.POST, "/subcategory/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.POST, "/country/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.POST, "/region/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.POST, "/town/**").hasRole("ADMIN")
	        .antMatchers(HttpMethod.POST, "/city/**").hasRole("ADMIN")
	        //.antMatchers(HttpMethod.POST, "/user").hasRole("USER")
	        .antMatchers("/message/**", "/user/byemail").authenticated()
	        .antMatchers("/**", "/login").permitAll()
	        	.and()//.apply(configurer)
	        .httpBasic()
	        	.and()
			.formLogin().loginPage("/login").permitAll()
			//.loginProcessingUrl("/login")
			.successHandler(authSuccess) // instead of reirecting after login, it sends succes OK http status code
			.failureHandler(authFailure) // instead of reirecting after login, it sends succes UNAUTHORİZED status code	
            .usernameParameter("username")
            .passwordParameter("password")
				.and()
			.logout()
			.logoutSuccessUrl("/logout")
			.logoutSuccessHandler(logoutSuccessHandler)
			.permitAll()
				.and()
			.rememberMe();
			//.logoutRequestMatcher(new AntPathRequestMatcher("/login?logout", "POST"))
           
           
//           .maximumSessions(1)

		 //http.authorizeRequests().anyRequest().authenticated();
		 
	}
	
	
	public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException, ServletException {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			
		}

	}
}
