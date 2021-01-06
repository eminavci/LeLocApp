package com.webproject.rest.resources.asm.realestate;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.ImageList;
import com.webproject.rest.mvc.realestate.RealEstateController;
import com.webproject.rest.resources.realestate.ImageListResource;

public class ImageListResourceAsm extends ResourceAssemblerSupport<ImageList, ImageListResource>{

	public ImageListResourceAsm() {
		super(RealEstateController.class, ImageListResource.class);
	}

	@Override
	public ImageListResource toResource(ImageList imgList) {
		ImageListResource imgListRes = instantiateResource(imgList);
		imgListRes.setImgListRes(new ImageResourceAsm().toResources(imgList.getImages()));
		
		return imgListRes;
	}

}
