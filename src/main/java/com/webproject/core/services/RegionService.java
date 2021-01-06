package com.webproject.core.services;

import com.webproject.core.models.entities.Region;
import com.webproject.core.services.util.RegionList;

public interface RegionService {

	public Region findById(Long id);
	public RegionList findAll();
	public Region save(Long countryId, Region region);
	public RegionList findAllByCountry(Long countryId);
	
}
