package com.webproject.rest.mvc.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.models.entities.Region;
import com.webproject.core.services.RegionService;
import com.webproject.core.services.util.RegionList;
import com.webproject.rest.exceptions.NotFoundException;
import com.webproject.rest.resources.asm.place.RegionListResourceAsm;
import com.webproject.rest.resources.asm.place.RegionResourceAsm;
import com.webproject.rest.resources.place.RegionListResource;
import com.webproject.rest.resources.place.RegionResource;

@RestController
@RequestMapping(value = "/region", produces = "application/json; charset=UTF-8")
public class RegionController {

	@Autowired
	RegionService regionService;
	
	@RequestMapping(value = "/{regionId}", method = RequestMethod.GET)
	public ResponseEntity<RegionResource> findById(@PathVariable Long regionId){
		Region region = regionService.findById(regionId);
		if(region == null)
			throw new NotFoundException("Region is not found id : " + regionId);
		return new ResponseEntity<RegionResource>(new RegionResourceAsm().toResource(region), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<RegionListResource> findAll(){
		
		RegionList regions = regionService.findAll();
		if(regions == null || regions.getRegions() == null || regions.getRegions().size() == 0)
			throw new NotFoundException("No regions is found!");
		
		return new ResponseEntity<>(new RegionListResourceAsm().toResource(regions), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bycountry/{countryId}")
	public ResponseEntity<RegionListResource> findAllByCountryId(@PathVariable Long countryId){
		return new ResponseEntity<RegionListResource>(new RegionListResourceAsm().toResource(regionService.findAllByCountry(countryId)), HttpStatus.OK);
	} 
	
	@RequestMapping(value = "/{countryId}/region-entry", method = RequestMethod.POST)
	public ResponseEntity<RegionResource> create(@PathVariable Long countryId, @RequestBody RegionResource regionRes){
		return new ResponseEntity<RegionResource>(new RegionResourceAsm().toResource(regionService.save(countryId, regionRes.toRegion())), HttpStatus.CREATED);
	}
	
}
