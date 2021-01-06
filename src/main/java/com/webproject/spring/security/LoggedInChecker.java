package com.webproject.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.webproject.core.models.entities.Account;
import com.webproject.spring.security.model.LeLocAppAccountDetail;
import com.webproject.spring.security.model.AppRole;
import com.webproject.spring.security.model.AppRole.RoleEnum;

@Component
public class LoggedInChecker {
	
    public Account getLoggedInAccount() {
        Account account = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // principal can be "anonymousUser" (String)
            if (principal instanceof LeLocAppAccountDetail) {
            	LeLocAppAccountDetail userDetails = (LeLocAppAccountDetail) principal;
                account = userDetails.getAccount();
            }
        }

        return account;
    }
    
    public RoleEnum getAccountRole(){
    	Account acc = getLoggedInAccount();
    	if(acc == null)
    		return null;
    	return acc.getRole();
    } 
}
