package com.example.springboot.activity;

import org.apache.commons.lang3.StringUtils;

public class PendingTask extends ProcessTask{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7191442266559732658L;

	@Override
	public String getFormKey(){
		return StringUtils.isEmpty(this.formKey)?this.taskKey:this.formKey;
	}
}
