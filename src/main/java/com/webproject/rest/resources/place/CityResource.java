package com.webproject.rest.resources.place;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.City;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResource extends ResourceSupport {
	private Long oid;
	private String name;
	private double latitudeMin;
	private double latitudeMax;
	private double longitudeMin;
	private double longitudeMax;

	private double longitude;
	private double latitude;
	private String code;

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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public City toCity() {
		City city = new City();

		city.setCode(this.getCode());
		city.setLatitude(this.getLatitude());
		city.setLongitude(this.getLongitude());
		city.setLatitudeMax(this.getLatitudeMax());
		city.setLatitudeMin(this.getLatitudeMin());
		city.setLongitudeMax(this.getLongitudeMax());
		city.setLongitudeMin(this.getLongitudeMin());
		city.setName(this.getName());

		return city;
	}
}
