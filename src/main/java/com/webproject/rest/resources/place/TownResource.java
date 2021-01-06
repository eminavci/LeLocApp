package com.webproject.rest.resources.place;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Town;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TownResource  extends ResourceSupport{
	private Long oid;
	private double latitudeMin;
	private double latitudeMax;
	private double longitudeMin;
	private double longitudeMax;
	private double longitude;
	private double latitude;
	private String postCode;
	private String name;
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
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
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
	
	public Town toTown(){
		Town town = new Town();
		town.setLatitude(this.getLatitude());
		town.setLatitudeMax(this.getLatitudeMax());
		town.setLatitudeMin(this.getLatitudeMin());
		town.setLongitude(this.getLongitude());
		town.setLongitudeMax(this.getLongitudeMax());
		town.setLongitudeMin(this.getLongitudeMin());
		town.setName(this.getName());
		town.setPostCode(this.getPostCode());
		
		return town;
	}
}
