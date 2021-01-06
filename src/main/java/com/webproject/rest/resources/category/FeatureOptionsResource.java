package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.FeatureOptions;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureOptionsResource extends ResourceSupport{
	private Long oid;
	private String name;// this is actually a value for Fetaure
	private String msgCode;
	
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
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public FeatureOptions toFeatureOptions(){
		FeatureOptions fos = new FeatureOptions();
		fos.setMsgCode(this.getMsgCode());
		fos.setName(this.getName());
		return fos;
	}
	
	
}
