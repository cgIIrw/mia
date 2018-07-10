package com.cg.aop;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
