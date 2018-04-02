package com.example.springboot.prove.applicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationC {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("test-conf.xml");
		ApplicationContextAwareTest test1 = (ApplicationContextAwareTest) context.getBean("appcontext");
		ApplicationContext appContext = test1.getContext();
		TextMy test2 = (TextMy) appContext.getBean("testA");
		test2.doTest();
		context.registerShutdownHook();
	}
}
