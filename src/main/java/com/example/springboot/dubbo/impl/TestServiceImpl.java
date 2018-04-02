package com.example.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springboot.dubbo.api.TestService;

@Service(version = "1.0.0",protocol = "dubbo")
public class TestServiceImpl implements TestService{

	@Override
	public String sayHello(String name) {
		
		return "Hello"+name+"!";
	}

}
