package com.webproject.rest.mvc.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.webproject.core.models.entities.City;
import com.webproject.core.services.CityService;
import com.webproject.rest.exceptions.NotFoundException;
import com.webproject.rest.resources.asm.place.CityListResourceAsm;
import com.webproject.rest.resources.asm.place.CityResourceAsm;
import com.webproject.rest.resources.place.CityListResource;
import com.webproject.rest.resources.place.CityResource;

@RestController
@RequestMapping(value = "/city", produces = "application/json; charset=UTF-8")
public class CityController {

	@Autowired 
	CityService cityService;
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
	public ResponseEntity<CityResource> findById(@PathVariable Long cityId){
		City city = cityService.findById(cityId);
		if(city == null)
			throw new NotFoundException("City is not found");
		
		CityResource cityRes = new CityResourceAsm().toResource(city);
		
		return new ResponseEntity<CityResource>(cityRes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/byregion/{regionId}", method = RequestMethod.GET)
	public ResponseEntity<CityListResource> findAllByRegionId(@PathVariable Long regionId){
		return new ResponseEntity<CityListResource>(new CityListResourceAsm().toResource(cityService.findAllByRegionId(regionId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bycountry/{countryId}", method = RequestMethod.GET)
	public ResponseEntity<CityListResource> findAllByCountryId(@PathVariable Long countryId){
		return new ResponseEntity<CityListResource>(new CityListResourceAsm().toResource(cityService.findAllByCountryId(countryId)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CityResource> create(@RequestBody CityResource cityRes){
		
		
		return null;
	}
	
}
