package com.example.springboot.designpatterns.builder;

/**
 * 定义组装的过程
 * @author Administrator
 *
 */
public abstract class Builder {
	//第一步：装CPU
	//第二步：装主板
	//第三步：装硬盘
	//返回产品的方法 获得组装好的电脑
	
	public abstract void BuildCPU();
	
	public abstract void BuildMainboard();
	
	public abstract void BuildHD();
	
	public abstract Computer GetComputer();
}
