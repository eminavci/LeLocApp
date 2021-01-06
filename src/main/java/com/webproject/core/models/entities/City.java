package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CITY")
public class City { // Ex : Seine-Saint-Denis
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 48)
	private String name;
	
	private double latitudeMin;
	private double latitudeMax;
	private double longitudeMin;
	private double longitudeMax;
	
	@Column(length=20, precision=3)
	private double longitude;
	@Column(length=20, precision=3)
	private double latitude;
	
	@Column(nullable = false, length = 10)
	private String code;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Region region;
	
	@OneToMany(mappedBy = "city")
	private List<Town> towns = new ArrayList<Town>();

	@Transient
	public String fname;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitudeMin() {
		return latitudeMin;
	}

	public void setLatitudeMin(double latitudeMin) {
		this.latitudeMin = latitudeMin;
	}

	public double getLatitudeMax() {
		return latitudeMax;
	}

	public void setLatitudeMax(double latitudeMax) {
		this.latitudeMax = latitudeMax;
	}

	public double getLongitudeMin() {
		return longitudeMin;
	}

	public void setLongitudeMin(double longitudeMin) {
		this.longitudeMin = longitudeMin;
	}

	public double getLongitudeMax() {
		return longitudeMax;
	}

	public void setLongitudeMax(double longitudeMax) {
		this.longitudeMax = longitudeMax;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonBackReference
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@JsonManagedReference
	public List<Town> getTowns() {
		return towns;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
