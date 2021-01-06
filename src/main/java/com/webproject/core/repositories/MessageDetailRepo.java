package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.MessageDetail;

public interface MessageDetailRepo extends JpaRepository<MessageDetail, Long>{

}
