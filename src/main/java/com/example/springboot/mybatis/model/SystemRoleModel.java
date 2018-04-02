package com.example.springboot.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SystemRoleModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1748595039789664448L;
	
	private Integer roleId;
	private String roleName;
	private String description;
	private Integer system;
	private Date createTime;
	private Date updateTime;
}
