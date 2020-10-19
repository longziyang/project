package com.project.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Aspect
@Component
public class AopConfig {

    @Pointcut("@within(com.project.config.annotation.Transfer)")
    public void pointcut() {

    }

    @AfterReturning(value = "pointcut()")
    public Object afterReturning(JoinPoint joinPoint) {

        return null;
    }

    @AfterThrowing(value = "pointcut()")
    public Object afterThrowing(JoinPoint joinPoint) {

        return null;
    }


    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Object obj = point.proceed();


        return null;
    }

}
