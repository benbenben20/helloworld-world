package com.example.springboot.prove.innerclass;

/**
 * 内部类调用外部类 通过  外部类.this 返回一个外部类的实例
 * @author Administrator
 *
 */
public class OuterClasss {

	private void display(){
		System.out.println("我是外部类的实例");
	}
	
	private class InnerClass{
		private OuterClasss getOuterClass(){
			return OuterClasss.this;
		}
	}
	
	public static void main(String[] args) {
		InnerClass innerClass = new OuterClasss().new InnerClass();
		innerClass.getOuterClass().display();;
	}
}
