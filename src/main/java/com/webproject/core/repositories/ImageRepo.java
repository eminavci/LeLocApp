package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Image;
import com.webproject.core.models.entities.RealEstate;

public interface ImageRepo extends JpaRepository<Image, Long>{

	public List<Image> findByRealEstate(RealEstate realEstate);
	
}
