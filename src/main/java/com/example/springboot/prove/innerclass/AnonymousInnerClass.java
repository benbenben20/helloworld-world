package com.example.springboot.prove.innerclass;

/**
 * 匿名内部类
 * 没有名字
 * 只能被实例化一次
 * 通常被声明在方法或者代码块内部 以一个带有分号的花括号结尾 
 * 没有名字 所以没有构造函数
 * 
 * 常用于创建线程
 * @author Administrator
 *
 */
public class AnonymousInnerClass {

	abstract class Animal{
		abstract void paly();
	}
	
	class Dog extends Animal{

		@Override
		void paly() {
			System.out.println("play with human");
		}
		
	}
	
	public static void main(String[] args) {
		//获取一个dog实例
		Animal dog = new AnonymousInnerClass().new Dog();
		//此处的Dog类只使用了一次 如果单独定义一个Dog类会显得有些麻烦 
		dog.paly();
		
		//这个时候可以使用匿名内部类
		Animal dog1 = new AnonymousInnerClass().new Animal() {
			
			@Override
			void paly() {
				System.out.println("精简代码,不用创建一个Dog类 play with human");
			}
		};
		dog1.paly();
	}
	
}
