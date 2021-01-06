package com.webproject.spring.common;

public enum Environment {
	DEV,
	TEST,
	PROD;
	
	public boolean isDEV(){
		return this == DEV;
	}
	
	public boolean isPROD(){
		return this == PROD;
	}
	
	public boolean isTEST(){
		return this == TEST;
	}
}
