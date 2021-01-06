package com.webproject.core.services.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webproject.core.common.api.Const;
import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.Message;
import com.webproject.core.models.entities.MessageDetail;
import com.webproject.core.models.entities.RealEstate;
import com.webproject.core.models.entities.User;
import com.webproject.core.repositories.MessageDetailRepo;
import com.webproject.core.repositories.MessageRepo;
import com.webproject.core.services.MessageService;
import com.webproject.core.services.RealEstateService;
import com.webproject.core.services.UserService;
import com.webproject.core.services.exceptions.UserException;
import com.webproject.core.services.util.MessageList;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepo messageRepo;
	@Autowired
	MessageDetailRepo messageDetailRepo;
	@Autowired
	UserService userService;
	@Autowired
	RealEstateService realEstateService;
	
	@Override
	public Message findById(Long id) {
		Message msg = messageRepo.findOne(id);
		if(msg == null)
			throw new UserException(ErrKeys.USER_MSG_NOT_FOUND);
		return msg;
	}

	@Override
	public MessageList findAllReceivedMessage(Long userId) {
		return new MessageList(messageRepo.findByStatusForOwnerAndRealEstateOwner(Const.MSG_STATUS_ACTIVE, findUserById(userId, true)));
	}

	@Override
	public MessageList findAllSentMessage(Long userId) {
		return new MessageList(messageRepo.findStatusForCustomerAndBySender(Const.MSG_STATUS_ACTIVE, findUserById(userId, true)));
	}

	@Override
	// TODO 2- Modify this method. This is fetching just unread messages which I recieved for my realestate post
	public MessageList findAllUnReadMessages(Long userId) {
		return new MessageList(messageRepo.findByStatusForOwnerAndNotifyOwnerAndRealEstateOwner(Const.MSG_STATUS_ACTIVE, true, findUserById(userId, true)));
	}
	
	@Override
	public Message saveMessage(Long senderUserId, Long realEstateId, MessageDetail msgDetail) {
		User senderUser = findUserById(senderUserId, true);
		RealEstate realEstate = findRealEstateById(realEstateId, true);
		if(senderUser.getId() == realEstate.getOwner().getId())
			throw new UserException(ErrKeys.USER_CANNOT_SEND_MSG_HIMSELF);
		
		Message msg = new Message();
		msg.setNotifyOwner(true);
		msg.setNotifySender(false);
		msg.setRealEstate(realEstate);
		msg.setSender(senderUser);
		msg.setStatusForCustomer(Const.MSG_STATUS_ACTIVE);
		msg.setStatusForOwner(Const.MSG_STATUS_ACTIVE);
		msg.setDate(new Date());
		
		messageRepo.save(msg);
		
		msgDetail.setMessage(msg);
		msgDetail.setStatusForCustomer(Const.MSG_STATUS_ACTIVE);
		msgDetail.setStatusForOwner(Const.MSG_STATUS_ACTIVE);
		msgDetail.setDate(new Date());
		
		messageDetailRepo.save(msgDetail);
		
		return msg;
	}

	@Override
	public Message sendMessageDetail(Long userId, Long messageId, MessageDetail msgDetail) {
		User senderUser = findUserById(userId, true);
		Message msg = findById(messageId);
		
		msgDetail.setDate(new Date());
		msgDetail.setStatusForCustomer(Const.MSG_STATUS_ACTIVE);
		msgDetail.setStatusForOwner(Const.MSG_STATUS_ACTIVE);
		msgDetail.setMessage(msg);
		
		if(senderUser.getId() == msg.getRealEstate().getOwner().getId()){ // User sending message for his own real estate
			msg.setNotifySender(true);
			msg.setNotifyOwner(false);
		} else {
			msg.setNotifySender(false);
			msg.setNotifyOwner(true);
		}
		
		return msg;
	}
	
	private User findUserById(Long userId, boolean excerption){
		try {
			return userService.findUserById(userId);
		} catch (Exception e) {
			if(excerption)
				throw e;
		}
		return null;
	}

	private RealEstate findRealEstateById(Long id,  boolean exception){
		
		return null;
	}
}
