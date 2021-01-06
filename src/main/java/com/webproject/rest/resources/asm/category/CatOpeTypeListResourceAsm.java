package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.CatOpeTypeList;
import com.webproject.rest.mvc.category.CategoryController;
import com.webproject.rest.resources.category.CatOpeTypeListResource;

public class CatOpeTypeListResourceAsm extends ResourceAssemblerSupport<CatOpeTypeList, CatOpeTypeListResource>{

	public CatOpeTypeListResourceAsm() {
		super(CategoryController.class, CatOpeTypeListResource.class);
	}

	@Override
	public CatOpeTypeListResource toResource(CatOpeTypeList entity) {
		
		CatOpeTypeListResource catOpeTypeListRes = instantiateResource(entity);
		catOpeTypeListRes.setCatOpeTypeListRes(new CatOpeTypeResourceAsm().toResources(entity.getCatOpeTypes()));
		return catOpeTypeListRes;
	}

}
