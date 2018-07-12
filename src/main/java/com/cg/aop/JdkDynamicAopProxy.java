package com.cg.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private  AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
         return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();

        // 如果拦截器方法过滤器都满足基本条件，就执行if中的方法
        if (advised.getMethodInterceptor() != null && advised.getMethodMatcher()
                .matches(method, advised.getTargetSource().getTarget().getClass())) {
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        } else {
            // 不满足条件直接执行原本被代理的方法
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
