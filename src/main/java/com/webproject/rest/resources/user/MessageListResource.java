package com.webproject.rest.resources.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class MessageListResource extends ResourceSupport{

	private List<MessageResource> msgListRes = new ArrayList<MessageResource>();

	public List<MessageResource> getMsgListRes() {
		return msgListRes;
	}
	public void setMsgListRes(List<MessageResource> msgListRes) {
		this.msgListRes = msgListRes;
	}
}
