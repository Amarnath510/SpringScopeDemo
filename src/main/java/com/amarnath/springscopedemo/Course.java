package com.amarnath.springscopedemo;

public class Course {
	
	public static int count;
	
	public Course() {
		++count;
	}
	
	public int getCount() {
		return count;
	}
}
