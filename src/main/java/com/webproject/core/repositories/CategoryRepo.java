package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Category;
@JsonIgnoreProperties(ignoreUnknown = true)
public interface CategoryRepo extends JpaRepository<Category, Long>{

	public List<Category> findByIsActive(boolean isActive);
}
