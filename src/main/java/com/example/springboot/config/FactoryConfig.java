package com.example.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableAutoConfiguration
public class FactoryConfig {

	final static Logger log = LoggerFactory.getLogger(FactoryConfig.class);
	
	public MethodValidationPostProcessor  methodValidationPostProcessor(){
		
		return new MethodValidationPostProcessor();
	}
}
