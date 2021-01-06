package com.webproject.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.CatOpeType;
import com.webproject.core.models.entities.Category;
import com.webproject.core.repositories.CatOpeTypeRepo;
import com.webproject.core.repositories.CategoryRepo;
import com.webproject.core.services.CategoryService;
import com.webproject.core.services.exceptions.CategoryException;
import com.webproject.core.services.util.CatOpeTypeList;
import com.webproject.core.services.util.CategoryList;
import com.webproject.core.services.util.DetailTitleList;

@Service
@Transactional
public class CategoryServiceImpl extends BaseService implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CatOpeTypeRepo opeTypeRepo;
	
	@Override
	public Category findCategoryById(Long categoryId) {
		Category cat = categoryRepo.findOne(categoryId);
		if(cat == null)
			throw new CategoryException("Category is not found by given id : " + categoryId, ErrKeys.CATEGORY_NOT_FOUND);
		return cat;
	}

	@Override
	@Cacheable(value="categoryCache")
	public CategoryList findAllCategory() {
		return new CategoryList(categoryRepo.findByIsActive(true));
	}
	
	@Override
	public Category saveCategory(Category category) {
		fieldMustHasText(category.getMsgCode(), "category.getMsgCode");
		fieldMustHasText(category.getName(), "category.getName");
		
		return categoryRepo.save(category);
	}

	@Override
	public CatOpeType findCatOpeTypeById(Long catOpeTypeId) {
		CatOpeType opeType = opeTypeRepo.findOne(catOpeTypeId);
		if(opeType == null)
			throw new CategoryException("Category Ope Type is not found by given id : " + catOpeTypeId, ErrKeys.CATEGORY_NOT_FOUND);
		return opeType;
	}

	@Override
	@Cacheable(value="opeTypeCacheByCategoryId", key="#categoryId")
	public CatOpeTypeList findOpeTypesOfCategory(Long categoryId) {
		return new CatOpeTypeList(opeTypeRepo.findByCategory(findCategoryById(categoryId)));
	}

	@Override
	public DetailTitleList findDetailTitlesOfCategory(Long categoryId) {
		return new DetailTitleList(findCategoryById(categoryId).getDetailTitles());
	}

	@Override
	public DetailTitleList findDetailTitlesOfCatOpeType(Long catOpeTypeId) {
		return new DetailTitleList(findCatOpeTypeById(catOpeTypeId).getDetailTitles());
	}

	@Override
	public CatOpeType saveCatOpeType(CatOpeType catOpeType) {
		fieldMustHasText(catOpeType.getMsgCode(), "catOpeType.getMsgCode");
		fieldMustHasText(catOpeType.getName(), "catOpeType.getName");
		
		return opeTypeRepo.save(catOpeType);
	}
	
	@Override
	public CatOpeType saveCatOpeType(Long categoryId, CatOpeType catOpeType) {
		Category cat = findCategoryById(categoryId);
		catOpeType.setCategory(cat);
		
		return saveCatOpeType(catOpeType);
	}

	
}
