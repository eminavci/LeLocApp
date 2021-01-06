package com.webproject.core.common.api;

public interface ErrKeys {
	
	public static final int GENERAL_INTERNAL_ERROR              = 100;
	public static final int GENERAL_NOT_FOUND_ERROR				= 101;
	
	/*
	 * 110 - 149 place exceptions
	 * */
	public static final int COUNTRY_NOT_FOUND 				= 110;
	public static final int COUNTRY_ALREADY_EXIST			= 111;
	
	
	
	/*
	 * 150 - 199 user exceptions
	 * */
	public static final int USER_NOT_FOUND					= 150;
	public static final int USER_EMAIL_PASSWORD_WRONG		= 151;
	public static final int USER_EMAIL_EXIST_ALREADY		= 152;
	public static final int USER_ADDRESS_NOT_FOUND			= 153;
	public static final int USER_COMPANY_NOT_FOUND			= 154;
	public static final int USER_IS_NOT_CORPORATIVE_TYPE	= 155;
	public static final int USER_MSG_NOT_FOUND				= 156;
	public static final int USER_CANNOT_SEND_MSG_HIMSELF    = 157;
	public static final int USER_NO_SESSION					= 158;
	
	/*
	 * 200 - 249 category exceptions
	 * */
	public static final int CATEGORY_NOT_FOUND				= 200;
	public static final int CATEGORY_ALREADY_EXIST			= 201;
	
	/*
	 * 250 - 300 advertisemet excepitons
	 * */
	public static final int REAL_ESTATE_NOT_FOUND			= 250;
	public static final int REAL_ESTATE_IMG_NOT_FOUND		= 251;
	
}
