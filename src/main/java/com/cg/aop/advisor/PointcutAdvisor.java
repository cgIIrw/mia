package com.cg.aop.advisor;

import com.cg.aop.pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
