package com.example.springboot.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.example.springboot.mybatis.model.SystemRoleModel;

@Mapper
public interface SystemRoleMapper {

	public String columns="role_id,role_name,description,system,create_time,update_time";
	public String insert="role_name,description,system,create_time";
	public String property="#{roleId},#{roleName},#{description},#{system},#{createTime},#{updateTime}";
	public String insertProperty="#{roleName},#{description},#{system},NOW()";
	public String update="role_name=#{roleName},description=#{description},system=#{system}";
	
	@Select("select "+columns+" FROM tbl_system_role ")
	@ResultMap(value="com.example.springboot.mybatis.mapper.SystemRoleMapper.SystemRoleModelMap")
	public List<SystemRoleModel> findAll();
}
