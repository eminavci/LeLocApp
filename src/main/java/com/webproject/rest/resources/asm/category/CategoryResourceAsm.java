package com.webproject.rest.resources.asm.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.webproject.core.models.entities.Category;
import com.webproject.rest.mvc.category.CategoryController;
import com.webproject.rest.resources.category.CategoryResource;

public class CategoryResourceAsm extends ResourceAssemblerSupport<Category, CategoryResource>{

	public CategoryResourceAsm() {
		super(CategoryController.class, CategoryResource.class);
	}

	@Override
	public CategoryResource toResource(Category category) {
		CategoryResource catRes = new CategoryResource();
		catRes.setActive(category.isActive());
		catRes.setMsgCode(category.getMsgCode());
		catRes.setName(category.getName());
		catRes.setOid(category.getId());
		
		catRes.add(linkTo(methodOn(CategoryController.class).findCategoryById(category.getId())).withSelfRel());
		catRes.add(linkTo(methodOn(CategoryController.class).findOpeTypesOfCategory(category.getId())).withRel("catopetypes"));
		catRes.add(linkTo(methodOn(CategoryController.class).findDetailTitlesOfCategory(category.getId())).withRel("detailtitles"));
		
		return catRes;
	}

}
