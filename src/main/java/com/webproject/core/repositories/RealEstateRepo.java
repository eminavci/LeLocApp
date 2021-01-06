package com.webproject.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.RealEstate;

public interface RealEstateRepo extends JpaRepository<RealEstate, Long>{

}
