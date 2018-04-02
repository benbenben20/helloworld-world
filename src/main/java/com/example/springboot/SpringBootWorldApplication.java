package com.example.springboot;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
public class SpringBootWorldApplication {

	@Bean
	public Queue queue(){
		return new ActiveMQQueue("sample.queue");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWorldApplication.class, args);
	}
}
