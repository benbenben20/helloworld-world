package com.example.springboot.dubbo.api;

import com.example.springboot.dubbo.vo.SayHelloVo;

public interface SayHelloService {

	public String sayHello(SayHelloVo vo);
}
