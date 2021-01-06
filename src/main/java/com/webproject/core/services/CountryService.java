package com.webproject.core.services;

import java.util.Set;

import com.webproject.core.models.entities.Country;
import com.webproject.core.services.util.CountryList;

public interface CountryService {

	public Country findById(Long id);
	public Country save(Country country);
	public Country findById(Long id, Set<String> fetchPolicy);
	public CountryList findAll();
	
}
