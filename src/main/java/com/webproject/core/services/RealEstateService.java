package com.webproject.core.services;

import java.util.Set;
import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.Image;
import com.webproject.core.models.entities.RealEstate;
import com.webproject.core.models.entities.UploadedFile;
import com.webproject.core.services.util.ImageList;
import com.webproject.core.services.util.RealEstateList;

public interface RealEstateService {

	public RealEstate findRealEstateById(Long id);
	public RealEstate saveRealEstate(Long userId, Long subCategoryId, RealEstate realEstate);
	public RealEstate saveRealEstateAddress(Long realEstateId, Long townId, Address address);
	public RealEstate saveRealEstateDetails(Long realEstateId, Set<Long> details);
	public RealEstate saveRealEstateFeatures(Long realEstateId, Set<Long> featureOptions);
	
	public Image findImageById(Long imageId);
	public ImageList findRealEstateImages(Long realEstateId);
	
	
	public UploadedFile uploadFile(UploadedFile uploadedFile);
	public boolean deleteUploadedFile(String name, long size);
	
	public RealEstateList showCase();
	
}
