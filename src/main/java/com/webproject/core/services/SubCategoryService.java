package com.webproject.core.services;

import com.webproject.core.models.entities.Feature;
import com.webproject.core.models.entities.FeatureOptions;
import com.webproject.core.models.entities.SubCategory;
import com.webproject.core.services.util.DetailTitleList;
import com.webproject.core.services.util.FeatureList;
import com.webproject.core.services.util.FeatureOptionsList;
import com.webproject.core.services.util.SubCategoryList;

public interface SubCategoryService {
	
	public SubCategory findSubCategorById(Long subCatId);
	public SubCategoryList findSubCategoriesOfOpeType(Long catOpeTypeId);
	public SubCategory saveSubCategory(Long catOpeType, SubCategory subCat);
	public SubCategory saveSubCategory(SubCategory subCat);
	
	public FeatureList findFeaturesOfSubCategory(Long subCatId);
	public DetailTitleList findDetailTitlesOfSubCategory(Long subCatId);
	public FeatureOptions findFeatureOptionById(Long featureOptionId);
	public FeatureOptionsList findOptionsOfFeature(Long featureId);
	public Feature findFeatureById(Long featureId);
	public Feature saveFeature(Feature feature);
	public FeatureOptions saveFeatureOption(Long featureId, FeatureOptions featureOption);
	public FeatureOptions saveFeatureOption(FeatureOptions featureOption);
	
	
}
