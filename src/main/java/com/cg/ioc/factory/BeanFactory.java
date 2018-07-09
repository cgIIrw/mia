package com.cg.ioc.factory;

import com.cg.ioc.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
