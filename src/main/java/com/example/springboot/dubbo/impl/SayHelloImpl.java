package com.example.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springboot.dubbo.api.SayHelloService;
import com.example.springboot.dubbo.vo.SayHelloVo;

@Service(version = "1.0.0",protocol = "dubbo")
public class SayHelloImpl implements SayHelloService{

	@Override
	public String sayHello(SayHelloVo vo) {
		return "欢迎"+vo.getAge()+"的"+vo.getName()+",登录时间："+vo.getDate();
	}

}
