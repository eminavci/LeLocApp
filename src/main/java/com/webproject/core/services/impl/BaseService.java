package com.webproject.core.services.impl;

import org.springframework.util.Assert;

public class BaseService {

	public static void fieldMustHasText(String text, String msg) {
		Assert.hasText(text, msg + " :> The field should not be null or empty!");
	}
	
	public static void fieldMustNotNull(Object obj, String msg){
		Assert.notNull(obj, msg + " :> The field shoudl not be null!");
	}
	
}
