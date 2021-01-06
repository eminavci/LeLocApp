package com.webproject.core.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is also use for the real estate's fromwho attribute
 * 
 * @author emin
 *
 */
public enum CompanyType {
	 @JsonProperty("REAL_ESTATE_AGENCY")
	REAL_ESTATE_AGENCY,
	@JsonProperty("BUILDING_COMPANY")
	BUILDING_COMPANY,
	@JsonProperty("INSTITUTION")
	INSTITUTION,
	@JsonProperty("INDIVIDUAL")
	INDIVIDUAL,
	@JsonProperty("OTHER")
	OTHER
}
