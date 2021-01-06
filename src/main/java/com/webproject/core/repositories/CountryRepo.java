package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webproject.core.models.entities.Country;

public interface CountryRepo extends JpaRepository<Country, Long>{
	public Country findByName(String name);
	
//	@Query("SELECT c FROM  Country c JOIN FETCH c.regions WHERE c.id = (:id)")
//	public Country findByIdAndFetchRegionsEagerly(@Param("id") Long id);
	
}
