package com.webproject.core.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DetailType {
	@JsonProperty("PRICE")
	PRICE,
	@JsonProperty("TEXT")
	TEXT,
	@JsonProperty("RADIO")
	RADIO,
	@JsonProperty("CHECKBOX")
	CHECKBOX,
	@JsonProperty("METERSQUARE")
	METERSQUARE
}
