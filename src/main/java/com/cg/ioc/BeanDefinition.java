package com.cg.ioc;

public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String beanClassname;

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
}
