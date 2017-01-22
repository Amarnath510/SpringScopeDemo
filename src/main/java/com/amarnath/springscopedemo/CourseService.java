package com.amarnath.springscopedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseService {
	
	@Autowired
    public Student autoStuSingleton;
	
	@Autowired
	public Course autoCourseSingleton;
	
	public void printSingletonCount() {
		System.out.println("Autowiring Singleton Scope Student Object count = " + autoStuSingleton.getCount() );
	}

	public void printPrototypeCount() {
		System.out.println("Autowiring Prototype Scope Course Object count = " + autoCourseSingleton.getCount() );
	}
}
