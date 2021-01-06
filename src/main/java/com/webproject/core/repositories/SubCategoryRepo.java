package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.CatOpeType;
import com.webproject.core.models.entities.Category;
import com.webproject.core.models.entities.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long>{

	public List<SubCategory> findByCatOpeTypeAndIsActive(CatOpeType opeType, boolean isActive);
}
