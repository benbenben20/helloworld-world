package com.example.springboot;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.mybatis.model.SystemRoleModel;
import com.example.springboot.mybatis.service.SystemRoleService;

import net.sf.json.JSONObject;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringBootWorldApplication.class)
public class MybatisTest {

	@Resource(name = "systemRoleServiceImpl")
	private SystemRoleService systemRoleService;
	
	@Test
	public void test(){
		List<SystemRoleModel> modle = systemRoleService.findAll();
		JSONObject jsonObject = JSONObject.fromObject(modle);
		System.out.println(jsonObject.toString());
	}
}
