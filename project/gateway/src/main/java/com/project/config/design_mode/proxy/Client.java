package com.project.config.design_mode.proxy;

public class Client {

    public static void main(String[] args) {

        Person person = new StudentProxy(new Student());
        person.eat();
    }
}
