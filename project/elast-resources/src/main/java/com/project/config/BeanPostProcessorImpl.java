package com.project.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {

    // Bean 实例化之前进行的处理

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == MyService.class) {
            System.out.println("对象" + beanName + "开始实例化" + bean);
        }
        return bean;
    }

    // Bean 实例化之后进行的处理
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == MyService.class) {
            System.out.println("对象" + beanName + "实例化完成" + bean);
        }
        return bean;
    }
}
