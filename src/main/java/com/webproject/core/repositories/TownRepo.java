package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Town;

public interface TownRepo extends JpaRepository<Town, Long>{

	public Town findByName(String name);
	public List<Town> findByLongitude(double longitude);
	public List<Town> findByCity(City city);
	
}
