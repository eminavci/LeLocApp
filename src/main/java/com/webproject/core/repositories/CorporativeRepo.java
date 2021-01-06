package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Corporative;
import com.webproject.core.models.entities.User;

public interface CorporativeRepo extends JpaRepository<Corporative, Long>{

	public Corporative findByUser(User user);
	
}
