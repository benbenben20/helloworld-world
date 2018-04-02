package com.example.springboot.job;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = JobProperties.PREFIX)
public class JobProperties {

	public static final String PREFIX = "elastic.job";
	
	private String namespace;

	private String serverList;

	private int baseSleepTimeMilliseconds;

	private int maxSleepTimeMilliseconds;

	private int maxRetries;

	private boolean disabled;
}
