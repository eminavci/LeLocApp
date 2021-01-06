package com.webproject.rest.resources.place;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Region;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionResource  extends ResourceSupport{

	private String name;
	private Long oid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public Region toRegion(){
		Region reg = new Region();
		reg.setName(this.getName());
		
		return reg;
	}
	
}
