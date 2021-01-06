package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.SubCategory;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryResource extends ResourceSupport{
	
	private Long oid;
	private String msgCode;
	private String name; //Default in English
	private boolean isActive;
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public SubCategory toSubCategory(){
		SubCategory subCat = new SubCategory();
		subCat.setActive(this.isActive());
		subCat.setMsgCode(this.getMsgCode());
		subCat.setName(this.getName());
	
		return subCat;
	}
}
