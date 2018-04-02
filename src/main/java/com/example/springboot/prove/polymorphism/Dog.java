package com.example.springboot.prove.polymorphism;

public class Dog extends Animal{

	public void shout(){
		System.out.println("汪汪汪汪汪汪汪汪汪汪汪汪");
	}
	
	@Override
	public void eat() {
		System.out.println("狗吃东西");
	}

	@Override
	public void fly() {
		
	}

}
