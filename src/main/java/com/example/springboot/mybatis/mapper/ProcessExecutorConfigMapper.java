package com.example.springboot.mybatis.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.springboot.mybatis.model.ProcessExecutorConfigModel;

@Mapper
public interface  ProcessExecutorConfigMapper{
	public String columns="process_key,task_key,executor_exp,extend_classname,extend_param,lastone_redo,create_time,update_time,view_index,sort_id";
	
	public String insert="process_key,task_key,executor_exp,extend_classname,extend_param,lastone_redo,create_time,sort_id";
																																																																																																												
	public String insertProperty="#{processKey},#{taskKey},#{executorExp},#{extendClassname},#{extendParam},#{lastoneRedo},#{createTime},#{sortId}";
																																																																																																																				
	public String update="executor_exp=#{executorExp}";
	
	@Select("select "+columns+" FROM tbl_process_executor_config ")    
	@ResultMap(value="com.example.springboot.mybatis.mapper.ProcessExecutorConfigMapper.ProcessExecutorConfigModelMap")
	public List<ProcessExecutorConfigModel> findAll();
	
	@Select("select "+columns+" FROM tbl_process_executor_config where process_key=#{processKey} and task_key=#{taskKey}")
	@ResultMap(value="com.example.springboot.mybatis.mapper.ProcessExecutorConfigMapper.ProcessExecutorConfigModelMap")
	public ProcessExecutorConfigModel findOne(ProcessExecutorConfigModel processExecutorConfigModel);
	
	@Select("select count(1) from tbl_process_executor_config ")
	public int findAllCount();
	
	@Insert("insert into tbl_process_executor_config ("+insert+") values ("+insertProperty+")")
	public long insert(ProcessExecutorConfigModel processExecutorConfigModel);

	@Update("update tbl_process_executor_config set "+update+" where process_key=#{processKey} and task_key=#{taskKey}")
	public long update(ProcessExecutorConfigModel processExecutorConfigModel); 
	
	@Delete("delete from tbl_process_executor_config where process_key=#{processKey} and task_key=#{taskKey}")
	public void del(ProcessExecutorConfigModel processExecutorConfigModel);
	
	
	@Select("select "+columns+" FROM tbl_process_executor_config where extend_param = #{roleId}")    
	@ResultMap(value="com.example.springboot.mybatis.mapper.ProcessExecutorConfigMapper.ProcessExecutorConfigModelMap")
	public List<ProcessExecutorConfigModel> findByRoleId(String roleId);
}