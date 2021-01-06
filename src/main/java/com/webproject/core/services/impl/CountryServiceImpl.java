package com.webproject.core.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.Country;
import com.webproject.core.repositories.CountryRepo;
import com.webproject.core.services.CountryService;
import com.webproject.core.services.exceptions.PlaceException;
import com.webproject.core.services.util.CountryList;

@Service
@Transactional//(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor=RuntimeException.class)
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryRepo countryRepo;
	
	@Override
	public Country findById(Long id) {
		Country country = countryRepo.findOne(id);
		if(country == null)
			throw new PlaceException("Country is not found by given id : " + id, ErrKeys.COUNTRY_NOT_FOUND);
		//Object obj = country.getTranslation().size();/// Eagerly initilization
		return country;
	}
	
	@Override
	@Deprecated
	public Country findById(Long id, Set<String> fetchPolicy) {
		Country country = findById(id);
		Object obj;
		if(fetchPolicy.contains("regions"))
			obj = country.getRegions().size();
		if(fetchPolicy.contains("city"))
			obj = country.getRegions();
		
		return null;
	}

	@Override
	public Country save(Country country) {
		if(countryRepo.findByName(country.getName()) != null)
			throw new PlaceException("A Country exists with the given name" + country.getName(), ErrKeys.COUNTRY_ALREADY_EXIST);

		return countryRepo.save(country);
	}

	@Override
	@Cacheable(value="CountryCacheAll")
	public CountryList findAll() {
		return new CountryList(countryRepo.findAll());
	}	

}
