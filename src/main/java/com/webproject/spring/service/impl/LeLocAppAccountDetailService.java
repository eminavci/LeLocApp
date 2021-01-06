package com.webproject.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.webproject.core.common.LeLocAppCrypto;
import com.webproject.core.models.entities.Account;
import com.webproject.core.repositories.AccountRepo;
import com.webproject.spring.security.model.LeLocAppAccountDetail;

@Component
public class LeLocAppAccountDetailService implements UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // username is email....
		Account acc = accountRepo.findByEmail(email);
		if(acc == null){
			logger.error("User account is not found by given email : " + email + " ***********************************************************");
			throw new UsernameNotFoundException("No user found by this email : " + email);
		}
		//acc.setPassword(LeLocAppCrypto.decryptAES(acc.getPassword()));
		logger.info("ACCOUNT IS FOUND : :::: " + acc.getUser().getFirstName() +" =============================================================");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		//grantedAuthorities.add(new SimpleGrantedAuthority(acc.getRole().toString()));
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		return new LeLocAppAccountDetail(acc, grantedAuthorities);
	}
	

	
	
}
