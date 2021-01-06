package com.webproject.core.services.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webproject.core.common.CompanyType;
import com.webproject.core.common.api.Const;
import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.common.api.Util;
import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.DetailDetail;
import com.webproject.core.models.entities.FeatureOptions;
import com.webproject.core.models.entities.Image;
import com.webproject.core.models.entities.RealEstate;
import com.webproject.core.models.entities.SubCategory;
import com.webproject.core.models.entities.Town;
import com.webproject.core.models.entities.UploadedFile;
import com.webproject.core.models.entities.User;
import com.webproject.core.repositories.AddressRepo;
import com.webproject.core.repositories.ImageRepo;
import com.webproject.core.repositories.RealEstateRepo;
import com.webproject.core.repositories.UploadedFileRepo;
import com.webproject.core.services.DetailTitleService;
import com.webproject.core.services.RealEstateService;
import com.webproject.core.services.SubCategoryService;
import com.webproject.core.services.TownService;
import com.webproject.core.services.UserService;
import com.webproject.core.services.exceptions.RealEstateNotFound;
import com.webproject.core.services.util.ImageList;
import com.webproject.core.services.util.RealEstateList;
@Service
@Transactional
public class RealEstateServiceImpl extends BaseService implements RealEstateService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RealEstateRepo realEstateRepo;
	@Autowired
	UserService userService;
	@Autowired
	SubCategoryService subCatService;
	@Autowired
	UploadedFileRepo fileRepo;
	@Autowired
	TownService townService;
	@Autowired
	DetailTitleService detailService;
	@Autowired
	AddressRepo addressRepo;
	@Autowired
	ImageRepo imageRepo;
	
	
	
	@Override
	public RealEstate findRealEstateById(Long id) {
		RealEstate re = realEstateRepo.findOne(id);
		if(re == null)
			throw new RealEstateNotFound("Real Estate is not found by given id : " + id, ErrKeys.REAL_ESTATE_NOT_FOUND);
		return re;
	}

	@Override
	public RealEstate saveRealEstate(Long userId, Long subCategoryId, RealEstate realEstate) {
		User user = findUserById(userId, true);
		SubCategory subCat = findSubCatById(subCategoryId, true);
		
		fieldMustHasText(realEstate.getName(), "realEstate.getName");
		
		realEstate.setSubCategory(subCat);
		realEstate.setOwner(user);
		if(user.getCompany() == null)
			realEstate.setFromWho(CompanyType.INDIVIDUAL);
		else
			realEstate.setFromWho(user.getCompany().getType());
		
		realEstate.setNo(Util.generateNo());
		realEstate.setPublishDate(new Date());
		realEstate.setViewCount(1L);
		realEstate.setStatus(Const.REAL_ESTATE_STATUS_ACTIVE);
		
		realEstateRepo.save(realEstate);
	
		List<Image> images = insertmages(userId, realEstate);
		realEstate.setImages(images);
		
		return realEstate;
	}
	
	private User findUserById(Long userId, boolean exception){
		try {
			return userService.findUserById(userId);
		} catch (Exception e) {
			if(exception)
				throw e;
		}
		return null;
	}
	
	private SubCategory findSubCatById(Long subCatId, boolean exception){
		try {
			return subCatService.findSubCategorById(subCatId);
		} catch (Exception e) {
			if(exception)
				throw e;
		}
		return null;
	}
	
	private List<Image> insertmages(Long userid, RealEstate realEstate){
		List<UploadedFile> files = fileRepo.findByUseridAndIsActive(userid, true);
		List<Image> images = new ArrayList<>();
		String curDate = Util.getFormattedCurDate();
		for (UploadedFile uploadedFile : files) {
			Image img = new Image();
			// TODO try to create images thumbs
			
			img.setActive(true);
			img.setName(curDate + "_" + Util.getAnID() + ".jpeg");
			img.setRealEstate(realEstate);
			img.setThumb("");
			img.setRealEstate(realEstate);
			
			OutputStream out = null;

			try {
				File path = new File("/home/emin/git/LeLocAppRepository/LeLocApp/src/main/resources/webapp/static/adimages/" + img.getName());
			    out = new BufferedOutputStream(new FileOutputStream(path));
			    out.write(uploadedFile.getFile());
			    imageRepo.save(img);
				fileRepo.delete(uploadedFile);
				images.add(img);
			} catch(Exception e){
				logger.error("Error Occured while writing images : " , e);
			} finally {
				try {
					 if (out != null)
						 out.close();
				} catch (IOException e) {
					
				}
			}
			
		}
		return images;
	}

	@Override
	public UploadedFile uploadFile(UploadedFile uploadedFile) {
		
		return fileRepo.saveAndFlush(uploadedFile);
	}

	/* TODO Change this method!!*/
	@Override
	@Deprecated
	public boolean deleteUploadedFile(String name, long size) {
		
		
		long f = size - 2000; // 2 KB difference
		long m = size + 2000;
		
		fileRepo.deletebyCustomQuery(name, f, m);
		
		
		return true;
	}

	@Override
	public RealEstate saveRealEstateAddress(Long realEstateId, Long townId, Address address) {
		Town town = townService.findById(townId);
		
		address.setTown(town);
		address.setCity(town.getCity());
		address.setRegion(town.getCity().getRegion());
		address.setCountry(town.getCity().getRegion().getCountry());
		
		addressRepo.save(address);
		
		RealEstate re = findRealEstateById(realEstateId);
		re.setAddress(address);
		
		return realEstateRepo.save(re);
	}

	@Override
	public RealEstate saveRealEstateDetails(Long realEstateId, Set<Long> details) {
		Set<DetailDetail> dets = new HashSet<DetailDetail>();
		for (Long long1 : details) {
			dets.add(detailService.findDetailDetailById(long1));
		}
		
		RealEstate re = findRealEstateById(realEstateId);
		re.setDetailDetails(dets);
		
		return re;
	}

	@Override
	public RealEstate saveRealEstateFeatures(Long realEstateId, Set<Long> featureOptions) {
		Set<FeatureOptions> fos = new HashSet<FeatureOptions>();
		for (Long long1 : featureOptions) {
			fos.add(subCatService.findFeatureOptionById(long1));
		}
		RealEstate re = findRealEstateById(realEstateId);
		re.setFeatures(fos);
		return realEstateRepo.save(re);
	}

	@Override
	public Image findImageById(Long imageId) {
		Image img = imageRepo.findOne(imageId);
		if(img == null)
			throw new RealEstateNotFound("Real Estate image is not foud by given id : " + imageId, ErrKeys.REAL_ESTATE_IMG_NOT_FOUND);
		return img;
	}

	@Override
	public ImageList findRealEstateImages(Long realEstateId) {
		return new ImageList(findRealEstateById(realEstateId).getImages());
	}

	@Override
	public RealEstateList showCase() {
		return new RealEstateList(realEstateRepo.findAll());
	}

}
