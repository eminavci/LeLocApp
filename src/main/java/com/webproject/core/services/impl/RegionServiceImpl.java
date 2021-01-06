package com.webproject.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.Country;
import com.webproject.core.models.entities.Region;
import com.webproject.core.repositories.RegionRepo;
import com.webproject.core.services.CountryService;
import com.webproject.core.services.RegionService;
import com.webproject.core.services.exceptions.PlaceException;
import com.webproject.core.services.util.RegionList;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{

	@Autowired
	private RegionRepo regionRepo;
	@Autowired
	private CountryService countryService;
	
	@Override
	@Cacheable(value="regionCacheById", key="#id")
	public Region findById(Long id) {
		Region region = regionRepo.findOne(id);
			if(region == null)
				throw new PlaceException("Region is not found by given id : " + id, ErrKeys.COUNTRY_NOT_FOUND);
		return region;
	}

	@Override
	public Region save(Long countryId, Region region) {
		assert(region.getCountry() == null) : "The country of region can not be null!";
		Country country = findCountryById(countryId, true);
		
		Region reg = regionRepo.findByNameAndCountry(region.getName(), country);
		if(reg != null)
			throw new PlaceException("The region with given name is", ErrKeys.COUNTRY_ALREADY_EXIST);
		
		return regionRepo.save(region);
	}

	private Country findCountryById(Long countryId, boolean exception) {
		try {
			return countryService.findById(countryId);
		} catch (Exception e) {
			if(exception)
				throw e;
		}
		return null;
	}

	@Override
	public RegionList findAll() {
		return new RegionList(regionRepo.findAll());
	}

	@Override
	@Cacheable(value="regionCacheByCountryId", key="#countryId")
	public RegionList findAllByCountry(Long countryId) {
		Country country = findCountryById(countryId, true);
		return new RegionList(regionRepo.findByCountry(country));
	}

}
