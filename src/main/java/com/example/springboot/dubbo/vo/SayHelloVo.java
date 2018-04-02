package com.example.springboot.dubbo.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SayHelloVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -545263216704393953L;

	private String name;
	
	private String age;
	
	private String date;
	
	
}
