package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.DetailTitleList;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.DetailTitleListResource;

public class DetailTitleListResourceAsm extends ResourceAssemblerSupport<DetailTitleList, DetailTitleListResource>{

	public DetailTitleListResourceAsm() {
		super(SubCategoryController.class, DetailTitleListResource.class);

	}

	@Override
	public DetailTitleListResource toResource(DetailTitleList detailTitleList) {
		DetailTitleListResource detailTitleListRes = instantiateResource(detailTitleList);
		detailTitleListRes.setDetailTitleListRes(new DetailTitleResourceAsm().toResources(detailTitleList.getDtList()));
		return detailTitleListRes;
	}

}
