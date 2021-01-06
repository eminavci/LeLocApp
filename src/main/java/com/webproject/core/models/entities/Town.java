package com.webproject.core.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TOWN")
public class Town {// example Sevran

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double latitudeMin;
	private double latitudeMax;
	private double longitudeMin;
	private double longitudeMax;
	
	@Column(length=20, precision=3)
	private double longitude;
	@Column(length=20, precision=3)
	private double latitude;
	
	@Column(nullable = false, length = 10)
	private String postCode;
	
	@Column(nullable = false, length = 48)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private City city; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@JsonBackReference
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
