package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Country;
import com.webproject.core.models.entities.Region;

public interface RegionRepo extends JpaRepository<Region, Long>{

	public Region findByNameAndCountry(String name, Country country);
	public List<Region> findByCountry(Country country);
}
