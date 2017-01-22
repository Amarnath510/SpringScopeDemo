package com.amarnath.springscopedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
	
	@Autowired
    public Student student;
	
	@Autowired
	public Course course;
	
	public void printSingletonCount() {
		System.out.println("Autowiring Singleton Scope Student Object count = " + student.getCount() );
	}

	public void printPrototypeCount() {
		System.out.println("Autowiring Prototype Scope Course Object count = " + course.getCount() );
	}
}
