package com.webproject.rest.mvc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.models.entities.SubCategory;
import com.webproject.core.services.DetailTitleService;
import com.webproject.core.services.SubCategoryService;
import com.webproject.rest.resources.asm.category.DetailDetailListResourceAsm;
import com.webproject.rest.resources.asm.category.DetailDetailResourceAsm;
import com.webproject.rest.resources.asm.category.DetailTitleListResourceAsm;
import com.webproject.rest.resources.asm.category.DetailTitleResourceAsm;
import com.webproject.rest.resources.asm.category.FeatureListResourceAsm;
import com.webproject.rest.resources.asm.category.FeatureOptionsListResourceAsm;
import com.webproject.rest.resources.asm.category.FeatureOptionsResourceAsm;
import com.webproject.rest.resources.asm.category.FeatureReasourceAsm;
import com.webproject.rest.resources.asm.category.SubCategoryListResourceAsm;
import com.webproject.rest.resources.asm.category.SubCategoryResourceAsm;
import com.webproject.rest.resources.category.DetailDetailListResource;
import com.webproject.rest.resources.category.DetailDetailResource;
import com.webproject.rest.resources.category.DetailTitleListResource;
import com.webproject.rest.resources.category.DetailTitleResource;
import com.webproject.rest.resources.category.FeatureListResource;
import com.webproject.rest.resources.category.FeatureOptionsListResource;
import com.webproject.rest.resources.category.FeatureOptionsResource;
import com.webproject.rest.resources.category.FeatureReasource;
import com.webproject.rest.resources.category.SubCategoryListResource;
import com.webproject.rest.resources.category.SubCategoryResource;

@RestController
@RequestMapping(value = "/subcategory", produces = "application/json; charset=UTF-8")
public class SubCategoryController {

	@Autowired
	SubCategoryService subCatService;
	@Autowired
	DetailTitleService detailTitleService;

	
	@RequestMapping(value = "/{subCategoryId}", method = RequestMethod.GET)
	public ResponseEntity<SubCategoryResource> findSubCategoryById(@PathVariable Long subCategoryId){
		return new ResponseEntity<SubCategoryResource>(new SubCategoryResourceAsm().toResource(subCatService.findSubCategorById(subCategoryId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save/{catOpeTypeId}", method = RequestMethod.POST)
	public ResponseEntity<SubCategoryResource> saveSubCategory(@PathVariable Long catOpeTypeId, @RequestBody SubCategory subCat){
		return new ResponseEntity<SubCategoryResource>(new SubCategoryResourceAsm().toResource(subCatService.saveSubCategory(catOpeTypeId, subCat)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bycatopetype/{catOpeTypeId}", method = RequestMethod.GET)
	public ResponseEntity<SubCategoryListResource> findSubCategoriesOfOpeType(@PathVariable Long catOpeTypeId){
		return new ResponseEntity<SubCategoryListResource>(new SubCategoryListResourceAsm().toResource(subCatService.findSubCategoriesOfOpeType(catOpeTypeId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detailtitles/{detailTitleId}", method = RequestMethod.GET)
	public ResponseEntity<DetailTitleResource> findDetailTitleById(@PathVariable Long detailTitleId){
		return new ResponseEntity<DetailTitleResource>(new DetailTitleResourceAsm().toResource(detailTitleService.findDetailTitleById(detailTitleId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detailtitles/bysubcategory/{subCatId}", method = RequestMethod.GET)
	public ResponseEntity<DetailTitleListResource> findDetailTitlesOfSubCategory(@PathVariable Long subCatId){
		return new ResponseEntity<DetailTitleListResource>(new DetailTitleListResourceAsm().toResource(subCatService.findDetailTitlesOfSubCategory(subCatId)), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/detaildetail/{detailDetailId}", method = RequestMethod.GET)
	public ResponseEntity<DetailDetailResource> findDetailDetailById(@PathVariable Long detailDetailId){
		return new ResponseEntity<DetailDetailResource>(new DetailDetailResourceAsm().toResource(detailTitleService.findDetailDetailById(detailDetailId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detaildetail/bydetailtitle/{detailTitleId}", method = RequestMethod.GET)
	public ResponseEntity<DetailDetailListResource> findDetailOfTitle(@PathVariable Long detailTitleId){
		return new ResponseEntity<DetailDetailListResource>(new DetailDetailListResourceAsm().toResource(detailTitleService.findDetailDetailOfTitle(detailTitleId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/features/{featureId}", method = RequestMethod.GET)
	public ResponseEntity<FeatureReasource> findFeatureById(@PathVariable Long featureId){
		return new ResponseEntity<FeatureReasource>(new FeatureReasourceAsm().toResource(subCatService.findFeatureById(featureId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/features/bysubcategory/{subCatId}", method = RequestMethod.GET)
	public ResponseEntity<FeatureListResource> findFeaturesOfSubCategory(@PathVariable Long subCatId){
		return new ResponseEntity<FeatureListResource>(new FeatureListResourceAsm().toResource(subCatService.findFeaturesOfSubCategory(subCatId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/featureoptions/{featureOptionId}", method = RequestMethod.GET)
	public ResponseEntity<FeatureOptionsResource> findFeatureOptionsById(@PathVariable Long featureOptionId){
		return new ResponseEntity<FeatureOptionsResource>(new FeatureOptionsResourceAsm().toResource(subCatService.findFeatureOptionById(featureOptionId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/featureoptions/byfeature/{featureId}", method = RequestMethod.GET)
	public ResponseEntity<FeatureOptionsListResource> findOptionsOfFeature(@PathVariable Long featureId){
		return new ResponseEntity<FeatureOptionsListResource>(new FeatureOptionsListResourceAsm().toResource(subCatService.findOptionsOfFeature(featureId)), HttpStatus.OK);	
	}
}
