package com.example.springboot.httpclient;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.common.json.JSONObject;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/shasenggujia")
public class ShasenggujiaCreateOrderResp {

	private static final Logger log = LoggerFactory.getLogger(ShasenggujiaCreateOrderResp.class);
	
	@RequestMapping(path="createOrder/response",method={RequestMethod.POST})
	public void CreateOrderResp(@RequestBody String response){
		log.info("response:"+response);
		JSONArray json=JSONArray.fromObject(response);
		JSONObject jsonOne;
		Map<String,String> map = null;
		for(int i=0;i<json.size();i++){
			log.info("json:"+json);
			
		}
	}
}
