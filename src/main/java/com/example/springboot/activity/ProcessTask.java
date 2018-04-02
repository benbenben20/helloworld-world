package com.example.springboot.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProcessTask implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2109516843399290949L;
	
	protected String id;
	protected String name;
	protected String description;
	protected String taskKey;
	protected String formKey;
	protected String processKey;
	protected String businessKey;
	protected String assignee;
	protected Date createTime;
	

}
