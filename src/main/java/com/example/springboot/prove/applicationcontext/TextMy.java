package com.example.springboot.prove.applicationcontext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextMy {

	public void init(){
		System.out.println("my bean start init!!");
	}
	
	public void doTest(){
		log.info("do my test!");
		System.out.println("do my test!!");
	}
	
	public void destory(){
		System.out.println("my bean begin close!!");
	}
}
