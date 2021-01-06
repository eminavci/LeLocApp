package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Account;
import com.webproject.core.models.entities.User;

public interface AccountRepo extends JpaRepository<Account, Long>{
	
	public Account findByEmail(String email);
	public Account findByUser(User user);
}
