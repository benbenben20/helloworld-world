package com.example.springboot.prove.innerclass;

/**
 * 内部类调用外部类
 * @author Administrator
 *
 */
public class OuterClass {

	private String name;
	private Integer age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}


	public class InnerClass{
		// 构造方法中直接使用外部类中属性 尽管他是私有的
		public InnerClass() {
			name = "貂蝉";
			age = 18;
		}

		private String display(){
			return "name:"+ name +"  age:"+age;
		}
		
	}
	
	public static void main(String[] args) {
		InnerClass inCls = new OuterClass().new InnerClass();
		//当我们在创建某个外围类的内部类对象时，此时内部类对象必定会捕获一个指向那个外围类对象的引用，只要我们在访问外围类的成员时，就会用这个引用来选择外围类的成员。
		System.out.println(inCls.display());
		
	}
	
}
