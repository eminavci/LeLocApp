package com.webproject.rest.resources.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Message;
import com.webproject.core.models.entities.MessageDetail;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResource extends ResourceSupport{
	
	private boolean notifyOwner;// true YES
	private boolean notifySender;
	private int statusForOwner;// 1: active, 0:passive, 2: Deleted
	private int statusForCustomer;// 1: active, 0:passive, 2: Deleted
	private List<MessageDetail> messageDetail = new ArrayList<MessageDetail>();
	
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
	public Message toMessage(){
		Message msg = new Message();
		msg.setNotifyOwner(this.isNotifyOwner());
		msg.setNotifySender(this.isNotifySender());
		msg.setStatusForCustomer(this.getStatusForCustomer());
		msg.setStatusForOwner(this.getStatusForOwner());
		msg.setMessageDetail(this.getMessageDetail());
		
		return msg;
	}
}
