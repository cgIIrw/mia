package com.cg.ioc.context;

import com.cg.ioc.beans.BeanDefinition;
import com.cg.ioc.beans.beanreader.XmlBeanDefinitionReader;
import com.cg.ioc.beans.factory.AbstractBeanFactory;
import com.cg.ioc.beans.factory.AutowireCapableBeanFactory;
import com.cg.ioc.io.ResourceLoader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception{
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
