package com.project.config.design_mode.proxy;

import java.lang.reflect.Method;

public class StudentProxyImp implements  java.lang.reflect.InvocationHandler {

    private Student student;

    public StudentProxyImp(Student student) {
        this.student = student;
    }

    @Override
    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        
        arg1.invoke(student, arg2);
        return student;
    }

}
