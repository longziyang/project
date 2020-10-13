package com.project.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class AopConfig {

    @Pointcut("execution(* com.project.service.*.*(..))")
    public void pointCut() {

    }

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private static final String INSERT_INTO_AOP_LOG = "insert into aop_log(id,class_name,method_name,create_time,args) values(null,?,?,?,?)";

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) {

        try {
            Object object = point.proceed();

            String className = point.getSignature().getDeclaringTypeName();
            String methodName = point.getSignature().getName();
            Object[] args = point.getArgs();

            int result = jdbcTemplate.update(INSERT_INTO_AOP_LOG, className, methodName, new Date(), Arrays.toString(args));
            if (result != 1) {

                return "log保存失败";
            }
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}
