package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.Message;
import com.webproject.rest.mvc.user.MessageController;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.MessageResource;

public class MessageResourceAsm extends ResourceAssemblerSupport<Message, MessageResource>{

	public MessageResourceAsm() {
		super(UserController.class, MessageResource.class);
	}

	@Override
	public MessageResource toResource(Message message) {
		MessageResource msgRes = new MessageResource();
		msgRes.setMessageDetail(message.getMessageDetail());
		msgRes.setNotifyOwner(message.isNotifyOwner());
		msgRes.setNotifySender(message.isNotifySender());
		msgRes.setStatusForCustomer(message.getStatusForCustomer());
		msgRes.setStatusForOwner(message.getStatusForOwner());

		msgRes.add(linkTo(methodOn(MessageController.class).findMessageById(message.getId())).withSelfRel());
		msgRes.add(linkTo(methodOn(UserController.class).findUserById(message.getSender().getId())).withRel("sender"));
		//msgRes.add(linkTo(methodOn()));
		
		return msgRes;
	}

}
