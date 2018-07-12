package com.cg.ioc.beans.factory;

import com.cg.aop.BeanPostProcessor;
import com.cg.ioc.beans.BeanDefinition;

import java.util.ArrayList;
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
            bean = initializeBean(bean, name);
        }
        return bean;
    }

    // 注册bean
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

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        // 这算是组成ioc的关键组成部分之一，因为beanDefinition内部是可以通过字符串创建Class的...
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected abstract void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception;



    // 添加的aop部分
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
             bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }

        return bean;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBean().getClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
