package com.cg.aop.proxy;

import com.cg.aop.AdvisedSupport;
import com.cg.aop.BeanFactoryAware;
import com.cg.aop.BeanPostProcessor;
import com.cg.aop.TargetSource;
import com.cg.aop.advisor.AspectJExpressionPointcutAdvisor;
import com.cg.aop.proxy.JdkDynamicAopProxy;
import com.cg.ioc.beans.factory.AbstractBeanFactory;
import com.cg.ioc.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = ( AbstractBeanFactory)beanFactory;

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }

        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory
                .getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                AdvisedSupport advisedSupport = new AdvisedSupport();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);

                return new JdkDynamicAopProxy(advisedSupport).getProxy();
            }
        }
        return bean;
    }
}
