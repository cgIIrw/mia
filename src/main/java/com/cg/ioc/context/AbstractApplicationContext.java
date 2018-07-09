package com.cg.ioc.context;

import com.cg.ioc.beans.factory.AbstractBeanFactory;

public class AbstractApplicationContext implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {

    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
