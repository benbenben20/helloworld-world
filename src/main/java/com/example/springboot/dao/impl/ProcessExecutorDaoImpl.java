package com.example.springboot.dao.impl;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.example.springboot.activity.ProcessExecutorConfigService;
import com.example.springboot.dao.BaseDao;
import com.example.springboot.dao.model.ProcessExecutorConfigModel;

public class ProcessExecutorDaoImpl extends BaseDao implements ProcessExecutorConfigService{

	@Override
	public void assignTo(DelegateTask task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTask(DelegateTask task, DelegateExecution execution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findAllCount() {
		String sql = "select * from tbl_process_executor_config";
		List<ProcessExecutorConfigModel> list = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ProcessExecutorConfigModel.class));
		return list.size();
	}
	
	public static void main(String[] args) {
		ProcessExecutorConfigService r = new ProcessExecutorDaoImpl();
		System.out.println(r.findAllCount());
	}

	@Override
	public com.example.springboot.mybatis.model.ProcessExecutorConfigModel findOne(
			com.example.springboot.mybatis.model.ProcessExecutorConfigModel processExecutorConfig) {
		// TODO Auto-generated method stub
		return null;
	}

}
