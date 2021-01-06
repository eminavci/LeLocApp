package com.webproject.rest.mvc.realestate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.webproject.core.models.entities.RealEstate;
import com.webproject.core.models.entities.UploadedFile;
import com.webproject.core.services.RealEstateService;
import com.webproject.core.services.util.DetailDetailList;
import com.webproject.core.services.util.FeatureOptionsList;
import com.webproject.rest.resources.asm.category.DetailDetailListResourceAsm;
import com.webproject.rest.resources.asm.category.FeatureOptionsListResourceAsm;
import com.webproject.rest.resources.asm.realestate.ImageListResourceAsm;
import com.webproject.rest.resources.asm.realestate.ImageResourceAsm;
import com.webproject.rest.resources.asm.realestate.RealEstateListResourceAsm;
import com.webproject.rest.resources.asm.realestate.RealEstateResourceAsm;
import com.webproject.rest.resources.asm.user.AddressResourceAsm;
import com.webproject.rest.resources.category.DetailDetailListResource;
import com.webproject.rest.resources.category.FeatureOptionsListResource;
import com.webproject.rest.resources.realestate.ImageListResource;
import com.webproject.rest.resources.realestate.ImageResource;
import com.webproject.rest.resources.realestate.RealEstateListResource;
import com.webproject.rest.resources.realestate.RealEstateResource;
import com.webproject.rest.resources.user.AddressResource;

@RestController
@RequestMapping(value = "/realestate", produces = "application/json; charset=UTF-8")
public class RealEstateController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RealEstateService realEstateService;

	@RequestMapping(value ="/{realEstateId}")
	public ResponseEntity<RealEstateResource> findRealEstateById(@PathVariable Long realEstateId){	
		RealEstate returnRealEstate=realEstateService.findRealEstateById(realEstateId);
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(returnRealEstate), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/dene/{realEstateId}")
	public ResponseEntity<RealEstateResource> deneme(@PathVariable Long realEstateId, HttpServletRequest request){
		
		logger.info("MY PATH : " + request.getContextPath());

		
		RealEstate returnRealEstate=realEstateService.findRealEstateById(realEstateId);
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(returnRealEstate), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/features/{realEstateId}")
	public ResponseEntity<FeatureOptionsListResource> findRealEstateFeatures(@PathVariable Long realEstateId){
		RealEstate re=realEstateService.findRealEstateById(realEstateId);
		return new ResponseEntity<FeatureOptionsListResource>(new FeatureOptionsListResourceAsm().toResource(new FeatureOptionsList(re.getFeatures())), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/details/{realEstateId}")
	public ResponseEntity<DetailDetailListResource> findRealEstateDetails(@PathVariable Long realEstateId){
		RealEstate re=realEstateService.findRealEstateById(realEstateId);
		return new ResponseEntity<DetailDetailListResource>(new DetailDetailListResourceAsm().toResource(new DetailDetailList(re.getDetailDetails())), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/address/{realEstateId}")
	public ResponseEntity<AddressResource> findRealEstateAddress(@PathVariable Long realEstateId){
		RealEstate re=realEstateService.findRealEstateById(realEstateId);
		return new ResponseEntity<AddressResource>(new AddressResourceAsm().toResource(re.getAddress()), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/image/{imageId}")
	public ResponseEntity<ImageResource> findImageById(@PathVariable Long imageId){
		return new ResponseEntity<ImageResource>(new ImageResourceAsm().toResource(realEstateService.findImageById(imageId)), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/image/byrealestate/{realEstateId}")
	public ResponseEntity<ImageListResource> findRealEstateImages(@PathVariable Long realEstateId){
		return new ResponseEntity<ImageListResource>(new ImageListResourceAsm().toResource(realEstateService.findRealEstateImages(realEstateId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/{subCatid}", method = RequestMethod.POST)
	public ResponseEntity<RealEstateResource> saveRealEstate(@PathVariable Long userId, @PathVariable Long subCatid, @RequestBody RealEstateResource realEstateRes){
		RealEstate re = realEstateService.saveRealEstate(userId, subCatid, realEstateRes.toRealEstate());
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(re), HttpStatus.OK);
	}
	@RequestMapping(value = "/detail/{realEstateId}", method = RequestMethod.POST)
	public ResponseEntity<RealEstateResource> saveRealEstateDetails(@PathVariable long realEstateId, @RequestBody Set<Long> details){
		RealEstate re=realEstateService.saveRealEstateDetails(realEstateId, details);
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(re), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/feature/{realEstateId}", method = RequestMethod.POST)
	public ResponseEntity<RealEstateResource> saveRealEstateFeatures(@PathVariable Long realEstateId, @RequestBody Set<Long> features){
		RealEstate re = realEstateService.saveRealEstateFeatures(realEstateId, features);
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(re), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address/{realEstateId}/{townId}", method = RequestMethod.POST)
	public ResponseEntity<RealEstateResource> saveRealEstateAddres(@PathVariable Long realEstateId, @PathVariable Long townId, @RequestBody AddressResource addressRes){
		RealEstate re =  realEstateService.saveRealEstateAddress(realEstateId, townId, addressRes.toAddress());
		return new ResponseEntity<RealEstateResource>(new RealEstateResourceAsm().toResource(re), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/showcase")
	public ResponseEntity<RealEstateListResource> lelocAppShowCase(){
		return new ResponseEntity<RealEstateListResource>(new RealEstateListResourceAsm().toResource(realEstateService.showCase()), HttpStatus.OK);
	}
	
	@RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

        try {
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                Long size = file.getSize();
                if(!(mimeType.contains("image") || mimeType.contains("IMAGE")))
                	throw new Exception("Bad File");
                
                mimeType = mimeType.split("/")[0] + "/jpeg";
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BufferedImage im = ImageIO.read(file.getInputStream());
                ImageIO.write(im, "jpeg", outputStream);
              
                
                filename = filename.substring(0, filename.lastIndexOf(".")) + ".jpeg";
                //byte[] bytes = file.getBytes();

                outputStream.flush();
                byte[] bytes = outputStream.toByteArray();
                outputStream.close();
                
                UploadedFile newFile = new UploadedFile(filename, 1L, bytes, mimeType, true, size);
                realEstateService.uploadFile(newFile);
                //fileUploadService.uploadFile(newFile);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
	
	@RequestMapping(value = "/remove_file", method = RequestMethod.POST)
    public ResponseEntity deleteFile(@RequestParam("file") String filename, @RequestParam int size){
    	
    	
		filename = filename.substring(0, filename.lastIndexOf(".")) + ".jpeg";
		
    	realEstateService.deleteUploadedFile(filename, size);
        
    	return new ResponseEntity<>("{}", HttpStatus.OK);
    }
	
}
