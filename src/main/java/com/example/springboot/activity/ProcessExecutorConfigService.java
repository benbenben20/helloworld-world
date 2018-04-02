package com.example.springboot.activity;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;

import com.example.springboot.mybatis.model.ProcessExecutorConfigModel;

public interface ProcessExecutorConfigService {

	public void assignTo(DelegateTask task);
	public void initTask(DelegateTask task,DelegateExecution execution);
	
	//查询所有记录总数
	public int findAllCount();
	
	public ProcessExecutorConfigModel findOne(ProcessExecutorConfigModel processExecutorConfig);
	
	
}
