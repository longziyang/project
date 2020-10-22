package com.project.config.design_mode.proxy;

public class Student implements Person {
    @Override
    public void eat() {
        System.out.println("学生在吃饭");
    }

    @Override
    public void run() {
        System.out.println("学生在跑步");
    }
}
