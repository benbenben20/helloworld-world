package com.example.springboot.dao;

import java.util.List;

import com.example.springboot.dao.model.ProcessExecutorConfigModel;

public interface ProcessExecutorDao {

	public List<ProcessExecutorConfigModel> findAll();
}
