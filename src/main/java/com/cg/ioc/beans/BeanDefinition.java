package com.cg.ioc.beans;

public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String beanClassname;

    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {

    }

//    public BeanDefinition(Object bean) {
//        this.bean = bean;
//    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassname() {
        return beanClassname;
    }

    public void setBeanClassname(String beanClassname) {
        this.beanClassname = beanClassname;

        try {
            this.beanClass = Class.forName(beanClassname);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
