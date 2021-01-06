package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.User;

public interface AddressRepo extends JpaRepository<Address, Long>{

	public List<Address> findByUser(User user);
}
