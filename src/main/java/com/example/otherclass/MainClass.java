package com.example.otherclass;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Student student = ctx.getBean("student", Student.class);
		student.getStudentInfo();
		
		
		ctx.close();
		
		er();
		
		MainClass a = new MainClass();
		a.er1();
	}
	
	
	static public void er() {
		System.out.println("heey");
	}
	
	public void er1() {
		System.out.println("11heey");
	}
}
