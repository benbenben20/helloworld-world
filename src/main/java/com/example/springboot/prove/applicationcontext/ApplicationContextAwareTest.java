package com.example.springboot.prove.applicationcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareTest implements ApplicationContextAware{

	ApplicationContext applicationContext;
	
	public ApplicationContext getContext(){
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

}
