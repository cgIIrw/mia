package com.cg.ioc.beanreader;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
