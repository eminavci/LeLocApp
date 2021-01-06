package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Region;

public interface CityRepo extends JpaRepository<City, Long>{

	public City findByName(String name);
	public List<City> findByRegion(Region region);
	
	@Query("select s  "
			+ "from City s, Country c, Region r "
			+ "where c.id = r.country and r.id = s.region and c.id = :countryId order by s.name")
	public List<City> findByCountry(@Param("countryId") Long countryId);
	
}
