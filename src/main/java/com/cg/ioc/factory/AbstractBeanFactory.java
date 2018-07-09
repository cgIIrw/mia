package com.cg.ioc.factory;

import com.cg.ioc.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);

        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named" + name + "is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    // 注册bean
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
//        Object bean = doCreateBean(beanDefinition);
//        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception {
        // 没有发生结构性变化
        for (String name : this.beanDefinitionNames) {
            getBean(name);
        }
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
