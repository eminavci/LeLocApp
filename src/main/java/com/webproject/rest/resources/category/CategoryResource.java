package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Category;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResource extends ResourceSupport{
	private Long oid;
	private String name;// default in English
	private String msgCode;
	private boolean isActive;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
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
	
	public Category toCategory(){
		Category cat = new Category();
		cat.setActive(this.isActive());
		cat.setMsgCode(this.getMsgCode());
		cat.setName(this.getName());
		
		return cat;
	}
	
}
