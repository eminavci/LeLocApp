package com.webproject.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "REAL_ESTATE_TRANSACTION")
public class RealEstateTransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private RealEstate realEstate;
	private boolean isActive;// only 1 of transaction of real estate can be active at a time
	private boolean transactionDate;
	
	@ManyToOne
	private User operator; // Who did the transaction. Since it is realestate trans history. for sure there is session
	
	/* ************** In the Consts file
	 * 1 : Real Estate added by owner
	 * 2 : Moderator confirmed the real estate
	 * 3 : Moderator reject the realestate
	 * 4 : Owner cancelled the publishment of Real estate
	 * 4 : Real Estate's publishment Date is expired
	 * 5 : Real Estate publishöent date is extended 
	 * 6 : Moderator cancel the publishment of Real Estate with a reason
	 * 7 : RealEstate is cancaled by Moderator because of report
	 *  
	 * */
	private int transactionType;
	
	@Column(length = 600)
	private String transactionMessage;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RealEstate getRealEstate() {
		return realEstate;
	}
	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(boolean transactionDate) {
		this.transactionDate = transactionDate;
	}
	public User getOperator() {
		return operator;
	}
	public void setOperator(User operator) {
		this.operator = operator;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionMessage() {
		return transactionMessage;
	}
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}
}
