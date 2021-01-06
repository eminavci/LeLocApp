package com.webproject.rest.mvc.place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.models.entities.Town;
import com.webproject.core.services.TownService;
import com.webproject.rest.exceptions.NotFoundException;
import com.webproject.rest.resources.asm.place.TownListResourceAsm;
import com.webproject.rest.resources.asm.place.TownResourceAsm;
import com.webproject.rest.resources.place.CityResource;
import com.webproject.rest.resources.place.TownListResource;
import com.webproject.rest.resources.place.TownResource;

@RestController
@RequestMapping(value = "/town", produces = "application/json; charset=UTF-8")
public class TownController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TownService townService;
	
	@RequestMapping(value = "/{townId}", method = RequestMethod.GET)
	public ResponseEntity<TownResource> findById(@PathVariable Long townId){
		
		Town town = townService.findById(townId);
		if(town == null)
			throw new NotFoundException();
		TownResource townRes = new TownResourceAsm().toResource(town);
		return new ResponseEntity<TownResource>(townRes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bycity/{cityId}" ,method = RequestMethod.GET)
	public ResponseEntity<TownListResource> findAllByCity(@PathVariable Long cityId){
		long s = System.currentTimeMillis();
		
		TownListResource townListRes = new TownListResourceAsm().toResource(townService.findAllByCityId(cityId));
		long e = System.currentTimeMillis();
		logger.info("TAKEN TIME : " + (e-s));
		return new ResponseEntity<TownListResource>(townListRes, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TownResource> create(@RequestBody CityResource cityRes){
		
		
		return null;
	}
}
