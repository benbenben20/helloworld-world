package com.example.springboot.dao.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProcessExecutorConfigModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3482263041652123150L;
	private String processKey;
	private String taskKey;
	private String executorExp;
	private String extendClassname;
	private String extendParam;
	private Integer LastoneRedo;
	private Date createTime;
	private Date updateTime;
	private int viewIndex;
	
}
