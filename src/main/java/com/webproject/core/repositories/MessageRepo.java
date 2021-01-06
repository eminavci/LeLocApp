package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Message;
import com.webproject.core.models.entities.User;

public interface MessageRepo extends JpaRepository<Message, Long>{

	public List<Message> findByStatusForOwnerAndRealEstateOwner(int statusForOwner, User user);
	public List<Message> findStatusForCustomerAndBySender(int satusForCustomer, User user);
	// TODO 1- Modify this method. This is fetching just unread messages which I recieved for my realestate post
	public List<Message> findByStatusForOwnerAndNotifyOwnerAndRealEstateOwner(int statusForOwner, boolean notifyOwner, User user);
	
	
}
