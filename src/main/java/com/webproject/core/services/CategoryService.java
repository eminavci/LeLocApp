package com.webproject.core.services;

import com.webproject.core.models.entities.CatOpeType;
import com.webproject.core.models.entities.Category;
import com.webproject.core.services.util.CatOpeTypeList;
import com.webproject.core.services.util.CategoryList;
import com.webproject.core.services.util.DetailTitleList;

public interface CategoryService {

	public Category findCategoryById(Long categoryId);
	public CategoryList findAllCategory();
	public Category saveCategory(Category category);
	public CatOpeType saveCatOpeType(Long categoryId, CatOpeType catOpeType);
	public CatOpeType saveCatOpeType(CatOpeType catOpeType);
	
	public CatOpeType findCatOpeTypeById(Long catOpeTypeId);
	public CatOpeTypeList findOpeTypesOfCategory(Long categoryId);
	public DetailTitleList findDetailTitlesOfCategory(Long categoryId);
	public DetailTitleList findDetailTitlesOfCatOpeType(Long catOpeTypeId);
	
	
}
