package com.example.springboot.prove.innerclass;

import com.example.springboot.prove.innerclass.StaticInnerClass.InnerClassStatic;

/**
 * 静态嵌套类
 * 建造模式 使用静态嵌套类
 * @author Administrator
 *
 */
public class StaticInnerClass {

	static class InnerClassStatic{
		
	}
	
}

class test{
	public static void main(String[] args) {
		InnerClassStatic innerClass = new StaticInnerClass.InnerClassStatic();
	}
}