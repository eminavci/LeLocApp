package com.webproject.rest.resources.asm.realestate;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.RealEstate;
import com.webproject.rest.mvc.realestate.RealEstateController;
import com.webproject.rest.resources.realestate.RealEstateResource;

public class RealEstateResourceAsm extends ResourceAssemblerSupport<RealEstate, RealEstateResource>{

	public RealEstateResourceAsm() {
		super(RealEstateController.class, RealEstateResource.class);
		
	}

	@Override
	public RealEstateResource toResource(RealEstate realEstate) {
		RealEstateResource reRes = new RealEstateResource();
		reRes.setCurrency(realEstate.getCurrency());
		reRes.setExplanation(realEstate.getExplanation());
		reRes.setName(realEstate.getName());
		reRes.setNo(realEstate.getNo());
		reRes.setPrice(realEstate.getPrice());
		reRes.setPublishDate(realEstate.getPublishDate());
		reRes.setStatus(realEstate.getStatus());
		reRes.setViewCount(realEstate.getViewCount());
		reRes.setOid(realEstate.getId());
		reRes.setFromWho(realEstate.getFromWho());
		reRes.setMeterSquare(realEstate.getMeterSquare());
		
		if(realEstate.getImages() != null && realEstate.getImages().size()>0)
			reRes.setPictureUrl("http://localhost:8080/static/adimages/" + realEstate.getImages().get(0).getName());
		
		reRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateById(realEstate.getId())).withSelfRel());
		reRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateImages(realEstate.getId())).withRel("images"));
		reRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateAddress(realEstate.getId())).withRel("address"));
		reRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateDetails(realEstate.getId())).withRel("details"));
		reRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateFeatures(realEstate.getId())).withRel("features"));
		
		// TODO add inks------------------
		
		return reRes;
	}

}
