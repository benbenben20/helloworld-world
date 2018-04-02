package com.example.springboot.designpatterns.builder;

/**
 * 建造者模式
 * 客户端调用
 * 具体的小城去电脑城找老板买电脑
 * @author Administrator
 *
 */
public class BuildPattern {

	public static void main(String[] args) {
		
		//声明电脑城老板和装机人员
		//沟通需求之后，老板叫装机人员执行具体的电脑装配
		//装完后，组装人员搬来组装好的电脑
		//组装人员展示成品
		
		Director director = new Director();
		Builder build = new ConcreateBuilder();
		
		director.Construct(build);
		Computer computer = build.GetComputer();
		computer.Show();
	}
}
