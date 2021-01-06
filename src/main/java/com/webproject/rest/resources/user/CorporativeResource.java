package com.webproject.rest.resources.user;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.common.CompanyType;
import com.webproject.core.models.entities.Corporative;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporativeResource extends ResourceSupport{

	private Long oid;
	private String officialName;
	private String shortName;
	private String webSite;
	private CompanyType type;
	
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public CompanyType getType() {
		return type;
	}
	public void setType(CompanyType type) {
		this.type = type;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Corporative toCorporative(){
		Corporative corp = new Corporative();
		corp.setOfficialName(this.getOfficialName());
		corp.setShortName(this.getShortName());
		corp.setWebSite(this.getWebSite());
		try {
			corp.setType(this.getType());
		} catch (Exception e) {}
		corp.setId(this.getOid());
		return corp;
	}
}
