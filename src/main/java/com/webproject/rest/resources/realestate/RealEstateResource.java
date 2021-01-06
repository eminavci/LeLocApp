package com.webproject.rest.resources.realestate;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.common.CompanyType;
import com.webproject.core.models.entities.RealEstate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealEstateResource extends ResourceSupport{
	private String no;
	private String name;
	private String explanation;
	private Double price;
	private String currency;
	private Date publishDate;
	private double meterSquare;
	private int status;// 0 - passive by System, 1: in publishment(Active), 2: waiting for moderator acceptance,  3: passive by user, 4: deleted, 5: PROCESS COMPLETED
	private long viewCount;
	private Long oid;
	private CompanyType fromWho;
	private String pictureUrl;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}	
	public CompanyType getFromWho() {
		return fromWho;
	}
	public void setFromWho(CompanyType fromWho) {
		this.fromWho = fromWho;
	}
	public double getMeterSquare() {
		return meterSquare;
	}
	public void setMeterSquare(double meterSquare) {
		this.meterSquare = meterSquare;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public RealEstate toRealEstate(){
		RealEstate re = new RealEstate();
		re.setCurrency(this.getCurrency());
		re.setExplanation(this.getExplanation());
		re.setName(this.getName());
		re.setNo(this.getNo());
		re.setPrice(this.getPrice());
		re.setPublishDate(this.getPublishDate());
		re.setStatus(this.getStatus());
		re.setFromWho(this.getFromWho());
		re.setMeterSquare(this.getMeterSquare());
		
		return re;
	}
}
