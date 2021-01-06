package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.CatOpeType;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatOpeTypeResource extends ResourceSupport{
	
	private Long oid;
	private String name;
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
	public CatOpeType toCatOpeType(){
		CatOpeType opeType = new CatOpeType();
		opeType.setMsgCode(this.getMsgCode());
		opeType.setName(this.getName());
		return opeType;
	}
}
