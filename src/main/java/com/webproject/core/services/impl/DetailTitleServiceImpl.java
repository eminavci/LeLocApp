package com.webproject.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.DetailDetail;
import com.webproject.core.models.entities.DetailTitle;
import com.webproject.core.repositories.DetailDetailRepo;
import com.webproject.core.repositories.DetailTitleRepo;
import com.webproject.core.services.CategoryService;
import com.webproject.core.services.DetailTitleService;
import com.webproject.core.services.SubCategoryService;
import com.webproject.core.services.exceptions.CategoryException;
import com.webproject.core.services.util.DetailDetailList;

@Service
@Transactional
public class DetailTitleServiceImpl extends BaseService implements DetailTitleService{

	@Autowired
	DetailTitleRepo detailTitleRepo;
	@Autowired
	DetailDetailRepo detailDetailRepo;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SubCategoryService subCategoryService;

	
	@Override
	public DetailTitle findDetailTitleById(Long detailTitleId) {
		DetailTitle dt = detailTitleRepo.findOne(detailTitleId);
		if(dt == null)
			throw new CategoryException("Detail Title is not found by given id : " + detailTitleId, ErrKeys.CATEGORY_NOT_FOUND);
		return dt;
	}

	@Override
	public DetailDetail findDetailDetailById(Long detailDetailId) {
		DetailDetail dd = detailDetailRepo.findOne(detailDetailId);
		if(dd == null)
			throw new CategoryException("Detail Detail is not found by given id : " + detailDetailId, ErrKeys.CATEGORY_NOT_FOUND);
		return dd;
	}

	@Override
	@Cacheable(value="detailCacheByDTitleId", key="#detailTitleId")
	public DetailDetailList findDetailDetailOfTitle(Long detailTitleId) {
		return new DetailDetailList(detailDetailRepo.findByDetailTitleAndIsActive(findDetailTitleById(detailTitleId), true));
	}

	@Override
	public DetailTitle saveDetailTitle(DetailTitle detailTitle) {
		fieldMustHasText(detailTitle.getMsgCode(), "detailTitle.getMsgCode");
		fieldMustHasText(detailTitle.getName(), "detailTitle.getName");
		return detailTitleRepo.save(detailTitle);
	}

	@Override
	public DetailDetail saveDetailDetail(Long detailTitleId, DetailDetail detailDetail) {
		DetailTitle dt = findDetailTitleById(detailTitleId);
		detailDetail.setDetailTitle(dt);
		
		return saveDetailDetail(detailDetail);
	}

	@Override
	public DetailDetail saveDetailDetail(DetailDetail detailDetail) {
		fieldMustHasText(detailDetail.getMsgCode(), "detailDetail.getMsgCode");
		fieldMustHasText(detailDetail.getName(), "detailDetail.getName");
		return detailDetailRepo.save(detailDetail);
	}

	

}
