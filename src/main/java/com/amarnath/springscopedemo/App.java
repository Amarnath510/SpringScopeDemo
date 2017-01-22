package com.amarnath.springscopedemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        /*----------------------------- Comment next block and run below code. --------------------------------------*/
        System.out.println("\n***** Singleton Scope *****\n");
        Student s1 = null;
        for (int i = 0; i < 3; i++) {
        	s1 = (Student) context.getBean("studentBean");
        }
        System.out.println("Student Objects Count for Singleton Scope: " + s1.getCount());
 
        System.out.println("\n***** Prototype Scope *****\n");
        Course c = null;
        for (int i = 0; i < 3; i++) {
        	c = (Course) context.getBean("courseBean");
        }

        // count will be 5 because we are creating 3 here and 2 from Autowired annotation in Course and Student service classes.
        System.out.println("Course Objects Count for Prototype Scope: " + c.getCount());
        /*-----------------------------------------------------------------------------------------------------*/
        
        
        /*----------------------------- Comment above and run below code. --------------------------------------*/
        // Using Autowired annotation
        System.out.println("\n***** Autowiring Singleton/Prototype Scope *****\n");
        StudentService studentService = context.getBean(StudentService.class);
        studentService.printSingletonCount();
        studentService.printPrototypeCount();
        
        CourseService courseService = context.getBean(CourseService.class);
        courseService.printSingletonCount();
        courseService.printPrototypeCount();
        /*-----------------------------------------------------------------------------------------------------*/
    }
}
