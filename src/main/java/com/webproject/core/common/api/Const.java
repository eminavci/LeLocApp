package com.webproject.core.common.api;

public class Const {

	public final static String DELIMITER = "@@";
	
	public final static int REAL_ESTATE_STATUS_PASSIVE_BY_SYSTEM = 0;
	public final static int REAL_ESTATE_STATUS_ACTIVE = 1;
	public final static int REAL_ESTATE_STATUS_WAITS_MODERATOR_CHECK = 2;
	public final static int REAL_ESTATE_STATUS_PASSIVE_BY_USER = 3;
	public final static int REAL_ESTATE_STATUS_DELETED = 4;
	public final static int REAL_ESTATE_STATUS_COMPLETED = 5; // after sell or rent is done
	
	public final static int MSG_STATUS_PASSIVE = 0;
	public final static int MSG_STATUS_ACTIVE = 1;
	public final static int MSG_STATUS_DELETED = 2;
	
	public final static int USER_TYPE_INDIVIDUAL = 0;
	public final static int USER_TYPE_CORPORATIVE = 1;
	
	/*
	 * 1 : Real Estate added by owner
	 * 2 : Moderator confirmed the real estate
	 * 3 : Moderator reject the realestate
	 * 4 : Owner cancelled the publishment of Real estate
	 * 5 : Real Estate's publishment Date is expired
	 * 6 : Real Estate publishöent date is extended 
	 * 7 : Moderator cancel the publishment of Real Estate with a reason
	 * 8 : RealEstate is cancaled by Moderator because of report
	 * */
	public final static int RE_TRANSACTION_ADDED = 1;
	public final static int RE_TRANSACTION_M_CONFIRMED = 2;
	public final static int RE_TRANSACTION_M_REJECTED = 3;
	public final static int RE_TRANSACTION_U_CANCELED = 4;
	public final static int RE_TRANSACTION_EXPIRED = 5;
	public final static int RE_TRANSACTION_EXTENDED = 6;
	public final static int RE_TRANSACTION_M_REJECTED_REASON = 7;
	public final static int RE_TRANSACTION_ABUSE_REPORTED = 8;
	
	public final static int PHONE_TYPE_MOBILE = 1;
	public final static int PHONE_TYPE_HOME	= 2;
	public final static int PHONE_TYPE_WORK = 3;
	
	public final static int ACCOUNT_REG_EMAIL = 1;
	public final static int ACCOUNT_REG_FB = 2;
	
	
}
