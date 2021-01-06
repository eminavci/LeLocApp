package com.webproject.rest.resources.asm.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.webproject.core.models.entities.Address;
import com.webproject.rest.mvc.place.CityController;
import com.webproject.rest.mvc.place.CountryController;
import com.webproject.rest.mvc.place.RegionController;
import com.webproject.rest.mvc.place.TownController;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.AddressResource;

public class AddressResourceAsm extends ResourceAssemblerSupport<Address, AddressResource>{

	public AddressResourceAsm() {
		super(UserController.class, AddressResource.class);
	}

	@Override
	public AddressResource toResource(Address address) {
		AddressResource adrRes = new AddressResource();
		adrRes.setLatitude(address.getLatitude());
		adrRes.setLongitude(address.getLongitude());
		adrRes.setLine1(address.getLine1());
		adrRes.setLine2(address.getLine2());
		adrRes.setPostCode(address.getPostCode());
		
		adrRes.add(linkTo(methodOn(UserController.class).findAddressById(address.getId())).withSelfRel());
		if(address.getUser() != null)
			adrRes.add(linkTo(methodOn(UserController.class).findUserById(address.getUser().getId())).withRel("user"));
		adrRes.add(linkTo(methodOn(CountryController.class).findById(address.getCountry().getId())).withRel("country"));
		
		if(address.getRegion() != null)
		adrRes.add(linkTo(methodOn(RegionController.class).findById(address.getRegion().getId())).withRel("region"));
		
		if(address.getCity() != null)
			adrRes.add(linkTo(methodOn(CityController.class).findById(address.getCity().getId())).withRel("city"));
		if(address.getTown() != null)
			adrRes.add(linkTo(methodOn(TownController.class).findById(address.getTown().getId())).withRel("town"));
		
		return adrRes;
	}

}
