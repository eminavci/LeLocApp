package com.webproject.rest.resources.asm.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.DetailTitle;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.DetailTitleResource;

public class DetailTitleResourceAsm extends ResourceAssemblerSupport<DetailTitle, DetailTitleResource>{

	public DetailTitleResourceAsm() {
		super(SubCategoryController.class, DetailTitleResource.class);
	}

	@Override
	public DetailTitleResource toResource(DetailTitle detailTitle) {
		DetailTitleResource detailTitleRes = instantiateResource(detailTitle);
		detailTitleRes.setActive(detailTitle.isActive());
		detailTitleRes.setMsgCode(detailTitle.getMsgCode());
		detailTitleRes.setName(detailTitle.getName());
		detailTitleRes.setShowOrder(detailTitle.getShowOrder());
		detailTitleRes.setType(detailTitle.getType());
		detailTitleRes.setOid(detailTitle.getId());
		detailTitleRes.setForAd(detailTitle.isForAd());
		detailTitleRes.setTypeForAd(detailTitle.getTypeForAd());
		
		detailTitleRes.add(linkTo(methodOn(SubCategoryController.class).findDetailTitleById(detailTitle.getId())).withSelfRel());
		detailTitleRes.add(linkTo(methodOn(SubCategoryController.class).findDetailOfTitle(detailTitle.getId())).withRel("details"));
		return detailTitleRes;
	}

}
