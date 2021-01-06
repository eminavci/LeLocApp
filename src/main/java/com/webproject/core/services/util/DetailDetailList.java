package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.webproject.core.models.entities.DetailDetail;

public class DetailDetailList {

	private List<DetailDetail> ddList = new ArrayList<DetailDetail>();

	public DetailDetailList(List<DetailDetail> ddList) {
		super();
		this.ddList = ddList == null ? new ArrayList<DetailDetail>() : ddList;
	}
	
	public DetailDetailList(Set<DetailDetail> ddSet){
		this.ddList = new ArrayList<DetailDetail>(ddSet);
	}

	public List<DetailDetail> getDdList() {
		return ddList;
	}
	public void setDdList(List<DetailDetail> ddList) {
		this.ddList = ddList;
	}
}
