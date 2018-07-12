package com.cg.aop.filter;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
