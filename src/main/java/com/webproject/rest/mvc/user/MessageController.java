package com.webproject.rest.mvc.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webproject.core.models.entities.MessageDetail;
import com.webproject.core.services.MessageService;
import com.webproject.rest.resources.asm.user.MessageListResourceAsm;
import com.webproject.rest.resources.asm.user.MessageResourceAsm;
import com.webproject.rest.resources.user.MessageListResource;
import com.webproject.rest.resources.user.MessageResource;

@Controller
@RequestMapping(value = "/message", produces = "application/json; charset=UTF-8")
public class MessageController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
	public ResponseEntity<MessageResource> findMessageById(@PathVariable Long messageId){
		return new ResponseEntity<MessageResource>(new MessageResourceAsm().toResource(messageService.findById(messageId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{senderId}/{realEstateId}", method = RequestMethod.POST)
	public ResponseEntity<MessageResource> saveMsg(@PathVariable Long senderId, @PathVariable Long realEstateId, @RequestBody MessageDetail msgDetail){
		return new ResponseEntity<MessageResource>(new MessageResourceAsm().toResource(messageService.saveMessage(senderId, realEstateId, msgDetail)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/send/{senderId}/{messageId}", method = RequestMethod.POST)
	public ResponseEntity<MessageResource> sendMessageDetail(@PathVariable Long senderId, @PathVariable Long messageId, @RequestBody MessageDetail msgDetail){
		return new ResponseEntity<MessageResource>(new MessageResourceAsm().toResource(messageService.sendMessageDetail(senderId, messageId, msgDetail)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sent/{userId}", method = RequestMethod.GET)
	public ResponseEntity<MessageListResource> findSentMessage(@PathVariable Long userId){ // Messages I send for someones real estate
		return new ResponseEntity<MessageListResource>(new MessageListResourceAsm().toResource(messageService.findAllSentMessage(userId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/received/{userId}", method = RequestMethod.GET)
	public ResponseEntity<MessageListResource> findReceivedMessage(@PathVariable Long userId){ // Messages I received for my real estates
		return new ResponseEntity<MessageListResource>(new MessageListResourceAsm().toResource(messageService.findAllReceivedMessage(userId)), HttpStatus.OK);
	}
}
