package com.webproject.rest.resources.asm.realestate;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.Image;
import com.webproject.rest.mvc.realestate.RealEstateController;
import com.webproject.rest.resources.realestate.ImageResource;

public class ImageResourceAsm extends ResourceAssemblerSupport<Image, ImageResource>{

	public ImageResourceAsm() {
		super(RealEstateController.class, ImageResource.class);
	}

	@Override
	public ImageResource toResource(Image image) {
		ImageResource imageRes = new ImageResource();
		imageRes.setActive(image.isActive());
		imageRes.setName(image.getName());
		imageRes.setOid(image.getId());
		imageRes.setThumb(image.getThumb());
		
		imageRes.add(linkTo(methodOn(RealEstateController.class).findImageById(image.getId())).withSelfRel());
		imageRes.add(linkTo(methodOn(RealEstateController.class).findRealEstateById(image.getRealEstate().getId())).withRel("realestate"));
		
		return imageRes;
	}

	
	
}
