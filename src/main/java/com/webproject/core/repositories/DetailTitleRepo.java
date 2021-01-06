package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.Category;
import com.webproject.core.models.entities.DetailTitle;

public interface DetailTitleRepo extends JpaRepository<DetailTitle, Long>{

}
