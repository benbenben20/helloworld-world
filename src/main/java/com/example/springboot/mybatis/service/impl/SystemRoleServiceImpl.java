package com.example.springboot.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.mybatis.mapper.SystemRoleMapper;
import com.example.springboot.mybatis.model.SystemRoleModel;
import com.example.springboot.mybatis.service.SystemRoleService;

@Service(value = "systemRoleServiceImpl")
public class SystemRoleServiceImpl implements SystemRoleService{

	@Autowired
	private SystemRoleMapper systemRoleMapper;
	
	@Override
	public List<SystemRoleModel> findAll() {
		return systemRoleMapper.findAll();
	}

}
