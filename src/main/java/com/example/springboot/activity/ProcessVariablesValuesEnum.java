package com.example.springboot.activity;

public enum ProcessVariablesValuesEnum {

	approval_approve("1"),
	approval_reject("0");
	
	private String value;
	private ProcessVariablesValuesEnum(String s){
		value = s;
	}
	
	@Override
	public String toString(){
		return value;
	}
}
