package com.project.config;

import com.project.entity.Aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Order(5)
@Aspect
@Component
public class AopConfig {

    private static final String INSERT_INTO="INSERT INTO AOP (`type`,`method`,args,create_time) VALUES (?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Pointcut("@within(com.project.config.annotation.Transfer)")
    public void pointcut() {

    }

    @AfterReturning(value = "pointcut()")
    public Object afterReturning(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        String sql = String.format(INSERT_INTO,className,methodName,args,new Date());
        jdbcTemplate.execute(sql);

        Object object = joinPoint.getThis();
        return object;
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
