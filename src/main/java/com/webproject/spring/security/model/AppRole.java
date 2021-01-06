package com.webproject.spring.security.model;

public interface AppRole {
	public final static String ROLE_ADMIN 		= "ROLE_ADMIN";
	public final static String ROLE_USER 		= "ROLE_USER";

	
	public static enum RoleEnum {
		ROLE_ADMIN(AppRole.ROLE_ADMIN),
		ROLE_USER(AppRole.ROLE_USER);
		
	    private RoleEnum(final String roleName){
	        this.roleName = roleName;
	    }
	    public String getRoleName() {
	        return roleName;
	    }
	    private String roleName = null;
	}
}
