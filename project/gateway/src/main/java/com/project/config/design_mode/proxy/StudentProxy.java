package com.project.config.design_mode.proxy;

public class StudentProxy implements Person{

    private Student student;

    public StudentProxy(Student student) {
        this.student = student;
    }

    @Override
    public void eat() {

        System.out.println("学生打饭");
        student.eat();
        System.out.println("学生洗碗");
    }

    @Override
    public void run() {
        
        System.out.println("学生集合");
        student.run();
        System.out.println("学生解散");
    }
}
