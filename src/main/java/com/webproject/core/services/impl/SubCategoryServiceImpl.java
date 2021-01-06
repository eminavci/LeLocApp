package com.webproject.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.CatOpeType;
import com.webproject.core.models.entities.Feature;
import com.webproject.core.models.entities.FeatureOptions;
import com.webproject.core.models.entities.SubCategory;
import com.webproject.core.repositories.FeatureOptionsRepo;
import com.webproject.core.repositories.FeatureRepo;
import com.webproject.core.repositories.SubCategoryRepo;
import com.webproject.core.services.CategoryService;
import com.webproject.core.services.SubCategoryService;
import com.webproject.core.services.exceptions.CategoryException;
import com.webproject.core.services.util.DetailTitleList;
import com.webproject.core.services.util.FeatureList;
import com.webproject.core.services.util.FeatureOptionsList;
import com.webproject.core.services.util.SubCategoryList;

@Service
@Transactional
public class SubCategoryServiceImpl extends BaseService implements SubCategoryService{

	@Autowired
	SubCategoryRepo subCatRepo;
	@Autowired
	CategoryService catService;
	@Autowired
	FeatureOptionsRepo foRepo;
	@Autowired
	FeatureRepo fRepo;
	
	@Override
	public SubCategory findSubCategorById(Long subCatId) {
		SubCategory subCat = subCatRepo.findOne(subCatId);
		if(subCat == null)
			throw new CategoryException("SubCategory is not found by given id : " + subCatId, ErrKeys.CATEGORY_NOT_FOUND);
		return subCat;
	}

	@Override
	public SubCategoryList findSubCategoriesOfOpeType(Long catOpeTypeId) {
		return new SubCategoryList(subCatRepo.findByCatOpeTypeAndIsActive(catService.findCatOpeTypeById(catOpeTypeId), true));
	}
	
	@Override
	public SubCategory saveSubCategory(SubCategory subCat) {
		fieldMustNotNull(subCat.getCatOpeType(), "subCat.getCatOpeType");
		fieldMustHasText(subCat.getMsgCode(), "subCat.getMsgCode");
		fieldMustHasText(subCat.getName(), "subCat.getName");
		
		return subCatRepo.save(subCat);
	}

	@Override
	public SubCategory saveSubCategory(Long catOpeType, SubCategory subCat) {
		CatOpeType opeType = catService.findCatOpeTypeById(catOpeType);
		subCat.setCatOpeType(opeType);
		return saveSubCategory(subCat);
	}

	@Override
	@Cacheable(value="featuresCacheBySubCategoryId", key="#subCatId")
	public FeatureList findFeaturesOfSubCategory(Long subCatId) {
		return new FeatureList(findSubCategorById(subCatId).getFeatures());
	}

	@Override
	@Cacheable(value="detailsCacheBySubCategoryId", key="#subCatId")
	public DetailTitleList findDetailTitlesOfSubCategory(Long subCatId) {
		return new DetailTitleList(findSubCategorById(subCatId).getDetailTitles());
	}

	@Override
	public FeatureOptions findFeatureOptionById(Long featureOptionId) {
		FeatureOptions fo = foRepo.findOne(featureOptionId);
		if(fo == null)
			throw new CategoryException("Feature Option is not found by given id : " + featureOptionId, ErrKeys.CATEGORY_NOT_FOUND);
		return fo;
	}

	@Override
	@Cacheable(value="featurOptionCacheByFeatureId", key="#featureId")
	public FeatureOptionsList findOptionsOfFeature(Long featureId) {
		return new FeatureOptionsList(foRepo.findByFeature(findFeatureById(featureId)));
	}

	@Override
	public Feature findFeatureById(Long featureId) {
		Feature f = fRepo.findOne(featureId);
		if(f == null)
			throw new CategoryException("Feature is not found by given id : " + featureId, ErrKeys.CATEGORY_NOT_FOUND);
		return f;
	}

	@Override
	public Feature saveFeature(Feature feature) {
		fieldMustHasText(feature.getMsgCode(), "feature.getMsgCode");
		fieldMustHasText(feature.getName(), "feature.getName");
		
		return fRepo.save(feature);
	}

	@Override
	public FeatureOptions saveFeatureOption(Long featureId, FeatureOptions featureOption) {
		Feature f = findFeatureById(featureId);
		featureOption.setFeature(f);
		return saveFeatureOption(featureOption);
	}

	@Override
	public FeatureOptions saveFeatureOption(FeatureOptions featureOption) {
		fieldMustHasText(featureOption.getMsgCode(), "featureOption.getMsgCode");
		fieldMustHasText(featureOption.getName(), "featureOption.getName");
		fieldMustNotNull(featureOption.getFeature(), "featureOption.getFeature");
		
		return foRepo.save(featureOption);
	}

}
