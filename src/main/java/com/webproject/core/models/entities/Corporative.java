package com.webproject.core.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.webproject.core.common.CompanyType;

@Entity
@Table(name = "COMPANY")
public class Corporative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 128)
	private String officialName;
	@Column(length = 24)
	private String shortName;
	@Column(length = 24)
	private String webSite;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne
	private User user;

	@Column(nullable = false, length=20)
	@Enumerated(EnumType.STRING)
	private CompanyType type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@JsonBackReference
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CompanyType getType() {
		return type;
	}
	public void setType(CompanyType type) throws Exception {
		if(type == CompanyType.INDIVIDUAL)
			throw new Exception("Company Type can not be " + CompanyType.INDIVIDUAL.toString());
		this.type = type;
	}
}
