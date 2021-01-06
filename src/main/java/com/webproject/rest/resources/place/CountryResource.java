package com.webproject.rest.resources.place;

import java.util.Map;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Country;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryResource extends ResourceSupport{

	private String name;
	private String phoneCode;
	private String localeName;
	private String currencyCode;
	private String isCode;
	private String continentName;
	private String icon;
	private String timeZone;
	private Long oid;
	private Map<String, String> translation;
	//private List<Region> regions = new ArrayList<>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Map<String, String> getTranslation() {
		return translation;
	}
	public void setTranslation(Map<String, String> translation) {
		this.translation = translation;
	}
	public String getIsCode() {
		return isCode;
	}
	public void setIsCode(String isCode) {
		this.isCode = isCode;
	}
	public String getContinentName() {
		return continentName;
	}
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public Country toCountry(){
		
		Country country = new Country();
		country.setName(			this.getName());
		country.setCurrencyCode(	this.getCurrencyCode());
		country.setContinentName(	this.getContinentName());
		country.setPhoneCode(		this.getPhoneCode());
		country.setIcon(			this.getIcon());
		country.setIsoCode(			this.getIsCode());
		country.setLocaleName(		this.getLocaleName());
		country.setTimeZone(		this.getTimeZone());
		country.setTranslation(		this.getTranslation());

		return country;
	}
}
