# Spring Scope Demo

## Spring Bean scopes
  - **Singleton** - Default Scope. Only one instance will be created per bean definition.
  - **Prototype** - Create a new instance for each bean call.
  - **Request** - Scope of the bean will be limited to the request life cycle.
  - **Session** - Limited to session.
  - **Global Session** - Limited to global session context. <br />
In the below section we will talk only about Singleton and Prototype scopes and how does Autowiring annotation works with this.  

## Project Creation:
  - Eclipse -> File -> New -> Maven -> Maven Project -> Select maven-archetype-quickstart -> Give artifact-id and group-id -> Finish.
  - Project Strucutre:
  
    ![ProjectStructure](https://github.com/Amarnath510/SpringScopeDemo/blob/master/ProjectStructure.png)
  
## Singleton Scope
  - Only one instance will be created per bean definition.
  - Example consider the following bean, <br />
  
    ```xml
    <bean id="studentBean" class="com.amarnath.springscopedemo.Student" /> 
    ``` 
    Now even though any number times we call getBean("studentBean") you will get the same instance. 
  - In the below code we are calling getBean method 3 times but still we will get the same Student instance for all calls.
    
    ```Java
      Student s = null;
      for (int i = 0; i < 3; i++) {
       	s = (Student) context.getBean("studentBean"); // only 1 object is created and the same will be returned.
      }
    ```
  - Bottom line: Default scope is Singleton scope where only one instance is created per bean definition.

## Prototype Scope
  - Every getBean() call will return a new instance.
  - To declare a bean type as prototype we have to mention in the bean explicitly as below, <br /> 
    
    ```xml
      <bean id="courseBean" class="com.amarnath.springscopedemo.Course" scope="prototype"/>
    ```
  - But if we call the same getBean method multiple times on a Prototype scoped bean then we will get new instance every time,
  
    ```Java
        Course c = null;
        for (int i = 0; i < 3; i++) {
        	c = (Course) context.getBean("courseBean"); // new bean gets created for every getBean call.
        }
    ```
    
 ## Singleton and Prototype scope on Autowired annotation
   - **Case-1:** Bean definition is provided in the configuration file and we are using Autowired annotation <br />
     - Just give @Autowired annotation for the instance as below and Spring will get the same instance if Student definition is Singleton else it will get a new instance if the bean definition is Prototype.
       
       ```Java
          @Autowired
          public Student student;
	
          @Autowired
          public Course course;
       ```
   - **Case-2:** What happens if the bean defition is no defined in the configuration file then how does we give the scope? <br />
     - In these cases we have to use `@Scope` annotation for the instance classes.
     - Example: Remove the bean definitions from the config file and add ```<context:component-scan base-package="com.amarnath" />``` such that it scans all the components in the application.
     
       ```Java  
         @Componenet
         @Scope("singleton")
         class Student {
          ...
         }
         
         @Componenet
         @Scope("prototype")
         class Course {
          ...
         }
       ```  

**NOTE:** When using @Autowired annotation if there is more than one bean definition per Student or Course instance then we have to specify which bean we want to refer to which can be done using @Qualifier("bean id name") annotation.


## Output

  ```
    Jan 22, 2017 4:02:48 PM org.springframework.context.support.AbstractApplicationContext prepareRefresh
    INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@6193b845: startup date [Sun Jan 22 16:02:48 IST 2017]; root of context hierarchy
    Jan 22, 2017 4:02:48 PM org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
    INFO: Loading XML bean definitions from class path resource [applicationContext.xml]
    Jan 22, 2017 4:02:48 PM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
    INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@58651fd0: defining beans [courseService,studentService,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,studentBean,courseBean,org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor]; root of factory hierarchy

    ***** Singleton Scope *****

    Student Objects Count for Singleton Scope: 1

    ***** Prototype Scope *****

    Course Objects Count for Prototype Scope: 5

    ***** Autowiring Singleton/Prototype Scope *****

    Autowiring Singleton Scope Student Object count = 1
    Autowiring Prototype Scope Course Object count = 5
    Autowiring Singleton Scope Student Object count = 1
    Autowiring Prototype Scope Course Object count = 5
  ```
