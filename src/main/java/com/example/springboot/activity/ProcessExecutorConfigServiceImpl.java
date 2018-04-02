package com.example.springboot.activity;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot.mybatis.mapper.ProcessExecutorConfigMapper;
import com.example.springboot.mybatis.model.ProcessExecutorConfigModel;

public class ProcessExecutorConfigServiceImpl implements ProcessExecutorConfigService {

	@Autowired
	private ProcessExecutorConfigMapper processExecutorConfigMapper;
	
	@Override
	public void assignTo(DelegateTask task) {
		String taskKey = task.getTaskDefinitionKey();
		String processKey = task.getProcessDefinitionId().split(":")[0];
		ProcessExecutorConfigModel p = this.findOne(new ProcessExecutorConfigModel(processKey, taskKey, ""));
		task.setAssignee(p.getExecutorExp());
	}

	@Override
	public void initTask(DelegateTask task, DelegateExecution execution) {
		String taskKey = task.getTaskDefinitionKey();
		String processKey = task.getProcessDefinitionId().split(":")[0];
		task.setCategory(execution.getProcessBusinessKey());
		//如果taskKey以 _sp_edit 为结尾的时候 则赋予特殊 formkey
		if(task.getTaskDefinitionKey().endsWith("_sp_edit") || task.getTaskDefinitionKey().endsWith("_sp__edit1") 
				|| task.getTaskDefinitionKey().endsWith("_sp__edit2") 
				|| task.getTaskDefinitionKey().endsWith("_sp__edit3")
				|| task.getTaskDefinitionKey().endsWith("_sp__edit4")){
			Object o = execution.getVariable(ProcessVariablesEnum.form_sp__edit.toString());
			if(o != null){
				task.setFormKey((String)o);
				taskKey = task.getFormKey();
			}
		}
	}

	@Override
	public int findAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProcessExecutorConfigModel findOne(ProcessExecutorConfigModel processExecutorConfig) {
		return this.processExecutorConfigMapper.findOne(processExecutorConfig);
	}

}
