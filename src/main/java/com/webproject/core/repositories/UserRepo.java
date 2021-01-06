package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webproject.core.models.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{

	public User findByAccountEmail(String email);
	public User findByAccountEmailAndAccountPassword(String email, String password);
	
}
