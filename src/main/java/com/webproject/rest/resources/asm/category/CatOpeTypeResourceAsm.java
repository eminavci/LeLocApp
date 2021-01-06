package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.CatOpeType;
import com.webproject.rest.mvc.category.CategoryController;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.CatOpeTypeResource;

public class CatOpeTypeResourceAsm extends ResourceAssemblerSupport<CatOpeType, CatOpeTypeResource>{

	public CatOpeTypeResourceAsm() {
		super(CategoryController.class, CatOpeTypeResource.class);
	}

	@Override
	public CatOpeTypeResource toResource(CatOpeType catOpe) {
		CatOpeTypeResource catOpeTypeRes = instantiateResource(catOpe);
		catOpeTypeRes.setMsgCode(catOpe.getMsgCode());
		catOpeTypeRes.setName(catOpe.getName());
		catOpeTypeRes.setOid(catOpe.getId());
		
		catOpeTypeRes.add(linkTo(methodOn(CategoryController.class).findCatOpeTypeByd(catOpe.getId())).withSelfRel());
		catOpeTypeRes.add(linkTo(methodOn(CategoryController.class).findCategoryById(catOpe.getCategory().getId())).withRel("category"));
		catOpeTypeRes.add(linkTo(methodOn(SubCategoryController.class).findSubCategoriesOfOpeType(catOpe.getId())).withRel("subcategories"));
		catOpeTypeRes.add(linkTo(methodOn(CategoryController.class).findDetailTitlesOfCatOpeTypey(catOpe.getId())).withRel("detailtitles"));
		
		return catOpeTypeRes;
	}

}
