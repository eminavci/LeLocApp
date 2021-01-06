package com.webproject.core.services;

import com.webproject.core.models.entities.DetailDetail;
import com.webproject.core.models.entities.DetailTitle;
import com.webproject.core.services.util.DetailDetailList;

public interface DetailTitleService {

	public DetailTitle findDetailTitleById(Long detailTitleId);
	public DetailDetail findDetailDetailById(Long detailDetailId);
	
	public DetailDetailList findDetailDetailOfTitle(Long detailTitleId);
	
	public DetailTitle saveDetailTitle(DetailTitle detailTitle);
	public DetailDetail saveDetailDetail(Long detailTitleId, DetailDetail detailDetail);
	public DetailDetail saveDetailDetail(DetailDetail detailDetail);
	
	
	
}
