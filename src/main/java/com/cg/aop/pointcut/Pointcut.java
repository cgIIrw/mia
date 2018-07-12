package com.cg.aop.pointcut;

import com.cg.aop.filter.ClassFilter;
import com.cg.aop.filter.MethodMatcher;

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
