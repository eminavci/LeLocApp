package com.webproject.rest.mvc.place;

import java.net.URI;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.models.entities.Country;
import com.webproject.core.services.CountryService;
import com.webproject.rest.resources.asm.place.CountryListResourceAsm;
import com.webproject.rest.resources.asm.place.CountryResourceAsm;
import com.webproject.rest.resources.place.CountryListResource;
import com.webproject.rest.resources.place.CountryResource;
import com.webproject.spring.service.MessageByLocaleService;

@RestController
@RequestMapping(value="/country", produces = "application/json; charset=UTF-8")
public class CountryController{

	protected Logger logger = LoggerFactory.getLogger(CountryController.class);
	
	
	@Autowired
	CountryService countryService;
	@Autowired
    MessageByLocaleService msgSource;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<String> test(){
	
		System.out.println("ccccccccccccccccccccccccccccccccc : " + Locale.getDefault().getLanguage());
		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAa : " + msgSource.getMessage("deneme"));
		
		return new ResponseEntity<String>("hellow", HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CountryListResource> findAll(){
		return new ResponseEntity<CountryListResource>(new CountryListResourceAsm().toResource(countryService.findAll()), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{countryId}", method = RequestMethod.GET)
	public ResponseEntity<CountryResource> findById(@PathVariable Long countryId){
		Country country = countryService.findById(countryId);

		CountryResource res = new CountryResourceAsm().toResource(country);
		return new ResponseEntity<CountryResource>(res, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CountryResource> create(@RequestBody CountryResource sentCountryRes){
		
		try {
			Country country = countryService.save(sentCountryRes.toCountry());
//			CountryResource countryRes = new CountryResourceAsm().toResource(country);
//			HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(URI.create(countryRes.getLink("self").getHref()));
            return new ResponseEntity<CountryResource>(new CountryResourceAsm().toResource(country), HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
