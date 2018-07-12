package com.cg;

import com.cg.aop.Advisor;
import com.cg.aop.AspectJAwareAdvisorAutoProxyCreator;
import com.cg.aop.AspectJExpressionPointcut;
import com.cg.aop.AspectJExpressionPointcutAdvisor;
import com.cg.ioc.beans.BeanDefinition;
import com.cg.ioc.beans.factory.AbstractBeanFactory;
import com.cg.ioc.context.ApplicationContext;
import com.cg.ioc.context.ClassPathXmlApplicationContext;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Main {
    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/cg/beans.xml");
//        System.out.println(((TestDemo) applicationContext.getBean("testDemo")).getCg());
//        System.out.println(((TestDemo) applicationContext.getBean("testDemo")).getApple().getColor());

        AspectJExpressionPointcut cut = new AspectJExpressionPointcut();
        cut.setExpression("execution(String com.cg.InterTestDemo.getCg())");

        Advice advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
//                System.out.println("方法执行前...");
                long startTime = System.currentTimeMillis();
                Object obj = invocation.proceed();//放行
//                System.out.println("方法执行后...");
                System.out.println(System.currentTimeMillis() - startTime);
                return obj;
            }
        };

        Advisor advisor = new AspectJExpressionPointcutAdvisor(cut, advice);

        BeanDefinition adv = new BeanDefinition();
        adv.setBean(advisor);

        AbstractBeanFactory beanFactory = ((ClassPathXmlApplicationContext) applicationContext).getBeanFactory();

        beanFactory.registerBeanDefinition("advisor", adv);

        AspectJAwareAdvisorAutoProxyCreator aspectJAwareAdvisorAutoProxyCreator = new AspectJAwareAdvisorAutoProxyCreator();

        aspectJAwareAdvisorAutoProxyCreator.setBeanFactory(beanFactory);

        beanFactory.addBeanPostProcessor(aspectJAwareAdvisorAutoProxyCreator);


        //System.out.println(((InterTestDemo) applicationContext.getBean("testDemo")).getApple().getColor());
        ((InterTestDemo) (applicationContext.getBean("testDemo"))).getCg();

    }
}
