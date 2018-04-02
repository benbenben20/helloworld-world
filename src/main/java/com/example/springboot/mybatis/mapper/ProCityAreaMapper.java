package com.example.springboot.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.mybatis.model.ProCityAreaModel;

@Mapper
public interface ProCityAreaMapper {

public String columns="id,parent_id,city_id,city_name,create_time,update_time";
	
	public String insert="parent_id,city_id,city_name,create_time";
																																																																																																												
	public String property="#{id},#{parentId},#{cityId},#{cityName},#{createTime},#{updateTime}";
	
	public String insertProperty="#{parentId},#{cityId},#{cityName},#{createTime}";
																																																																																																																				
	public String update="parent_id=#{parentId},city_id=#{cityId},city_name=#{cityName},update_time=#{updateTime}";
	
	
	@Insert("insert into tbl_pro_city_area ("+insert+") values ("+insertProperty+")")
	public long insert(ProCityAreaModel proCityArea);
}
