package com.webproject.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Country;
import com.webproject.core.models.entities.Region;
import com.webproject.core.repositories.CityRepo;
import com.webproject.core.services.CityService;
import com.webproject.core.services.CountryService;
import com.webproject.core.services.RegionService;
import com.webproject.core.services.exceptions.PlaceException;
import com.webproject.core.services.util.CityList;

@Service
@Transactional
public class CityServiceImpl implements CityService{

	@Autowired
	CityRepo cityRepo;
	@Autowired
	RegionService regionService;
	@Autowired
	CountryService countryService;
	
	@Override
	@Cacheable(value="cityCacheById", key="#id")
	public City findById(Long id) {
		City city = cityRepo.findOne(id);
		if(city == null)
			throw new PlaceException("City is not found by given id : " + id, ErrKeys.COUNTRY_NOT_FOUND);
		return city;
	}

	@Override
	public City save(City city) {
		// TODO add controll...
		return cityRepo.save(city);
	}

	@Override
	public List<City> retrieveAll() {
		return cityRepo.findAll();
	}

	@Override
	public List<City> retrieveAllById(List<Long> ids) {
		return cityRepo.findAll(ids);
	}

	@Override
	@Cacheable(value="cityCacheByRegionId", key="#regionId")
	public CityList findAllByRegionId(Long regionId) {
		Region region = findRegionById(regionId, true);
		return new CityList(cityRepo.findByRegion(region));
	}

	
	private Region findRegionById(Long regionId, boolean exception){
		try {
			return regionService.findById(regionId);
		} catch (Exception e) {
			if(exception)
				throw e;
		}
		return null;
		
	}

	@Override
	public CityList findAllByCountryId(Long countryId) {
		
		return new CityList(cityRepo.findByCountry(countryId));
	}
}
