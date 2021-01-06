package com.webproject.spring.security.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webproject.core.models.entities.Account;


public class LeLocAppAccountDetail implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Account account;
	private List<GrantedAuthority> authorities;
	

	
	public LeLocAppAccountDetail(Account account, List<GrantedAuthority> authorities) {
		super();
		this.account = account;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.account.getPassword();
	}

	@Override
	public String getUsername() {
		return this.account.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "LeLocAppAccountDetail [account=" + account + ", authorities=" + authorities + ", getAuthorities()="
				+ getAuthorities() + ", getPassword()=" + getPassword() + ", getUsername()=" + getUsername() + "]";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
