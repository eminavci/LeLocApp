package com.webproject.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "UPLOADED_FILE")
public class UploadedFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String filename;
    private Long userid;
	
    public UploadedFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Lob
    private byte[] file;
    private String mimeType;
    private boolean isActive;
    private long size;
	public UploadedFile(String filename, Long userid, byte[] file, String mimeType, boolean isActive,
			long size) {
		super();
		this.filename = filename;
		this.file = file;
		this.mimeType = mimeType;
		this.isActive = isActive;
		this.size = size;
		this.userid = userid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
