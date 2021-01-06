package com.webproject.rest.mvc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.models.entities.Account;
import com.webproject.core.services.CategoryService;
import com.webproject.core.services.UserService;
import com.webproject.rest.resources.asm.category.CatOpeTypeListResourceAsm;
import com.webproject.rest.resources.asm.category.CatOpeTypeResourceAsm;
import com.webproject.rest.resources.asm.category.CategoryListResourceAsm;
import com.webproject.rest.resources.asm.category.CategoryResourceAsm;
import com.webproject.rest.resources.asm.category.DetailTitleListResourceAsm;
import com.webproject.rest.resources.category.CatOpeTypeListResource;
import com.webproject.rest.resources.category.CatOpeTypeResource;
import com.webproject.rest.resources.category.CategoryListResource;
import com.webproject.rest.resources.category.CategoryResource;
import com.webproject.rest.resources.category.DetailTitleListResource;

@RestController
@RequestMapping(value = "/category", produces = "application/json; charset=UTF-8")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService us;
	
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<CategoryResource> findCategoryById(@PathVariable Long categoryId){
		
		Account acc = us.getLoggedInAccount();
		if(acc==null)
			System.err.println("NO SESSION FOUND *********************************");
		else
			System.err.println("S :))) Session FOUND : *******************" + acc.getEmail());
		
		return new ResponseEntity<CategoryResource>(new CategoryResourceAsm().toResource(categoryService.findCategoryById(categoryId)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CategoryListResource> findAllCategory(){
		return new ResponseEntity<CategoryListResource>(new CategoryListResourceAsm().toResource(categoryService.findAllCategory()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CategoryResource> saveategory(@RequestBody CategoryResource catRes){
		return new ResponseEntity<CategoryResource>(new CategoryResourceAsm().toResource(categoryService.saveCategory(catRes.toCategory())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catopetype/{catOpeTypeId}", method = RequestMethod.GET)
	public ResponseEntity<CatOpeTypeResource> findCatOpeTypeByd(@PathVariable Long catOpeTypeId){
		return new ResponseEntity<CatOpeTypeResource>(new CatOpeTypeResourceAsm().toResource(categoryService.findCatOpeTypeById(catOpeTypeId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catopetype/bycategory/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<CatOpeTypeListResource> findOpeTypesOfCategory(@PathVariable Long categoryId){
		return new ResponseEntity<CatOpeTypeListResource>(new CatOpeTypeListResourceAsm().toResource(categoryService.findOpeTypesOfCategory(categoryId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detailtitles/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<DetailTitleListResource> findDetailTitlesOfCategory(@PathVariable Long categoryId){
		return new ResponseEntity<DetailTitleListResource>(new DetailTitleListResourceAsm().toResource(categoryService.findDetailTitlesOfCategory(categoryId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catopetype/detailtitles/{catOpeTypeId}", method = RequestMethod.GET)
	public ResponseEntity<DetailTitleListResource> findDetailTitlesOfCatOpeTypey(@PathVariable Long catOpeTypeId){
		return new ResponseEntity<DetailTitleListResource>(new DetailTitleListResourceAsm().toResource(categoryService.findDetailTitlesOfCatOpeType(catOpeTypeId)), HttpStatus.OK);
	}
}
