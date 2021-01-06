package com.webproject.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.core.models.entities.DetailDetail;
import com.webproject.core.models.entities.DetailTitle;

public interface DetailDetailRepo extends JpaRepository<DetailDetail, Long>{

	public List<DetailDetail> findByDetailTitleAndIsActive(DetailTitle detailTitle, boolean isActive);
}
