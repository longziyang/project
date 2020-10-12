//package com.mystery.aop;
//
//import java.util.Arrays;
//import java.util.Date;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class AopAspect {
//
//	public AopAspect() {
//		System.out.println("触发aop构造");
//	}
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Pointcut("execution(* com.my.service.*.*(..))")
//	public void point() {
//
//	}
//
//	@Around(value = "point()")
//	public Object before(ProceedingJoinPoint point) {
//
//		System.out.println("开始记录");
//		Object object;
//		try {
//			object = point.proceed();
//
//			System.out.println("记录结束");
//
//			String objectname = point.getSignature().getDeclaringTypeName();
//			String methodname = point.getSignature().getName();
//
//			String sql = "insert into aop(id,objectname,methodname,createtime,args) values(null,?,?,?,?)";
//			jdbcTemplate.update(sql, objectname, methodname, new Date(), Arrays.toString(point.getArgs()));
//			return object;
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}