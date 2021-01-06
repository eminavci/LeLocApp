package com.webproject.core.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webproject.core.models.entities.UploadedFile;

public interface UploadedFileRepo extends JpaRepository<UploadedFile, Long>{

	public List<UploadedFile> findByUseridAndIsActive(Long userid, boolean isActive);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM UploadedFile u WHERE u.filename = :fileName AND u.size BETWEEN :fileSizeS AND :fileSizeB")
	public void deletebyCustomQuery(@Param("fileName") String fileName, @Param("fileSizeS") long fileSizeS, @Param("fileSizeB") long fileSizeB);
	
	@Transactional
	@Modifying
	public void deleteByFilenameAndUserid(String fileName, Long userid);
	
}
