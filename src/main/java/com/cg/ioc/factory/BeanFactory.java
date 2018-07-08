package com.cg.ioc.factory;

import com.cg.ioc.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
