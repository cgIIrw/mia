package com.cg.ioc.beans.beanreader;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
