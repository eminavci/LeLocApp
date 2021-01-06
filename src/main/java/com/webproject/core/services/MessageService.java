package com.webproject.core.services;

import com.webproject.core.models.entities.Message;
import com.webproject.core.models.entities.MessageDetail;
import com.webproject.core.services.util.MessageList;

public interface MessageService {

	public Message findById(Long id);
	public MessageList findAllReceivedMessage(Long userId);
	public MessageList findAllSentMessage(Long userId);
	public MessageList findAllUnReadMessages(Long userId);
	public Message saveMessage(Long senderUserId, Long realEstateId, MessageDetail msgDetail);
	
	public Message sendMessageDetail(Long userId, Long messageId, MessageDetail msgDetail);
}
