package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private RealEstate realEstate; // This is for Messages I receive for my real estate posts 
	
	@ManyToOne
	private User sender; // Thi is for the messages I send
	
	private boolean notifyOwner;// true YES
	private boolean notifySender;
	private int statusForOwner;// 1: active, 0:passive, 2: Deleted
	private int statusForCustomer;// 1: active, 0:passive, 2: Deleted
	private Date date;
	
	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
	private List<MessageDetail> messageDetail = new ArrayList<MessageDetail>();

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
	@JsonBackReference
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public boolean isNotifyOwner() {
		return notifyOwner;
	}
	public void setNotifyOwner(boolean notifyOwner) {
		this.notifyOwner = notifyOwner;
	}
	public boolean isNotifySender() {
		return notifySender;
	}
	public void setNotifySender(boolean notifySender) {
		this.notifySender = notifySender;
	}
	public int getStatusForOwner() {
		return statusForOwner;
	}
	public void setStatusForOwner(int statusForOwner) {
		this.statusForOwner = statusForOwner;
	}
	public int getStatusForCustomer() {
		return statusForCustomer;
	}
	public void setStatusForCustomer(int statusForCustomer) {
		this.statusForCustomer = statusForCustomer;
	}
	public List<MessageDetail> getMessageDetail() {
		return messageDetail;
	}
	public void setMessageDetail(List<MessageDetail> messageDetail) {
		this.messageDetail = messageDetail;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
