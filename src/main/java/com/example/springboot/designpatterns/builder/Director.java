package com.example.springboot.designpatterns.builder;

/**
 * 指挥者
 * 控制产品生产的顺序过程
 * 隔离的客户和对象的生产过程
 * 电脑城老板
 * @author Administrator
 *
 */
public class Director {

	//指挥装机人员组装电脑
	public void Construct(Builder builder){
		builder.BuildCPU();
		builder.BuildMainboard();
		builder.BuildHD();
	}
}
