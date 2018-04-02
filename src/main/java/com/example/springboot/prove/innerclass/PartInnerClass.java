package com.example.springboot.prove.innerclass;

import org.apache.curator.framework.recipes.atomic.PromotedToLock;

/**
 * 局部内部类
 * @author Administrator
 *
 */
public class PartInnerClass {

	private String name = "貂蝉";
	
	private void getAge(){
		//方法的局部变量只能被标记为  final 或者 局部变量是 effectively final的 内部类才可以使用
		final Integer age = 18;
		
		//局部内部类只能使用 abstract final 修饰
		class InnerClass{
			public void detailWork(){
				System.out.println("获取外部内name:"+name);
				System.out.println("(方法的局部变量只有被声明为不可变内部类才可以使用)获取内部类属性age:"+age);
			}//close inner class method
		}// close inner class definition
		
		InnerClass innerCls = new InnerClass();
		innerCls.detailWork();
	}
	
	public static void main(String[] args) {
		new PartInnerClass().getAge();
	}
	
}
