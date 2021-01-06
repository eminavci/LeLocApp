package com.webproject.core.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE_DETAIL")
public class MessageDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column( nullable = false, length = 1024)
	private String messageText;
	
	@Column( nullable = false)
	private Date date;
	
	private int statusForOwner;// 1: active, 0:passive, 2: Deleted
	private int statusForCustomer;// 1: active, 0:passive, 2: Deleted
	
	@ManyToOne
	private Message message;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
}
