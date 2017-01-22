package com.amarnath.springscopedemo;

public class Student {
	
	private static int count; 
	
	public Student() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
}
