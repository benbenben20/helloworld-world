package com.example.springboot.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProCityAreaModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4415838336101522640L;
	
	private Integer id;
	private String parentId;
	private String cityId;
	private String cityName;
	private Date createTime;
	private Date updateTime;

}
