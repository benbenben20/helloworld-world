/**
 * Project Name:midai-oa-service-impl
 * File Name:HttpTest.java
 * Package Name:com.midai.test
 * Date:2017年12月25日下午2:47:18
 * Copyright (c) 2017, jing9241120@sina.com
 *
*/

package com.example.springboot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.httpclient.HttpClient;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassName:HttpTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年12月25日 下午2:47:18 <br/>
 * @author   孟海山  
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWorldApplication.class)
public class HttpTest {

	@Autowired
	private HttpClient httpClient;
	
	@Test
	public void test01(){
		String resp = httpClient.myPostTest();
		log.info("---"+resp);
		System.out.println("===="+resp);
	}
	
	@Test
	public void test02(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("", "");
		httpClient.myGetTest(map);
	}
	
	public static void main(String[] args) {
		
		String resp = " {\"code\":200,\"orderExt\":{\"id\":1847,\"code\":\"66wHR5pujT\",\"recommend_price\":15.94,\"app_order_id\":\"998\",\"created_at\":\"2018-01-29 10:13:19\",\"historyPriceTrend\":[\"16.39\",\"16.30\",\"16.20\",\"16.12\",\"16.03\",\"15.94\"],\"futurePriceTrend\":[\"15.94\",\"14.84\",\"13.81\"]}} ";                  
	
		JSONObject fromObject = JSONObject.fromObject(resp);
		JSONArray object = (JSONArray) fromObject.get("orderExt");
		Integer code = fromObject.getInt("code");
		System.out.println(code);
	
	
	}
	
	
	
	
	
	
	
	
	
}

