package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Message;

public class MessageList {

	private List<Message> messages = new ArrayList<Message>();

	public MessageList(List<Message> messages) {
		super();
		this.messages = messages == null ? new ArrayList<Message>() : messages;
	}

	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
