package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.webproject.core.services.util.DetailDetailList;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.DetailDetailListResource;

public class DetailDetailListResourceAsm extends ResourceAssemblerSupport<DetailDetailList, DetailDetailListResource>{

	public DetailDetailListResourceAsm() {
		super(SubCategoryController.class, DetailDetailListResource.class);
	}

	@Override
	public DetailDetailListResource toResource(DetailDetailList detailDetailList) {
		DetailDetailListResource detailDetailListRes = instantiateResource(detailDetailList);
		detailDetailListRes.setDetailDetailListRes(new DetailDetailResourceAsm().toResources(detailDetailList.getDdList()));
		return detailDetailListRes;
	}
}
