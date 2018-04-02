package com.example.springboot.designpatterns.builder;

/**
 * 具体的装机人员
 * @author Administrator
 *
 */
public class ConcreateBuilder extends Builder{

	//创建产品实例
	Computer computer = new Computer();
	
	//组装产品
	@Override
	public void BuildCPU() {
		computer.Add("组装CPU");
	}

	@Override
	public void BuildMainboard() {
		computer.Add("组装主板");		
	}

	@Override
	public void BuildHD() {
		computer.Add("组装硬盘");		
	}

	@Override
	public Computer GetComputer() {
		return computer;
	}

}
