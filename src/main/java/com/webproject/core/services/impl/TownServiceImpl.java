package com.webproject.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Town;
import com.webproject.core.repositories.TownRepo;
import com.webproject.core.services.CityService;
import com.webproject.core.services.TownService;
import com.webproject.core.services.util.TownList;

@Service
@Transactional
public class TownServiceImpl implements TownService{

	@Autowired
	TownRepo townRepo;
	@Autowired
	CityService cityService;
	
	@Override
	@Cacheable(value="townCacheById", key="#id")
	public Town findById(Long id) {
		return townRepo.findOne(id);
	}

	@Override
	public Town save(Town town) {
		// TODO add controls..
		return townRepo.save(town);
	}

	@Override
	public List<Town> retrieveAll() {
		return townRepo.findAll();
	}

	@Override
	public List<Town> getOthers() {
		return townRepo.findByLongitude(0);
	}

	@Override
	@Cacheable(value="townCacheByCityId", key="#cityId")
	public TownList findAllByCityId(Long cityId) {
		
		return new TownList(townRepo.findByCity(findCityById(cityId, true)));
	}
	
	private City findCityById(Long cityId, boolean exception){
		try {
			return cityService.findById(cityId);
		} catch (Exception e) {
			if(exception)
				throw e;
		}
		return null;
	}
	
	

}
