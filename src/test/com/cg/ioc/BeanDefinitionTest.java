package com.cg.ioc;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* BeanDefinition Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 8, 2018</pre> 
* @version 1.0 
*/ 
public class BeanDefinitionTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getBean() 
* 
*/ 
@Test
public void testGetBean() throws Exception {
    Object bean = new Object();
    BeanDefinition beanDefinition = new BeanDefinition(bean);
    beanDefinition.getBean();
//TODO: Test goes here... 
} 


} 
