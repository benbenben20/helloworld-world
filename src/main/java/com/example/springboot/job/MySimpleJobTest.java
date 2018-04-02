package com.example.springboot.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

@MySimpleJob(value = "JobTest",cron = "0 0/5 * * * ? ",shardingTotalCount = 1)
public class MySimpleJobTest implements SimpleJob{

	@Override
	public void execute(ShardingContext shardingContext) {
		System.out.println(" ============= 定时任务开始执行了。 timeNow；"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

}
