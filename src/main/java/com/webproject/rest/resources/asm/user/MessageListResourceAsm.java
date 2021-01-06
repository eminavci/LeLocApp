package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.MessageList;
import com.webproject.rest.mvc.user.MessageController;
import com.webproject.rest.resources.user.MessageListResource;

public class MessageListResourceAsm extends ResourceAssemblerSupport<MessageList, MessageListResource>{

	public MessageListResourceAsm() {
		super(MessageController.class, MessageListResource.class);
	}

	@Override
	public MessageListResource toResource(MessageList messageList) {
		MessageListResource msgListRes = new MessageListResource();
		msgListRes.setMsgListRes(new MessageResourceAsm().toResources(messageList.getMessages()));
		return msgListRes;
	}

}
