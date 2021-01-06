package com.webproject.core.services;

import java.util.List;

import com.webproject.core.models.entities.City;
import com.webproject.core.services.util.CityList;

public interface CityService {

	public City findById(Long id);
	public City save(City city);
	public List<City> retrieveAll();
	public List<City> retrieveAllById(List<Long> ids);
	
	public CityList findAllByRegionId(Long regionId);
	public CityList findAllByCountryId(Long countryId);
}
