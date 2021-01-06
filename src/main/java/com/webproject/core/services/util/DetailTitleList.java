package com.webproject.core.services.util;

import java.util.HashSet;
import java.util.Set;

import com.webproject.core.models.entities.DetailTitle;

public class DetailTitleList {

	private Set<DetailTitle> dtList;

	public DetailTitleList(Set<DetailTitle> dtList) {
		super();
		this.dtList = dtList == null ? new HashSet<DetailTitle>() : dtList;
	}
	
	public Set<DetailTitle> getDtList() {
		return dtList;
	}
	public void setDtList(Set<DetailTitle> dtList) {
		this.dtList = dtList;
	}
}
