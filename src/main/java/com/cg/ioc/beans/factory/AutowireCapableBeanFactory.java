package com.cg.ioc.beans.factory;

import com.cg.ioc.beans.BeanDefinition;
import com.cg.ioc.beans.BeanReference;
import com.cg.ioc.beans.PropertyValue;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {

            // 这算是组成ioc的关键组成部分之一，因为beanDefinition内部是可以通过字符串创建Class的...
            Object bean = createBeanInstance(beanDefinition);
            beanDefinition.setBean(bean);
            applyPropertyValues(bean, beanDefinition);
            return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    // 通过反射，把属性值传递给通过newInstance方法生成的"空属性"对象
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValueList()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference)value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(bean, value);
        }
    }
}
