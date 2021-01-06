package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "COUNTRY")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 3)
	private String localeName;
	
	@Column(nullable = false, length = 12)
	private String continentName;
	
	@Column(nullable = false, length = 3)
	private String isoCode;
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@Column(nullable = false, length = 10)
	private String currencyCode;

	@Column(nullable = false, length = 6)
	private String phoneCode;
	
	@Column(length = 36)
	private String icon;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name="name", length = 50)
    @Column(name="value", length = 96)
    @CollectionTable(name="country_translation", joinColumns=@JoinColumn(name="id"))
	private Map<String, String> translation = new HashMap<>();
	
	@Column(length = 12)
	private String timeZone;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Region> regions = new ArrayList<Region>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLocaleName() {
		return localeName;
	}


	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}


	public String getContinentName() {
		return continentName;
	}


	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}


	public String getIsoCode() {
		return isoCode;
	}


	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getPhoneCode() {
		return phoneCode;
	}


	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public Map<String, String> getTranslation() {
		return translation;
	}


	public void setTranslation(Map<String, String> translation) {
		this.translation = translation;
	}


	public String getTimeZone() {
		return timeZone;
	}


	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@JsonManagedReference
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
}
