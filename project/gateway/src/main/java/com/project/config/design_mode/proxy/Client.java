package com.project.config.design_mode.proxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {

//        Person person = new StudentProxy(new Student());
//        person.run();
        
        Person person = (Person)Proxy.newProxyInstance(Client.class.getClassLoader(), new Class<?>[] {Person.class}, new StudentProxyImp(new Student()));
        person.run();
    }
}
