package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.CatOpeType;
import com.webproject.core.models.entities.Category;

public interface CatOpeTypeRepo extends JpaRepository<CatOpeType, Long>{
	
	public List<CatOpeType> findByCategory(Category category);
}
