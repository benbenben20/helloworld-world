package com.example.springboot.drools.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.example.springboot.drools.model.Order;
import com.example.springboot.drools.model.User;

public class TestPoint {

	public static void main(String[] args) throws ParseException {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		List<Order> orderList = getInitData();
		for (int i = 0; i < orderList.size(); i++) {
			Order o = orderList.get(i);
			kSession.insert(o);
			kSession.fireAllRules();
			// 执行完规则后, 执行相关的逻辑
			addScore(o);
		}
		kSession.dispose();
	}

	private static void addScore(Order o) {
		System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());
	}

	private static List<Order> getInitData() throws ParseException {
		 List<Order> orderList = new ArrayList<Order>();  
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	        {  
	            Order order = new Order();  
	            order.setAmount(80);  
	            order.setBookingDate(df.parse("2015-07-01"));  
	            User user = new User();  
	            user.setLevel(1);  
	            user.setName("Name1");  
	            order.setUser(user);  
	            orderList.add(order);  
	        }  
	        {  
	            Order order = new Order();  
	            order.setAmount(200);  
	            order.setBookingDate(df.parse("2015-07-02"));  
	            User user = new User();  
	            user.setLevel(2);  
	            user.setName("Name2");  
	            order.setUser(user);  
	            orderList.add(order);  
	        }  
	        {  
	            Order order = new Order();  
	            order.setAmount(800);  
	            order.setBookingDate(df.parse("2015-07-03"));  
	            User user = new User();  
	            user.setLevel(3);  
	            user.setName("Name3");  
	            order.setUser(user);  
	            orderList.add(order);  
	        }  
	        {  
	            Order order = new Order();  
	            order.setAmount(1500);  
	            order.setBookingDate(df.parse("2015-07-04"));  
	            User user = new User();  
	            user.setLevel(4);  
	            user.setName("Name4");  
	            order.setUser(user);  
	            orderList.add(order);  
	        }  
	        return orderList;  
	    }  
}
