package com.cg.aop.advisor;

import com.cg.aop.advisor.PointcutAdvisor;
import com.cg.aop.pointcut.AspectJExpressionPointcut;
import com.cg.aop.pointcut.Pointcut;
import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;
//            = new AspectJExpressionPointcut();

    private Advice advice;

    public AspectJExpressionPointcutAdvisor(AspectJExpressionPointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
