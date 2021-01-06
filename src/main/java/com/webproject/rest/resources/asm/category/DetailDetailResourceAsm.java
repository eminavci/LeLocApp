package com.webproject.rest.resources.asm.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.DetailDetail;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.DetailDetailResource;

public class DetailDetailResourceAsm extends ResourceAssemblerSupport<DetailDetail, DetailDetailResource>{

	public DetailDetailResourceAsm() {
		super(SubCategoryController.class, DetailDetailResource.class);
	}

	@Override
	public DetailDetailResource toResource(DetailDetail dd) {
		DetailDetailResource detailDetailRes = instantiateResource(dd);
		detailDetailRes.setActive(dd.isActive());
		detailDetailRes.setMsgCode(dd.getMsgCode());
		detailDetailRes.setName(dd.getName());
		detailDetailRes.setShowOrder(dd.getShowOrder());
		detailDetailRes.setOid(dd.getId());
		
		detailDetailRes.add(linkTo(methodOn(SubCategoryController.class).findDetailDetailById(dd.getId())).withSelfRel());
		detailDetailRes.add(linkTo(methodOn(SubCategoryController.class).findDetailTitleById(dd.getDetailTitle().getId())).withRel("detailtitle"));
		return detailDetailRes;
	}

	
}
