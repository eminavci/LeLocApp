package com.webproject.core.services;

import java.util.List;

import com.webproject.core.models.entities.Town;
import com.webproject.core.services.util.TownList;

public interface TownService {

	public Town findById(Long id);
	public Town save(Town town);
	public List<Town> retrieveAll();
	
	public List<Town> getOthers();
	
	public TownList findAllByCityId(Long cityId);
	
}
